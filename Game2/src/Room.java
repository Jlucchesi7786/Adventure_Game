/**
 * This class controls the frame that the player is in.
 * @author Dr. Cheese
 */
public class Room extends Compiler {
	int width;
	int height;

	String orientation;

	public Room() {
		this(30, 30);
	}
	
	public Room(int width, int height) {
		this(width, height, "down", new Player());
	}
	
	/**
	 * This constructor makes a room with some given width and some given height. 
	 * The playable area is a square/rectangle in the middle, with the walls 
	 * taking up one space on each side.
	 * @param width What width the playable area should be at base (int).
	 * @param height What height the playable area should be at base (int).
	 * @param orientation Still not sure what to do with this.
	 */
	public Room(int width, int height, String orientation, Player player) {
		super(width+2, height+2, player);
		this.width = width+2;
		this.height = height+2;
		this.orientation = orientation; // orientation tells it where the entrance is
	}

	public Room(int width, int height, String orientation, Player player, Chest[] chests, Wall[] walls, Door[] doors) {
		super(width+2, height+2, player, chests, walls, doors);
		this.orientation = orientation;
	}

	/**
	 * This toString() method constructs a string with all of the symbols and 
	 * the empty space in the frame.
	 */
	public String toString() {
		String s = "";

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				s += map[x][y].character + " ";

				if (x == width-1) {
					s += "\n";
				}
			}
		}

		return s;
	}

	/**
	 * This method "compresses" the room's stored arrays (chests[], walls[], etc.) down 
	 * to one string then returns it. I'm not sure if it will be useful, but that's what
	 * we're going with right now.
	 * @return The compressed room in String format.
	 */
	public String compress() {
		String s = "";
		s += "[width: " + this.width + ", height: " + this.height + "]*[chests: ";
		for (int i = 0; i < getChests().length; i++) {
			s += "(state: ";
			if (getChests()[i].open == true) {
				s += "open";
			} else {
				s += "closed";
			}
			s += "; position: " + getChests()[i].pos + "; contents: " + getChests()[i].contents.name
					+ ")";
			if (i < getChests().length - 1) {
				s += " | ";
			}
		}
		
		s += "]*[walls: ";
		for (int i = 0; i < getWalls().length; i++) {
			s += "(starting position: " + getWalls()[i].startPos + "; length: "
					+ getWalls()[i].length + "; orientation: ";
			if (getWalls()[i].horizontal == true) {
				s += "horizontal";
			} else {
				s += "vertical";
			}
			s += ")";
			if (i < getWalls().length - 1) {
				s += " | ";
			}
		}
		
		s += "]*[doors: ";
		for (int i = 0; i < getDoors().length; i++) {
			s += "(position: " + getDoors()[i].pos + "; orientation: " + getDoors()[i].orientation
					+ "; state: ";
			if (getDoors()[i].locked == true) {
				s += "locked";
			} else {
				s += "unlocked";
			}
			s += ")";
			if (i < getDoors().length - 1) {
				s += " | ";
			}
		}
		s += "]";

		return s;
	}
}
