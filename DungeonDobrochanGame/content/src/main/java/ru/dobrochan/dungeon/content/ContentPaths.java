
package ru.dobrochan.dungeon.content;

import java.io.File;

/**
 * Содержит пути к основным рабочим деректориям.
 * (В будующем и деректории будут указыватся в XML-файле ресурсов.)
 *
 * @author SkinnyMan
 */
public class ContentPaths
{
	private final static String fs = File.separator;

	public final static String SCRIPTS = fs + "Scripts" + fs;

	public final static String TEXTURE = fs + "Textures" + fs;
	public final static String BACK = TEXTURE + "Back" + fs;
	public final static String INTERFACE = TEXTURE + "Interface" + fs;
	public final static String MAIN_MENU = TEXTURE + "MainMenu" + fs;
	public final static String SPRITES = TEXTURE + "Sprites" + fs;

	public final static String COLORS_LIGHT_BACK = INTERFACE + "ColorsLightBack" + fs;
	public final static String BUTTON = INTERFACE + "Button" + fs;
	public final static String CURSORS = INTERFACE + "Cursors" + fs;

	public final static String CREATURES = SPRITES + "Creatures" + fs;



	private ContentPaths() { }
}
