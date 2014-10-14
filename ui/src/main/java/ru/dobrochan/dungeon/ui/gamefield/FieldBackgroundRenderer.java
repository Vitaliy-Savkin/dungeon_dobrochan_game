
package ru.dobrochan.dungeon.ui.gamefield;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.BigImage;
import org.newdawn.slick.SlickException;
import ru.dobrochan.dungeon.core.GameField;
import ru.dobrochan.dungeon.core.consts.Surface;
import ru.dobrochan.dungeon.content.ResourceManager;

/**
 * Performs rendering of the background (static objects) of a GameFieldView.
 *
 * @author SkinnyMan
 */
public class FieldBackgroundRenderer
{
	private static final int SINGLE = 0;
	private static final int CENTER = 1;
	private static final int EXT_ANGLE = 2;
	private static final int INT_ANGLE = 3;
	private static final int CROSS_ANGLE = 4;
	private static final int HSIDE = 5;
	private static final int VSIDE = 6;

	private static final int angularDecorOffsetX = 22;
	private static final int angularDecorOffsetY = 22;

	private static final int gridOffsetX = 98;
	private static final int gridOffsetY = 153;

	private int cellWidth = 31;
	private int cellHeight = 31;

	private int gridWidth;
	private int gridHeight;

	private ArrayList<Image> cornerDecorationImages = new ArrayList<Image>();
	private ArrayList<Image> borderDecorationImages = new ArrayList<Image>();

	private HashMap<Integer, HashMap<Integer, List<Image>>> surfaceMap = new HashMap<Integer, HashMap<Integer, List<Image>>>();	

	// border -> (image index, offset in segment)
	private HashMap<Border, ArrayList<Entry<Integer, Integer>>> generatedBorderDecorations =
			new HashMap<Border, ArrayList<Entry<Integer, Integer>>>();
	
	// corner -> image index
	private HashMap<Corner, Integer> generatedCornerDecorations = new HashMap<Corner, Integer>();

	// cell position -> image index
	private HashMap<Integer, Integer> reliefImagesIndexes = new HashMap<Integer, Integer>();
	
	// center cell position -> center image rotation
	private HashMap<Integer, Integer> centerImagesRotation = new HashMap<Integer, Integer>();

	private BigImage grid;
	private BigImage gridBorder;

	private GameField gameField;
	private int gfWidth;
	private int gfHeight;

	private Random random = new Random();

	public FieldBackgroundRenderer()
	{
		ResourceManager rm = ResourceManager.getInstance();

		HashMap<Integer, List<Image>> rockMap = new HashMap<Integer, List<Image>>();
		rockMap.put(SINGLE, rm.getImageList("BATTLEFIELD_ROCK_SINGLE"));
		rockMap.put(CENTER, rm.getImageList("BATTLEFIELD_ROCK_CENTER"));
		rockMap.put(EXT_ANGLE, rm.getImageList("BATTLEFIELD_ROCK_EXT_ANGLE"));
		rockMap.put(INT_ANGLE, rm.getImageList("BATTLEFIELD_ROCK_INT_ANGLE"));
		rockMap.put(CROSS_ANGLE, rm.getImageList("BATTLEFIELD_ROCK_CROSS_ANGLE"));
		rockMap.put(HSIDE, rm.getImageList("BATTLEFIELD_ROCK_HSIDE"));
		rockMap.put(VSIDE, rm.getImageList("BATTLEFIELD_ROCK_VSIDE"));

		HashMap<Integer, List<Image>> waterMap = new HashMap<Integer, List<Image>>();
		waterMap.put(SINGLE, rm.getImageList("BATTLEFIELD_WATER_SINGLE"));
		waterMap.put(CENTER, rm.getImageList("BATTLEFIELD_WATER_CENTER"));
		waterMap.put(EXT_ANGLE, rm.getImageList("BATTLEFIELD_WATER_EXT_ANGLE"));
		waterMap.put(INT_ANGLE, rm.getImageList("BATTLEFIELD_WATER_INT_ANGLE"));
		waterMap.put(CROSS_ANGLE, rm.getImageList("BATTLEFIELD_WATER_CROSS_ANGLE"));
		waterMap.put(HSIDE, rm.getImageList("BATTLEFIELD_WATER_HSIDE"));
		waterMap.put(VSIDE, rm.getImageList("BATTLEFIELD_WATER_VSIDE"));

		surfaceMap.put(Surface.ROCK(), rockMap);
		surfaceMap.put(Surface.WATER(), waterMap);

		//gridBorder = ResourceManager.getInstance().getBigImage("BATTLEFIELD_BORDER");		
		//grid= ResourceManager.getInstance().getBigImage("BATTLEFIELD_GRID");

		System.out.print(System.getProperty("user.dir"));
		try
		{
			gridBorder = new BigImage("Textures\\Back\\GridBorder.png");
			grid = new BigImage("Textures\\Back\\Grid001.png");
		}
		catch (SlickException ex)
		{
			System.out.println(ex);
			Logger.getLogger(FieldBackgroundRenderer.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		gridWidth = grid.getWidth();
		gridHeight = grid.getHeight();

		cornerDecorationImages.addAll(ResourceManager.getInstance().getImageList("BATTLEFIELD_ANGULAR_DECORATION"));
		borderDecorationImages.addAll(ResourceManager.getInstance().getImageList("BATTLEFIELD_BORDER_DECORATION"));
		
		generateCorners();
		generateBorders();
	}

	public GameField getGameField()	{ return gameField; }

	public void setGameField(GameField gameField)
	{
		this.gameField = gameField;
		gfWidth = gameField.widthInCells();
		gfHeight = gameField.heightInCells();
	}

	/**
	 * Draws background (static objects) for the GameFieldView
	 *
	 */
	public void drawBackground(Graphics g)
	{
		//draw border
		g.drawImage(gridBorder, 0, 0);

		for (Corner corner : Corner.values())
			drawCornerDecoration(g, corner);

		for (Border border : Border.values())
			drawBorderDecoration(g, border);
		
		//draw grid
		g.drawImage(grid, gridOffsetX, gridOffsetY);
		for (int i = 0; i < gfHeight; i++)
			for (int j = 0; j < gfWidth; j++)
				processCell(g, j, i);
	}
	
	enum Corner
	{
		TOP_LEFT(180),
		TOP_RIGHT(270),
		BOTTOM_RIGHT(0),
		BOTTOM_LEFT(90);
		
		Corner(int angle) 
		{
	        this.angle = angle;
	    }
		
	    private int angle;

	    public int getAngle() 
	    {
	        return angle;
	    }
	}
	
	private void generateCorners()
	{
		for(Corner corner : Corner.values())
		{
			// probability of the angular decoration appearance
			final double angRnd = 0.5;
			if (random.nextDouble() < angRnd)
				generatedCornerDecorations.put(corner, random.nextInt(cornerDecorationImages.size()));
			else
				generatedCornerDecorations.put(corner, null);
		}
	}
	
	private void drawCornerDecoration(Graphics g, Corner corner)
	{		
		Integer imageIndex = generatedCornerDecorations.get(corner);
		
		if (imageIndex != null)
		{
			int x = gridOffsetX, y = gridOffsetY;
					
			Image img = cornerDecorationImages.get(imageIndex);
			switch (corner)
			{
				case TOP_LEFT:
					x += -img.getWidth() + angularDecorOffsetX;
					y += -img.getHeight() + angularDecorOffsetY;
					break;
				case TOP_RIGHT: 
					x += gridWidth - angularDecorOffsetX;
					y += -img.getHeight() + angularDecorOffsetY;
					break;
				case BOTTOM_RIGHT: 
					x += gridWidth - angularDecorOffsetX;
					y += gridHeight - angularDecorOffsetY;
					break;
				case BOTTOM_LEFT: 
					x += -img.getWidth() + angularDecorOffsetX;
					y += gridHeight - angularDecorOffsetY;
					break;
			}
			img.setRotation(corner.getAngle());
			g.drawImage(img, x, y);
		}
	}	
	
	enum Border
	{
		BOTTOM(180),
		LEFT(270),
		TOP(0),
		RIGHT(90);
		
		Border(int angle) 
		{
	        this.angle = angle;
	    }
		
	    private int angle;

	    public int getAngle() 
	    {
	        return angle;
	    }
	    
	    public Boolean isVertical()
	    {
	    	return this == Border.LEFT || this == Border.RIGHT; 
	    }
	    
	    public Boolean isHorizontal()
	    {
	    	return !isVertical(); 
	    }
	}

	private void generateBorders()
	{
		// maximal decorations count on the top or down border
		final int horizCount = 3;			
		// maximal decorations count on the left or right border
		final int vertCount = 2;
		// area around corners that must not contain decorations
		final int indent = 20;
		
		for (Border border : Border.values())
		{
			int count = random.nextInt((border.isVertical() ? vertCount : horizCount) + 1);
			ArrayList<Entry<Integer, Integer>> result = new ArrayList<Entry<Integer, Integer>>();
			
			for(int i = 0; i < count; i++)
			{
				// really not good way for an offset calculation:
				// code duplication and usage of the setRotation method				
				int segm = (int)(((border.isVertical() ? gridHeight : gridWidth) - 2 * indent) / (float)count);
				int angImg = random.nextInt(borderDecorationImages.size());
				Image img = borderDecorationImages.get(angImg);
				img.setRotation(border.getAngle());
				
				int offset = (int)((segm - img.getWidth()) * random.nextDouble());
				result.add(new AbstractMap.SimpleEntry<Integer, Integer>(angImg, offset));
			}			
			generatedBorderDecorations.put(border, result);
		}		
	}
	
	private void drawBorderDecoration(Graphics g, Border border)
	{				
		// area around corners that must not contain decorations
		final int indent = 20;	

		ArrayList<Entry<Integer, Integer>> decorations = generatedBorderDecorations.get(border);
		int segm = (int)(((border.isVertical() ? gridHeight : gridWidth) - 2 * indent) / (float)decorations.size());
		
		for(int i = 0; i < decorations.size(); i++)		
		{
			Entry<Integer, Integer> decoration = decorations.get(i);
			Image img = borderDecorationImages.get(decoration.getKey());
			img.setRotation(border.getAngle());	
			
			int offset = decoration.getValue();				
			int x = gridOffsetX, y = gridOffsetY;
			
			switch(border)
			{
				case TOP:
					x += segm * i + offset + indent;
					y += -img.getHeight();
					break;
				case RIGHT: 
					x += gridWidth - (img.getWidth() - img.getHeight()) / 2;
					y += segm * i + offset + indent + (img.getWidth() - img.getHeight()) / 2;
					break;
				case BOTTOM: 
					x += segm * i + offset + indent;
					y += gridHeight;
					break;
				case LEFT: 
					x += -img.getHeight() - (img.getWidth() - img.getHeight()) / 2;
					y += segm * i + offset + indent + (img.getWidth() - img.getHeight()) / 2;
					break;
			}

			g.drawImage(img, x, y);
		}
	}
	
	private void processCell(Graphics g, int x, int y)
	{
		int gfW = gfWidth - 1;
		int gfH = gfHeight - 1;

		int id = gameField.getSurfaceAt(x, y);

		if (id == Surface.EMPTY())
			return;

		int luC = 0;
		int ruC = 0;
		int rdC = 0;
		int ldC = 0;

        int cellCount = 0;

		if (x>0 && gameField.getSurfaceAt(x-1, y) == id)
		{
			luC++;
			ldC++;
			cellCount++;
		}
		if (x>0 && y>0 && gameField.getSurfaceAt(x-1, y-1) == id)
		{
			luC++;
			cellCount++;
		}
		if (y>0 && gameField.getSurfaceAt(x, y-1) == id)
		{
			luC++;
			ruC++;
			cellCount++;
		}
		if (x<gfW && y>0 && gameField.getSurfaceAt(x+1, y-1) == id)
		{
			ruC++;
			cellCount++;
		}
		if (x<gfW && gameField.getSurfaceAt(x+1, y) == id)
		{
			ruC++;
			rdC++;
			cellCount++;
		}
		if (x<gfW && y<gfH && gameField.getSurfaceAt(x+1, y+1) == id)
		{
			rdC++;
			cellCount++;
		}
		if (y<gfH && gameField.getSurfaceAt(x, y+1) == id)
		{
			rdC++;
			ldC++;
			cellCount++;
		}
		if (x>0 && y<gfH && gameField.getSurfaceAt(x-1, y+1) == id)
		{
			ldC++;
			cellCount++;
		}

		int pointX = gridOffsetX + cellWidth * x;
		int pointY = gridOffsetY + cellHeight * y;

		if (cellCount == 8)
			drawCenterCell(g, pointX, pointY, id);

		boolean luB = false;
		boolean ruB = false;
		boolean rdB = false;
		boolean ldB = false;
		int cornCount = 0;

		if (luC == 3)
		{
			luB = true;
			cornCount++;
		}
		if (ruC == 3)
		{
			ruB = true;
			cornCount++;
		}
		if (rdC == 3)
		{
			rdB = true;
			cornCount++;
		}
		if (ldC == 3)
		{
			ldB = true;
			cornCount++;
		}

		if (cornCount == 3)
		{
			int cornDir = 0;
			if (!ldB)
				cornDir = 0;
			else if (!luB)
				cornDir = 1;
			else if (!ruB)
				cornDir = 2;
			else if (!rdB)
				cornDir = 3;
			drawAngularCell(g, pointX, pointY, id, INT_ANGLE, cornDir);
		}

		if (cornCount == 2)
		{
			int bordDir = -1;
			if (ruB && rdB)
				bordDir = 0;
			else if (rdB && ldB)
				bordDir = 1;
			else if (luB && ldB)
				bordDir = 2;
			else if (luB && ruB)
				bordDir = 3;
			
			if (bordDir != -1)
				drawSideCell(g, pointX, pointY, id, bordDir);

			int angDir = -1;
			if (luB && rdB)
				angDir = 0;
			else if (ldB && ruB)
				angDir = 1;
			
			if (angDir != -1)
				drawAngularCell(g, pointX, pointY, id, CROSS_ANGLE ,angDir);
		}

		if (cornCount == 1)
		{
			int angDir = 0;
			if (ruB)
				angDir = 0;
			else if (rdB)
				angDir = 1;
			else if (ldB)
				angDir = 2;
			else if (luB)
				angDir = 3;
			
			drawAngularCell(g, pointX, pointY, id, EXT_ANGLE, angDir);
		}

		if (cornCount == 0)
			drawSingleCell(g, pointX, pointY, id);
	}
	
	private void drawCenterCell(Graphics g, int x, int y, int surface)
	{
		int rotation = getCenterImageRotation(x, y); 
		Boolean flipHorizontal = rotation == 1 || rotation == 3;
		Boolean flipVertical = rotation == 2;
		drawReliefCell(g, x, y, surface, CENTER, flipHorizontal, flipVertical);
	}

	private void drawSideCell(Graphics g, int x, int y, int surface, int rotation)
	{
		int directionType = (rotation == 0 || rotation == 2) ? VSIDE : HSIDE;
		Boolean flipHorizontal = rotation == 2;
		Boolean flipVertical = rotation == 3;
		drawReliefCell(g, x, y, surface, directionType, flipHorizontal, flipVertical);
	}
	
	private void drawSingleCell(Graphics g, int x, int y, int surface)
	{
		drawReliefCell(g, x, y, surface, SINGLE, false, false);
	}
	
	private void drawAngularCell(Graphics g, int x, int y, int surface, int angleType, int rotation)	{
		Boolean flipHorizontal = rotation == 2 || rotation == 3;
		Boolean flipVertical = rotation == 1 || rotation == 2;
		drawReliefCell(g, x, y, surface, angleType, flipHorizontal, flipVertical);
	}
	
	
	int getCenterImageRotation(int x, int y)
	{
		final int maxY = 2000;
		int pos = x * maxY + y;
		if(!centerImagesRotation.containsKey(pos))
			centerImagesRotation.put(pos, random.nextInt(4));
		return centerImagesRotation.get(pos);
	}
	
	int getReliefImageIndex(List<Image> imageList, int x, int y)
	{
		final int maxY = 2000;
		int pos = x * maxY + y;
		if(!reliefImagesIndexes.containsKey(pos))
			reliefImagesIndexes.put(pos, random.nextInt(imageList.size()));
		return reliefImagesIndexes.get(pos);
	}
	
	private void drawReliefCell(Graphics g, int x, int y, int surface, int directionType, 
								Boolean flipHorizontal, Boolean flipVertical)
	{
		List<Image> imgList = surfaceMap.get(surface).get(directionType);
		int index = getReliefImageIndex(imgList, x, y);
		Image img = imgList.get(index).getFlippedCopy(flipHorizontal, flipVertical);
		g.drawImage(img, x, y);
	}
}
