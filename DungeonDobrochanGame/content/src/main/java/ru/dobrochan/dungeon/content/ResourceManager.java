
package ru.dobrochan.dungeon.content;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.lwjgl.input.Cursor;
import org.newdawn.slick.*;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.opengl.CursorLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/** Представляет средства для работы с ресурсами. */
public class ResourceManager {

	private static String SPRITE_SHEET_REF = "__SPRITE_SHEET_";

	// Синглтон.
	private static ResourceManager _instance = new ResourceManager();

	private Map<String, Music> soundMap;
	private Map<String, Image> imageMap;
	private Map<String, ResourceAnimationData> animationMap;
	private Map<String, Cursor> cursorMap;
	private Map<String, String> textMap;
	private Map<String, List<Image>> imageListMap;

	/** Рабочая директория приложения. */
	public static final String PATH = System.getProperty("user.dir") + File.separator;

	private ResourceManager(){
		soundMap  = new HashMap<String, Music>();
		imageMap = new HashMap<String, Image>();
		animationMap = new HashMap<String, ResourceAnimationData>();
		cursorMap = new HashMap<String, Cursor>();
		textMap = new HashMap<String, String>();
		imageListMap = new HashMap<String, List<Image>>();
	}

	/** Возвращает единственный экземпляр класса ResourceManager. */
	public static ResourceManager getInstance(){
		return _instance;
	}

	/**
	 * Загружает ресурсы из указанного потока.
	 *
	 * @param is входной поток, содержащий XML с данными о ресурсах.
	 * @throws SlickException указывает, что произошла ошибка при загрузке ресурсов.
	 */
	public void loadResources(InputStream is) throws SlickException {
		loadResources(is, false);
	}

	/**
	 * Загружает ресурсы из указанного потока с возможность отложеной загрузки ресурсов.
	 *
	 * @param is входной поток, содержащий XML с данными о ресурсах.
	 * @param deferred указывает, будет ли загрузка отложена до превого использования ресурса.
	 * @throws SlickException указывает, что произошла ошибка при загрузке ресурсов.
	 */
	public void loadResources(InputStream is, boolean deferred) throws SlickException {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new SlickException("Could not load resources", e);
		}
		Document doc = null;
        try {
			doc = docBuilder.parse (is);
		} catch (Exception e) {
			throw new SlickException("Could not load resources", e);
		}

		// Нормализует представление текста.
        doc.getDocumentElement().normalize ();

        NodeList listResources = doc.getElementsByTagName("resource");

        int totalResources = listResources.getLength();

        if(deferred){
        	LoadingList.setDeferredLoading(true);
        }

        for(int resourceIdx = 0; resourceIdx < totalResources; resourceIdx++)
		{

        	Node resourceNode = listResources.item(resourceIdx);

        	if(resourceNode.getNodeType() == Node.ELEMENT_NODE){
        		Element resourceElement = (Element)resourceNode;

        		String type = resourceElement.getAttribute("type");

				switch (type)
				{
					case "image":
						addElementAsImage(resourceElement);
						break;
					case "sound":
						addElementAsSound(resourceElement);
						break;
					case "text":
						addElementAsText(resourceElement);
						break;
					case "font":
						break;
					case "animation":
						addElementAsAnimation(resourceElement);
						break;
					case "cursor":
						addElementAsCursor(resourceElement);
						break;
					case "imagelist":
						addElementAsImageList(resourceElement);
						break;
				}
			}
		}
	}

	/**
	 *
	 *
	 * @param resourceElement
	 * @throws SlickException
	 */
	private void addElementAsAnimation(Element resourceElement) throws SlickException
	{
		loadAnimation(resourceElement.getAttribute("id"), resourceElement.getTextContent(),
				Integer.valueOf(resourceElement.getAttribute("tw")),
				Integer.valueOf(resourceElement.getAttribute("th")),
				Integer.valueOf(resourceElement.getAttribute("duration")));
	}

	private void loadAnimation(String id, String spriteSheetPath,
			int tw, int th, int duration) throws SlickException
	{
		if(spriteSheetPath == null || spriteSheetPath.length() == 0)
			throw new SlickException("Image resource [" + id + "] has invalid path");

		spriteSheetPath = PATH + spriteSheetPath;
		loadImage( SPRITE_SHEET_REF + id, spriteSheetPath);

		animationMap.put(id, new ResourceAnimationData(SPRITE_SHEET_REF+id, tw, th, duration));
	}

	public final Animation getAnimation(String ID)
	{
		ResourceAnimationData rad = animationMap.get(ID);

		SpriteSheet spr = new SpriteSheet(getImage(rad.getImageId()), rad.tw, rad.th);

		Animation animation = new Animation(spr, rad.duration);

		return animation;
	}

	private void addElementAsText(Element resourceElement) throws SlickException
	{
		loadText(resourceElement.getAttribute("id"), resourceElement.getTextContent());
	}

	/**
	 * Загружает строковой русурс.
	 *
	 * @param id идентефикатор ресурса
	 * @param value строка ресурса
	 * @return строку ресурса
	 * @throws SlickException ошибка при загрузке ресурса, value не может быть null
	 */
	public String loadText(String id, String value) throws SlickException
	{
		if(value == null)
			throw new SlickException("Text resource [" + id + "] has invalid value");

		textMap.put(id, value);

		return value;
	}

	/**
	 * Возвращает строковой ресурс по указанному id.
	 *
	 * @param id идентификатор ресурса
	 * @return струку ресурса
	 */
	public String getText(String id)
	{
		return textMap.get(id);
	}

	private void addElementAsSound(Element resourceElement) throws SlickException
	{
		loadSound(resourceElement.getAttribute("id"), resourceElement.getTextContent());
	}

	/**
	 * Загружает звуковой ресурс.
	 *
	 * @param id идентификатор ресурса
	 * @param path путь к файлу
	 * @return загруженный звук
	 * @throws SlickException ошибка при загрузке ресурса, недопустимый path
	 */
	public Music loadSound(String id, String path) throws SlickException
	{
		if(path == null || path.length() == 0)
			throw new SlickException("Sound resource [" + id + "] has invalid path");

		Music music = null;
		path = PATH + path;
		music = new Music(path);

		this.soundMap.put(id, music);

		return music;
	}

	/**
	 * Возвращает звуковой ресурс по указанному id.
	 *
	 * @param id идентификатор ресурса
	 * @return звуковой ресурс
	 */
	public final Music getSound(String id){
		return soundMap.get(id);
	}

	private void addElementAsImage(Element resourceElement) throws SlickException
	{
		loadImage(resourceElement.getAttribute("id"), resourceElement.getTextContent());
	}

	/**
	 * Загружает изображения по указанному id.
	 *
	 * @param id идентификатор ресурса
	 * @param path путь к файлу
	 * @return изображение
	 * @throws SlickException ошибка при загрузке ресурса
	 */
	public Image loadImage(String id, String path) throws SlickException
	{
		if(path == null || path.length() == 0)
			throw new SlickException("Image resource [" + id + "] has invalid path");

		Image image = null;
		try{
			path = PATH + path;
			image = new Image(path);
		} catch (SlickException e) {
			throw new SlickException("Could not load image", e);
		}

		this.imageMap.put(id, image);

		return image;
	}

	/**
	 * Возвращает изображение по указанному id.
	 *
	 * @param id идентификатор ресурса
	 * @return изображение
	 */
	public final Image getImage(String id){
		return imageMap.get(id);
	}

	private void addElementAsImageList(Element resourceElement) throws SlickException
	{
		loadImageList(resourceElement.getAttribute("id"), resourceElement.getTextContent());
	}

	/**
	 * Загружает список изображений. Список формируется из изображений удовлетваряющих указанному паттерну.
	 *
	 * @param id идентефикатор ресурса
	 * @param pattern шаблон имени (должна быть одна и только одна звездочка)
	 * @return список изображений
	 * @throws SlickException ошибка при загрузке ресурса
	 */
	public List<Image> loadImageList(String id, String pattern) throws SlickException
	{
		if(pattern == null || pattern.length() == 0)
			throw new SlickException("ImageList resource [" + id + "] has invalid pattern");

		// Разбор паттерна.
		int ps = pattern.lastIndexOf("\\");
		String path = pattern.substring(0, ps);
		String patternStr = pattern.substring(ps+1);
		File f = new File(PATH + path);
		String[] list = f.list();
		String[] split = patternStr.split("\\*");

		if (split.length != 2)
			throw new SlickException("ImageList resource [" + id + "] has invalid pattern");

		ArrayList<Image> result = new ArrayList<Image>();
		Image image;
		// Поиск файлов, удовлетворяющих паттерну.
		for (String file : list)
		{
			if (file.startsWith(split[0]) && file.endsWith(split[1]))
				try
				{
					String imagePath = PATH + path + File.separator + file;
					image = new Image(imagePath);
					result.add(image);
				} catch (SlickException e) {
					throw new SlickException("Could not load ImageList", e);
				}
		}

		imageListMap.put(id, result);

		return result;
	}

	/**
	 * Возвращает список изображений по указанному id.
	 *
	 * @param id идентефикатор ресурса
	 * @return список изображений
	 */
	public final List<Image> getImageList(String id) {
		return imageListMap.get(id);
	}

	private void addElementAsCursor(Element resourceElement) throws SlickException
	{
		loadCursor(resourceElement.getAttribute("id"), resourceElement.getTextContent(),
				Integer.valueOf(resourceElement.getAttribute("hotSpotX")),
				Integer.valueOf(resourceElement.getAttribute("hotSpotY")));
	}

	/**
	 * Загружает курсор.
	 *
	 * @param id идентефикатор ресурса
	 * @param path путь к файлу
	 * @param hotSpotX координата X hotSpot курсора
	 * @param hotSpotY координата Y hotSpot курсора
	 * @return курсор
	 * @throws SlickException ошибка при загрузке ресурса
	 */
	public Cursor loadCursor(String id, String path, int hotSpotX, int hotSpotY) throws SlickException
	{
		if(path == null || path.length() == 0)
			throw new SlickException("Cursor resource [" + id + "] has invalid path");

		Cursor cursor = null;
		try
		{
			path = PATH + path;
			cursor = CursorLoader.get().getCursor(path, hotSpotX, hotSpotY);
		}
		catch (Exception e)
		{
			throw new SlickException("Could not load cursor", e);
		}

		this.cursorMap.put(id, cursor);
		return cursor;
	}

	/**
	 * Возвращает курсор по указанному id.
	 *
	 * @param id идентефикатор ресурса
	 * @return курсор
	 */
	public final Cursor getCursor(String id)
	{
		return cursorMap.get(id);
	}

	private class ResourceAnimationData{
		int duration;
		int tw;
		int th;
		String imageId;

		public ResourceAnimationData(String id, int tw, int th, int duration){
			this.imageId = id;
			this.tw = tw;
			this.th = th;
			this.duration = duration;
		}

//		public final int getDuration() {
//			return duration;
//		}
//		public final void setDuration(int duration) {
//			this.duration = duration;
//		}
//		public final int getTw() {
//			return tw;
//		}
//		public final void setTw(int tw) {
//			this.tw = tw;
//		}
//		public final int getTh() {
//			return th;
//		}
//		public final void setTh(int th) {
//			this.th = th;
//		}
//		public final void setImageId(String imageId) {
//			this.imageId = imageId;
//		}
		public final String getImageId() {
			return imageId;
		}
	}
}