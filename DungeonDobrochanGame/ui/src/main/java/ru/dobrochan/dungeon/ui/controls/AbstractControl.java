package ru.dobrochan.dungeon.ui.controls;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;
import ru.dobrochan.dungeon.ui.primitives.*;
import ru.dobrochan.dungeon.ui.events.*;

public abstract class AbstractControl extends AbstractComponent {

	private Point location;//of the left upper corner
	protected IControlContainer parent;	
	protected boolean disabled = false, visible = true, hovered = false, pressed = false;
	
	public IControlContainer getParent(){
		return parent;
	}
	
	public boolean isDisabled() {
		return disabled;
	}	
	
	public boolean isEnabled() {
		return !disabled;
	}

	public void setEnabled(boolean enabled) {
		this.disabled = !enabled;
	}	

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean inBounds(Point point)
	{
		return Point.inBounds(getLocation(), getSize(), point);
	}
	
	public boolean inBounds(int x, int y)
	{
		return inBounds(new Point(x, y));
	}	
	
	public AbstractControl(GUIContext container) {
		super(container);
		
		location = new Point(0, 0);
		
		onMouseMovedAdd(new MouseMovedAction() {
			@Override
			public void execute(AbstractControl sender, MouseMovedEventArgs e)
			{
				sender.hovered = sender.inBounds(e.getNewLocation());
				sender.pressed &= sender.hovered;
			}
		});
		
		onMousePressedAdd(new MouseButtonAction() {
			@Override
			public void execute(AbstractControl sender, MouseButtonEventArgs e)
			{
				sender.hovered = sender.inBounds(e.getLocation());
				sender.pressed = sender.hovered;
			}
		});
		
//		onMouseReleasedAdd(new MouseButtonAction() {
//			@Override
//			public void execute(AbstractControl sender, MouseButtonEventArgs e)
//			{
//				sender.pressed = false;
//			}
//		});
	}	

	@Override
	public int getX() {
		return location.getX();
	}

	@Override
	public int getY() {
		return location.getY();
	}

	public Point getLocation() {
		return location;
	}

	@Override
	public void setLocation(int x, int y) {
		this.setLocation(new Point(x, y));
	}
	
	public void setLocation(Point location) {
		this.location = location;
	}
	
	public Size getSize(){
		return new Size(getWidth(), getHeight());
	}
	
	public void removeFromParent() {
		if (parent != null)
		{
			parent.removeChild(this);
			parent = null;
		}
	}
	
	public boolean isAcceptingInput() 
	{ 
		return !disabled && (parent == null || parent.isAcceptingInput()) && super.isAcceptingInput(); 
	}		
	
	/* Implementation of the events like in Windows Forms.
	 * Interaction should be performed via actions that were set using 
	 * onEventNameAdd / onEventNameRemove methods.
	 */
	
	private EventHandler<KeyAction, KeyEventArgs> keyPressedHandler = new EventHandler<KeyAction, KeyEventArgs>();
	private EventHandler<KeyAction, KeyEventArgs> keyReleasedHandler = new EventHandler<KeyAction, KeyEventArgs>();
	private EventHandler<MouseMovedAction, MouseMovedEventArgs> mouseDraggedHandler = new EventHandler<MouseMovedAction, MouseMovedEventArgs>();
	private EventHandler<MouseMovedAction, MouseMovedEventArgs> mouseMovedHandler = new EventHandler<MouseMovedAction, MouseMovedEventArgs>();
	private EventHandler<MouseClickedAction, MouseClickedEventArgs> mouseClickedHandler = new EventHandler<MouseClickedAction, MouseClickedEventArgs>();
	private EventHandler<MouseButtonAction, MouseButtonEventArgs> mousePressedHandler = new EventHandler<MouseButtonAction, MouseButtonEventArgs>();
	private EventHandler<MouseButtonAction, MouseButtonEventArgs> mouseReleasedHandler = new EventHandler<MouseButtonAction, MouseButtonEventArgs>();
	private EventHandler<MouseWheelAction, MouseWheelEventArgs> mouseWheelMovedHandler = new EventHandler<MouseWheelAction, MouseWheelEventArgs>();	
	
    public void onKeyPressedAdd(KeyAction action)
	{
		keyPressedHandler.addAction(action);
	}
	
	public void onKeyPressedRemove(KeyAction action)
	{
		keyPressedHandler.removeAction(action);
	}

    public void onKeyReleasedAdd(KeyAction action)
	{
		keyReleasedHandler.addAction(action);
	}
	
	public void onKeyReleasedRemove(KeyAction action)
	{
		keyReleasedHandler.removeAction(action);
	}

    public void onMouseDraggedAdd(MouseMovedAction action)
	{
		mouseDraggedHandler.addAction(action);
	}
	
	public void onMouseDraggedRemove(MouseMovedAction action)
	{
		mouseDraggedHandler.removeAction(action);
	}

    public void onMouseMovedAdd(MouseMovedAction action)
	{
		mouseMovedHandler.addAction(action);
	}
	
	public void onMouseMovedRemove(MouseMovedAction action)
	{
		mouseMovedHandler.removeAction(action);
	}

    public void onMouseClickedAdd(MouseClickedAction action)
	{
		mouseClickedHandler.addAction(action);
	}
	
	public void onMouseClickedRemove(MouseClickedAction action)
	{
		mouseClickedHandler.removeAction(action);
	}

    public void onMousePressedAdd(MouseButtonAction action)
	{
		mousePressedHandler.addAction(action);
	}
	
	public void onMousePressedRemove(MouseButtonAction action)
	{
		mousePressedHandler.removeAction(action);
	}

    public void onMouseReleasedAdd(MouseButtonAction action)
	{
		mouseReleasedHandler.addAction(action);
	}
	
	public void onMouseReleasedRemove(MouseButtonAction action)
	{
		mouseReleasedHandler.removeAction(action);
	}

    public void onMouseWheelMovedAdd(MouseWheelAction action)
	{
		mouseWheelMovedHandler.addAction(action);
	}
	
	public void onMouseWheelMovedRemove(MouseWheelAction action)
	{
		mouseWheelMovedHandler.removeAction(action);
	}
	
	public final void keyPressed(int key, char c)	{
		keyPressedHandler.invoke(this, new KeyEventArgs(key, c));
	}
	
	public final void keyReleased(int key, char c) 	{
		keyReleasedHandler.invoke(this, new KeyEventArgs(key, c));
	}
	
	public final void mouseClicked(int button, int x, int y, int clickCount){
		if(inBounds(x, y))
			mouseClickedHandler.invoke(this, new MouseClickedEventArgs(button, x, y, clickCount));		
	}
	
	public final void mouseDragged(int oldx, int oldy, int newx, int newy) {
		if(inBounds(oldx, oldy) || inBounds(newx, newy))
			mouseDraggedHandler.invoke(this, new MouseMovedEventArgs(oldx, oldy, newx, newy));
	}
	
	public final void mouseMoved(int oldx, int oldy, int newx, int newy) {
		if(inBounds(oldx, oldy) || inBounds(newx, newy))
			mouseMovedHandler.invoke(this, new MouseMovedEventArgs(oldx, oldy, newx, newy));		
	}
	
	public final void mousePressed(int button, int x, int y) {
		if(inBounds(x, y))
			mousePressedHandler.invoke(this, new MouseButtonEventArgs(button, x, y));		
	}
	
	public final void mouseReleased(int button, int x, int y) {
		if(inBounds(x, y))
			mouseReleasedHandler.invoke(this, new MouseButtonEventArgs(button, x, y));		
	}
	
	public final void mouseWheelMoved(int change){
		mouseWheelMovedHandler.invoke(this, new MouseWheelEventArgs(change));	
	}
}
















