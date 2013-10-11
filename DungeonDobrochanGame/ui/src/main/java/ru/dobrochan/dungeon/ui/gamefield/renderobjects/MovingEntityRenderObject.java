
package ru.dobrochan.dungeon.ui.gamefield.renderobjects;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import ru.dobrochan.dungeon.content.ContentPaths;
import ru.dobrochan.dungeon.content.ResourceManager;
import ru.dobrochan.dungeon.ui.primitives.Point;
import ru.dobrochan.dungeon.ui.gamefield.GameFieldControl;
import ru.dobrochan.dungeon.core.IEntity;

/**
 *
 * @author SkinnyMan
 */
public class MovingEntityRenderObject extends EntityRenderObject
{
	private Point[] path;
	private int currentCell;
	private Image image;
	private double cellX;
	private double cellY;
	private int t;

	public MovingEntityRenderObject(IEntity entity, Point[] path)
	{
		try
		{
			setOwner(entity);
			
			image = ResourceManager.getInstance().loadImage((String)entity.getParam("image"),
				ContentPaths.CREATURES + 
				//(String)entity.getParam("image") 
				"SkeletonWarrior60"
				+ ".png");
			this.path = path;
			currentCell = 0;
			t = 0;
			update(0);
		}
		catch (SlickException ex)
		{
			Logger.getLogger(MovingEntityRenderObject.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public MovingEntityRenderObject(IEntity entity, int fromX, int fromY, int toX, int toY){
		this(entity, new Point[] { new Point(fromX, fromY), new Point(toX, toY) });
	}

	@Override
	public final void update(int delta)
	{
		final int interval = 500;
		t += 1;

		if (t > interval)
		{
			t = 0;
			currentCell++;
		}
		if (currentCell >= path.length - 1)
			return;
		Point current = path[currentCell];
		Point next = path[currentCell + 1];
		double d = (double)t / interval;

		cellX = current.getX() + (next.getX() - current.getX()) * d;
		cellY = current.getY() + (next.getY() - current.getY()) * d;
	}

	@Override
	public void render(Graphics g)
	{
		GameFieldControl gfv = getGameFieldView();
		int imgX = gfv.getX() + (int)(gfv.getCellWidth() * cellX);
		int imgY = gfv.getY() + (int)(gfv.getCellHeight() * cellY);
		g.drawImage(image, imgX, imgY);
	}

}
