package ru.dobrochan.dungeon.ui.primitives;

public class Point {
	
	int x, y;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	static Point empty = new Point(0, 0);

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Point [X=" + x + ", Y=" + y + "]";
	}

	static public Size Substract(Point pt1, Point pt2) {
		return new Size(pt1.x - pt2.x, pt1.y - pt2.y);
	}

	static public Point Add(Point pt, Size sz) {
		return new Point(pt.x + sz.getWidth(), pt.y + sz.getHeight());
	}	
	
	static public boolean inBounds(Point location, Size size, Point test){

		return location.x <= test.x && test.x <= location.x + size.getWidth() &&
			   location.y <= test.y && test.y <= location.y + size.getHeight();
	}
}
