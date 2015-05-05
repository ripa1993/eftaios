package it.polimi.ingsw.cg_8.model.sectors;

public abstract class Coordinate {
	final int x;
	final int y;
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Coordinate (int x, int y){
		this.x=x;
		this.y=y;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Coordinate [x=" + x + ", y=" + y + "]";
	}
}
