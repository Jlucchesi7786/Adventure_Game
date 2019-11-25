/**
 * This class holds some basic information about the chests lying around the dungeon.
 * @author Dr. Cheese
 */
public class Chest {
	public Position pos;

	boolean open; // holds the state of the chest

	Item contents; // the Item inside the chest
	Tile space;

	/**
	 * This constructor makes a new chest at an (x, y) coordinate set upon declaration.
	 * @param x int
	 * @param y int
	 */
	Chest(int x, int y) {
		this(new Position(x,y));
	}

	Chest(Position pos) {
		this.pos = pos;

		open = false; // sets the state of the chest to not open, or closed

		contents = new Item(); // generates a random item to be inside the chest
		space = new Tile("closed chest");
	}

	/**
	 * This method changes the state of the chest from closed to open.
	 */
	public void open() {
		open = true;
		space.update("open chest");
	}

	public String toString() {
		String s = "Position: " + pos + "\nState: ";
		if (open) {
			s += "open";
		} else {
			s += "closed";
		}

		return s;
	}
}
