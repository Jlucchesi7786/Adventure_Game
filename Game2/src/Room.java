/**
 * This class controls the frame that the player is in.
 * @author Dr. Cheese
 */
public class Room extends Compiler {
	int width;
	int height;

	public Room() {
		this(30, 30);
	}
	
	public Room(int width, int height) {
		this(width, height, "down");
	}
	
	/**
	 * This constructor makes a room with some given width and some given height. 
	 * The playable area is a square/rectangle in the middle, with the walls 
	 * taking up one space on each side.
	 * @param width What width the playable area should be at base (int).
	 * @param height What height the playable area should be at base (int).
	 */
	public Room(int width, int height, String orientation) {
		super(width+2, height+2, new Player());
		this.width = width+2;
		this.height = height+2;
	}

	public Room(int width, int height, Chest[] chests, Wall[] walls, Door[] doors) {
		super(width+2, height+2, new Player(), chests, walls, doors);
		this.width = width+2;
		this.height = height+2;
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
}
