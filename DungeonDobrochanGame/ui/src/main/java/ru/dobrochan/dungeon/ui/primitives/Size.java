package ru.dobrochan.dungeon.ui.primitives;

public class Size {
	int w, h;

	public Size(int w, int h) {
		super();
		this.w = w;
		this.h = h;
	}

	public int getWidth() {
		return w;
	}

	public int getHeight() {
		return h;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + h;
		result = prime * result + w;
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
		Size other = (Size) obj;
		if (h != other.h)
			return false;
		if (w != other.w)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Size [Width=" + w + ", Height=" + h + "]";
	}
}
