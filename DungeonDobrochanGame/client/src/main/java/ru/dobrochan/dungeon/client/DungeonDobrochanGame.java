package ru.dobrochan.dungeon.client;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import ru.dobrochan.dungeon.content.ResourceManager;
import ru.dobrochan.dungeon.content.Settings;
//import ru.dobrochan.dungeon.core.IEntity;
//import ru.dobrochan.dungeon.core.scripts.CalcScriptHandler;
//import ru.dobrochan.dungeon.core.scripts.ConstsScriptHandler;
//import ru.dobrochan.dungeon.core.scripts.UnitScriptHandler;
//import ru.dobrochan.dungeon.core.xmlloader.UnitLoader;
import ru.dobrochan.dungeon.client.gamestates.GamePoolState;
import ru.dobrochan.dungeon.client.gamestates.GameProcessState;
import ru.dobrochan.dungeon.client.gamestates.MainMenuState;

/**
 *
 * @author SkinnyMan
 */
public class DungeonDobrochanGame extends StateBasedGame
{
	public static final int MAIN_MENU_STATE = 0;
	public static final int GAME_POOL_STATE = 1;
	public static final int GAME_PROCESS_STATE = 2;

	public DungeonDobrochanGame()
	{
		super("Dungeon");
	}

    @Override
    public void initStatesList(GameContainer container)
			throws SlickException
	{
		InputStream is = null;
		try
		{
			is = new FileInputStream("Resources.xml");
			ResourceManager.getInstance().loadResources(is, true);
			MainMenuState menu = new MainMenuState(MAIN_MENU_STATE);
			GameProcessState game = new GameProcessState(GAME_PROCESS_STATE);
			GamePoolState pool = new GamePoolState(GAME_POOL_STATE);
			addState(menu);
			addState(game);
			addState(pool);
			enterState(MAIN_MENU_STATE);
		}
		catch (FileNotFoundException ex)
		{
			Logger.getLogger(DungeonDobrochanGame.class.getName()).log(Level.SEVERE, null, ex);
		}
		finally
		{
			try
			{
				is.close();
			}
			catch (IOException ex)
			{
				Logger.getLogger(DungeonDobrochanGame.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

    }


	public static void main(String[] args)
    {
		try
		{
			//Test();
			AppGameContainer app = new AppGameContainer(new DungeonDobrochanGame());
			app.setDisplayMode(Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT, false);
			app.setUpdateOnlyWhenVisible(false);
			app.setAlwaysRender(true);
			app.start();
		}
		catch(SlickException e)
		{
			e.printStackTrace();
		}
    }

//	private static void Test()
//	{
//		String str = "";
//
//		if (str.isEmpty())
//			System.out.format("String %s", "is empty.");
//		if (str == null)
//			System.out.format("String %s", "is null.");
//
//
//		InputStream is = null;
//		try
//		{
//			is = new FileInputStream("Units.xml");
//			//UnitLoader ul = new UnitLoader();
//			//ul.loadUnits(is);
//		}
//		catch (FileNotFoundException | SlickException ex)
//		{
//			Logger.getLogger(DungeonDobrochanGame.class.getName()).log(Level.SEVERE, null, ex);
//		}
//
//	}
}
