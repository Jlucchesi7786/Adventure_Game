
public class Door {
	public Position pos;
	public Tile space;

	public String orientation;
	boolean locked;

	public Door(int x, int y, String state, String orientation) {
		this(new Position(x, y), state, orientation);
	}

	public Door(Position pos, String state, String orientation) {
		this.orientation = orientation;
		this.pos = pos;
		if (state.equals("unlocked")) {
			space = new Tile(orientation + " door");
			locked = false;
		} else {
			space = new Tile("locked door");
			locked = true;
		}
	}
	
	public Door(int x, int y, String state) {
		this(new Position(x, y), state);
	}

	public Door(Position pos, String state) {
		this.pos = pos;
		if (state.equals("unlocked")) {
			space = new Tile(orientation + " door");
			locked = false;
		} else {
			space = new Tile("locked door");
			locked = true;
		}
	}

	public void unlock() {
		space.update(orientation + " door");
		locked = false;
		System.out.println("you unlocked the door.");
	}

	public void lock() {
		space.update("locked door");
		locked = true;
		System.out.println("you locked the door.");
	}

	public String getState() {
		if (locked) {
			return "locked";
		} else {
			return "unlocked";
		}
	}
	
	public String toString() {
		String s = "Position: " + pos + ", orientation: " + orientation + ", state: ";
		if (locked) {
			s += "locked";
		} else {
			s += "unlocked";
		}
		return s;
	}
}
