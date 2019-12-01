/**
 * This class is essentially an entire section of the dungeon, or an entire floor. While 
 * the class' name is "Room", it really represents several small rooms at a time. At 
 * initialization, the constructor takes in a width, height, and arrays of the contents of
 * the room and sends it to its parent class, the Compiler, to compile everything into one
 * array of tiles: the map[][] array. The Room class then uses the array to print out the 
 * section/floor to the console, using its modified toString() method.
 * @author Dr. Cheese
 */
public class Room extends Compiler {
	int width;
	int height;
	private Position spawnPos;

	private static final Position defaultSpawnPos = new Position(1, 2);
	
	private static final Chest[] defaultChests = {new Chest(new Position(1, 30)), 
		new Chest(new Position(30, 19)), new Chest(new Position(1, 1)), 
		new Chest(new Position(30 ,2)), new Chest(new Position(5, 5)), 
		new Chest(new Position(26, 15))};

	private static final Wall[] defaultWalls = {new Wall("vertical", 30, new Position(6, 1)), 
		new Wall("horizontal", 5, new Position(1, 4)), new Wall("horizontal", 25, new Position(6, 14)), 
		new Wall("vertical", 30, new Position(25, 1)), new Wall("horizontal", 6, new Position(25, 18)), 
		new Wall("horizontal", 6, new Position(0, 20)), new Wall("horizontal", 32, new Position(0, 0)),
		new Wall("horizontal", 32, new Position(0, 31)), new Wall("vertical", 32, new Position(0, 0)),
		new Wall("vertical", 32, new Position(31, 0))};

	private static final Door[] defaultDoors = {new Door(new Position(6, 2), "unlocked", "vertical"), 
		new Door(new Position(25, 10), "unlocked", "vertical"), new Door(new Position(2, 4), "unlocked", "horizontal"), 
		new Door(new Position(6, 16), "unlocked", "vertical"), new Door(new Position(6, 22), "unlocked", "vertical"), 
		new Door(new Position(26, 18), "unlocked", "horizontal"), new Door(new Position(25, 27), "unlocked", "vertical"),
		new Door(new Position(6, 11), "unlocked", "vertical")};

	/**
	 * The base constructor for the Room class gives it a width and 
	 * height only, then calls the constructor that takes in only two
	 * ints.
	 */
	public Room() {
		this(32, 32);
	}
	
	/**
	 * This constructor takes in a width and height, then runs the 
	 * biggest constructor using the default arrays provided inside the
	 * class.
	 * @param width the width of the entire room (including outside walls)
	 * @param height the height of the entire room (including outside walls)
	 */
	public Room(int width, int height) {
		this(width, height, defaultChests, defaultWalls, defaultDoors);
		this.spawnPos = defaultSpawnPos;
	}
	
	public Room(int width, int height, Position spawnPos) {
		this(width, height, defaultChests, defaultWalls, defaultDoors);
		this.spawnPos = spawnPos;
	}

	/**
	 * This constructor takes in a width, height, and several arrays to get 
	 * all information to the Compiler. 
	 * @param width the width of the entire room (including outside walls)
	 * @param height the height of the entire room (including outside walls)
	 * @param chests the array of chests that the Room should have
	 * @param walls the array of walls that the Room should have
	 * @param doors the array of doors that the Room should have
	 */
	public Room(int width, int height, Chest[] chests, Wall[] walls, Door[] doors) {
		super(width, height, chests, walls, doors);
		this.width = width;
		this.height = height;
	}
	
	public Room(int width, int height, Chest[] chests, Wall[] walls, Door[] doors, Position spawnPos) {
		this(width, height, chests, walls, doors);
		this.spawnPos = spawnPos;
	}
	
	public Position getSpawnPosition() {
		return spawnPos;
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
