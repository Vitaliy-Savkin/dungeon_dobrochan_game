
package ru.dobrochan.dungeon.client.gamestates;

import java.io.File;
import java.util.List;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.state.StateBasedGame;

import ru.dobrochan.dungeon.core.consts.Surface;
import ru.dobrochan.dungeon.content.ResourceManager;
import ru.dobrochan.dungeon.ui.controls.AbstractControl;
import ru.dobrochan.dungeon.ui.controls.Picture;
import ru.dobrochan.dungeon.ui.controls.combined.CreaturesBar;
import ru.dobrochan.dungeon.ui.controls.combined.LogControl;
import ru.dobrochan.dungeon.ui.controls.combined.RootControl;
import ru.dobrochan.dungeon.ui.primitives.Size;
import ru.dobrochan.dungeon.ui.gamefield.*;
import ru.dobrochan.dungeon.core.GameContext;
import ru.dobrochan.dungeon.core.GameField;
import ru.dobrochan.dungeon.core.models.*;
import ru.dobrochan.dungeon.core.views.*;
import ru.dobrochan.dungeon.core.server.*;
import ru.dobrochan.dungeon.core.actions.queries.MoveQuery;
import ru.dobrochan.dungeon.core.actions.AbstractAction;
import ru.dobrochan.dungeon.ui.views.GameProcessView;

/**
 *
 * @author SkinnyMan
 */
public class GameProcessState extends GameState
{

	public GameProcessState(int id) { super(id); }
	
	GameProcessModel model;
	GameProcessView view;
	
	//test: single machine game
	Server server;
	ServerConnector connector;

	private GameField gameField;
	private GameFieldControl gameFieldControl;

	//private IEntityContainer entitiesContainer;

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException
	{
		super.enter(container, game);
		model = new GameProcessModel(gameField, new ru.dobrochan.dungeon.core.units.Skeleton(1, 2, 2));
		view = new GameProcessView(gameFieldControl);
		server = new Server(model);
		connector = new SingleMachineServerConnector(server);

		gameFieldControl.onCellClickedAdd(new CellClickedAction(){
			@Override 
			public void execute(AbstractControl sender, CellClickedEventArgs e) {	
				GameContext context = new GameContext(1, null, model, view);
				List<AbstractAction> actions = 
						connector.SendQuery(new MoveQuery(context.id(), 1, e.getCellX(), e.getCellY()));
				for (AbstractAction action : actions)
					action.execute(context);				
			}				
		});
	}
	
	LogControl logControl;
	Picture btnSettings, btnSave, btnLoad, popupAbility, popupMagic, popupWait;
	CreaturesBar creaturesBar;
	
	@Override
	protected RootControl buildRootControl(GUIContext context) throws SlickException 
	{
		RootControl rootControl = super.buildRootControl(context);	
		
		Font font = new UnicodeFont("fonts"+ File.separator +"GABRIOLA.ttf", 12, false, false);

		gameField = surfaceTest();	// test
		gameFieldControl = new GameFieldControl(context);
		gameFieldControl.setGameField(gameField);

		logControl = new LogControl(context, font);
		
		btnSettings = new Picture(context, buildMenuButtonPicture("BATTLE_SCREEN_ICON_SETTINGS"));
		btnSave = new Picture(context, buildMenuButtonPicture("BATTLE_SCREEN_ICON_SAVE"));
		btnLoad = new Picture(context, buildMenuButtonPicture("BATTLE_SCREEN_ICON_LOAD"));
		
		popupAbility = new Picture(context, ResourceManager.getInstance().getImage("BATTLE_SCREEN_ABILITY"));
		popupMagic = new Picture(context, ResourceManager.getInstance().getImage("BATTLE_SCREEN_MAGIC"));
		popupWait = new Picture(context, ResourceManager.getInstance().getImage("BATTLE_SCREEN_WAIT"));

		creaturesBar = new CreaturesBar(context, font);
		
		rootControl.addChild(gameFieldControl, new Size(10, 10));
		rootControl.addChild(logControl, new Size(0, 15));
		
		int SCREEN_WIDTH = 1280;
		int barX = (SCREEN_WIDTH - creaturesBar.getWidth()) / 2;
		rootControl.addChild(creaturesBar, new Size(barX, getDockedControlYOffset(creaturesBar) + 7));
		
		rootControl.addChild(btnSettings, new Size(100, 
				getDockedControlYOffset(btnSettings)));
		rootControl.addChild(btnSave, new Size(100 + (btnSettings.getWidth() + 10), 
				getDockedControlYOffset(btnSave)));
		rootControl.addChild(btnLoad, new Size(100 + (btnSettings.getWidth() + 10) + (btnSave.getWidth() + 10),  
				getDockedControlYOffset(btnLoad)));
		
		rootControl.addChild(popupAbility, new Size(970, getDockedControlYOffset(popupAbility)));
		rootControl.addChild(popupMagic, new Size(1070, getDockedControlYOffset(popupMagic)));
		rootControl.addChild(popupWait, new Size(1150, getDockedControlYOffset(popupWait)));
		
		controlsTest();
		
		return rootControl;
	}
	
	private void controlsTest() throws SlickException{		
		logControl.addMessage("1ssssss");
		logControl.addMessage("2ssssss");
		logControl.addMessage("3ssssss");
		logControl.addMessage("4ssssss");
		logControl.addMessage("5ssssss");
		logControl.addMessage("6ssssss");
		logControl.addMessage("7ssssss");
		
		creaturesBar.setLabelText("10/15");
		
		creaturesBar.getCreaturesImages().add(
				new Image("Textures\\Sprites\\Creatures\\Archer60.png"));
		creaturesBar.getCreaturesImages().add(
				new Image("Textures\\Sprites\\Creatures\\Buffaloman60.png"));
		creaturesBar.getCreaturesImages().add(
				new Image("Textures\\Sprites\\Creatures\\Archer60.png"));
		creaturesBar.getCreaturesImages().add(
				new Image("Textures\\Sprites\\Creatures\\Buffaloman60.png"));
		creaturesBar.getCreaturesImages().add(
				new Image("Textures\\Sprites\\Creatures\\Archer60.png"));
		creaturesBar.getCreaturesImages().add(
				new Image("Textures\\Sprites\\Creatures\\Buffaloman60.png"));
		creaturesBar.getCreaturesImages().add(
				new Image("Textures\\Sprites\\Creatures\\Archer60.png"));
		creaturesBar.getCreaturesImages().add(
				new Image("Textures\\Sprites\\Creatures\\Buffaloman60.png"));
		creaturesBar.getCreaturesImages().add(
				new Image("Textures\\Sprites\\Creatures\\Archer60.png"));
		creaturesBar.getCreaturesImages().add(
				new Image("Textures\\Sprites\\Creatures\\Buffaloman60.png"));	
	}
	
	private int getDockedControlYOffset(AbstractControl control){
		int SCREEN_HEIGHT = 800;		
		int BORDER_WIDTH = 7;
		return SCREEN_HEIGHT - control.getHeight() - BORDER_WIDTH;
	}
	
	private Image buildMenuButtonPicture(String iconName) throws SlickException{
		Image background = ResourceManager.getInstance().getImage("BATTLE_SCREEN_SHORT_MENU").copy();
		Image icon = ResourceManager.getInstance().getImage(iconName);
		int x = (background.getWidth() - icon.getWidth()) / 2;
		int y = (background.getHeight() - icon.getHeight()) / 2 + 5;
		background.getGraphics().drawImage(icon, x, y);
		background.getGraphics().flush();
		return background;
	}

	private GameField surfaceTest()
	{
		GameFieldBuilder gfb = new GameFieldBuilder();
		gfb.setRect(5, 6, 4, 4, Surface.ROCK());
		gfb.setRect(8, 9, 3, 3, Surface.ROCK());

		gfb.setRect(23, 3, 2, 8, Surface.ROCK());
		gfb.setRect(19, 7, 8, 2, Surface.ROCK());

		gfb.setCell(7, 3, Surface.ROCK());
		gfb.setRect(7, 3, 2, 2, Surface.ROCK());

		gfb.setRect(15, 12, 5, 2, Surface.ROCK());
		gfb.setRect(16, 11, 4, 1, Surface.ROCK());
		gfb.setRect(17, 10, 3, 1, Surface.ROCK());
		gfb.setRect(18, 9, 2, 1, Surface.ROCK());

		gfb.setRect(0, 0, 35, 2, Surface.ROCK());
		gfb.setRect(0, 0, 2, 16, Surface.ROCK());
		gfb.setRect(30, 0, 5, 16, Surface.ROCK());
		gfb.setRect(0, 14, 35, 2, Surface.ROCK());

		gfb.setRect(10, 5, 4, 3, Surface.WATER());
		gfb.clearCell(13, 7);

		gfb.setRect(17, 5, 6, 4, Surface.WATER());
		gfb.setRect(20, 9, 3, 2, Surface.WATER());

		gfb.clearRect(0, 3, 2, 4);
		gfb.clearRect(30, 10, 5, 3);

		return gfb.getBuiltField();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		gameFieldControl.update(delta);
	}
}
