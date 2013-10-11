
package ru.dobrochan.dungeon.client.gamestates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ru.dobrochan.dungeon.client.DungeonDobrochanGame;
import ru.dobrochan.dungeon.content.ResourceManager;
import ru.dobrochan.dungeon.ui.controls.combined.RootControl;
/**
 *
 * @author SkinnyMan
 */
public abstract class GameState extends BasicGameState
{
	private int id;
	protected RootControl rootControl;
	protected DungeonDobrochanGame game;

	protected GameState(int id)
	{
		this.id = id;
	}
	
	protected RootControl buildRootControl(GUIContext context) throws SlickException
	{		
		Image background = ResourceManager.getInstance().getImage("WINDOW_BACKGROUND");
		Image windowBorder = ResourceManager.getInstance().getImage("WINDOW_BORDER");
		return new RootControl(context, background, windowBorder);
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		rootControl = buildRootControl(container);	
		this.game = (DungeonDobrochanGame)game;
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException
	{
		rootControl.setAcceptingInput(true);
	}
	
	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException
	{
		rootControl.setAcceptingInput(false);
	}
	
	@Override
	public final int getID()
	{
		return id;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		rootControl.render(container, g);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {	
	}
}
