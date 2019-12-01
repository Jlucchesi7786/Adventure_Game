/**
 * This class holds some basic information about the chests lying around the dungeon.
 * @author Dr. Cheese
 */
public class Chest {
	public Position pos;

	boolean open; // holds the state of the chest

	Item contents; // the Item inside the chest
	Tile space;

	public Chest() {
		this.pos = new Position();
		open = false;
		contents = new Item();
		space = new Tile("closed chest");
	}
	
	Chest(Position pos) {
		this();
		this.pos = pos;
	}
	
	Chest(Item drop, Position pos) {
		this();
		this.pos = pos;
		this.contents = drop;
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
