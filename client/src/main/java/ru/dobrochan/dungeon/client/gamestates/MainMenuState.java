package ru.dobrochan.dungeon.client.gamestates;

import java.io.File;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.GUIContext;
import ru.dobrochan.dungeon.client.DungeonDobrochanGame;
import ru.dobrochan.dungeon.ui.controls.AbstractControl;
import ru.dobrochan.dungeon.ui.controls.combined.MainMenu;
import ru.dobrochan.dungeon.ui.controls.combined.RootControl;
import ru.dobrochan.dungeon.ui.events.MouseClickedAction;
import ru.dobrochan.dungeon.ui.events.MouseClickedEventArgs;
import ru.dobrochan.dungeon.ui.primitives.Size;

/**
 *
 * @author SkinnyMan
 */
public class MainMenuState extends GameState
{
	public MainMenuState(int id)
	{
		super(id);
	}
	
	@SuppressWarnings("unchecked")
	private Font makeMenuFont() throws SlickException
	{
		String fontPath = "fonts"+ File.separator +"GABRIOLA.ttf";
		UnicodeFont uFont = new UnicodeFont(fontPath , 20, false, false); //Create Instance
		uFont.addAsciiGlyphs();   //Add Glyphs
		//uFont.addGlyphs(0, 65535); //Add Glyphs
		//uFont.addGlyphs("Привет");

		uFont.addGlyphs(0x400, 0x4ff); //Add rus Glyphs
		uFont.getEffects().add(new ColorEffect(java.awt.Color.WHITE)); //Add Effects
		uFont.loadGlyphs();  //Load Glyphs
		return uFont;
	}

	@Override
	protected RootControl buildRootControl(GUIContext context) throws SlickException 
	{
		RootControl rootControl = super.buildRootControl(context);		
		MainMenu menu = new MainMenu(context, makeMenuFont());
		menu.addButton("Start game", new MouseClickedAction()
		{
			@Override
			public void execute(AbstractControl sender, MouseClickedEventArgs e) {
				game.enterState(DungeonDobrochanGame.GAME_PROCESS_STATE);
			}
		});
		rootControl.addChild(menu, new Size(10, 10));	
		return rootControl;
	}
}
