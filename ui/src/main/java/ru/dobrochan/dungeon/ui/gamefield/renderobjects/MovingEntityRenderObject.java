
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
import ru.dobrochan.dungeon.core.Entity;

/**
 *
 * @author SkinnyMan
 */



public class MovingEntityRenderObject extends EntityRenderObject
{
    private Image image;
	private double cellX;
	private double cellY;
	private int t;
    private boolean firstUpdate = true;
    public Runnable started;
    public Runnable finished;
    private Point from;
    private Point to;

	public MovingEntityRenderObject(Entity entity, Point from, Point to)
	{
		try
		{
            this.from = from;
            this.to = to;
			setOwner(entity, from); // TODO: make sure 'from' is a logically correct value
			
			image = ResourceManager.getInstance().loadImage("1",
			//(String)entity.getParam("image"),
				ContentPaths.CREATURES + 
				//(String)entity.getParam("image") 
				"SkeletonWarrior60"
				+ ".png");
		}
		catch (SlickException ex)
		{
			Logger.getLogger(MovingEntityRenderObject.class.getName()).log(Level.SEVERE, null, ex);
		}
	}


	@Override
	public final void update(int delta)
	{
		final int interval = 200;
        if (firstUpdate){
            firstUpdate = false;
            t = 0;
            if(started != null)
                started.run();
        }
		t += 1;
		if (t > interval)
		{
            if(finished != null)
                finished.run();
		}
		double d = (double)t / interval;

		cellX = from.getX() + (to.getX() - from.getX()) * d;
		cellY = from.getY() + (to.getY() - from.getY()) * d;
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
