import java.util.*;

/**
 * This class takes all of the random tiles and characters lying around the dungeon and organizes them into one two-stage array, called map[][].
 * @author Dr. Cheese
 */
public class Compiler {
	private static final Chest[] defaultChests = {new Chest(new Position(1, 30)), new Chest(30, 19), new Chest(1, 1), new Chest(30, 2), new Chest(5, 5), new Chest(26, 15)};
	private Chest[] chests;
	private static final Wall[] defaultWalls = {new Wall("vertical", 30, 6, 1), new Wall("horizontal", 5, 1, 4), new Wall("horizontal", 25, 6, 14), new Wall("vertical", 30, 25, 1), new Wall("horizontal", 6, 25, 18)};
	private Wall[] walls;
	private static final Door[] defaultDoors = {new Door(new Position(6, 2), "unlocked", "vertical"), new Door(new Position(25, 10), "unlocked", "vertical"), new Door(new Position(2, 4), "unlocked", "horizontal")};
	private Door[] doors;
	private Player player;

	private int width;
	private int height;

	public Tile[][] map;

	public Compiler(int width, int height, Player player) {
		this.width = width;
		this.height = height;
		this.player = player;
		this.chests = defaultChests;
		this.walls = defaultWalls;
		this.doors = defaultDoors;
		compileTiles();
	}
	
	public Compiler(int width, int height, Player player, Chest[] chests, Wall[] walls, Door[] doors) {
		this(width, height, chests, walls, doors);
		this.player = player;
	}
	
	public Compiler(int width, int height, Chest[] chests, Wall[] walls, Door[] doors) {
		this.width = width;
		this.height = height;
		this.chests = chests;
		this.walls = walls;
		this.doors = doors;
		compileTiles();
	}

	public Compiler() {
		this(32, 32, new Player());
	}

	/**
	 * This is where all the magic happens. This makes two array lists, one to 
	 * store each row of tiles, then another to store all of the tiles
	 * in each row. Combined with for loops, they store the identities of 
	 * every tile on a map of a given size provided by the constructor.
	 */
	public void compileTiles() {
		ArrayList<Tile[]> mapList = new ArrayList<Tile[]>();
		for (int x = 0; x < width; x++) {
			ArrayList<Tile> rowList = new ArrayList<Tile>();
			for (int y = 0; y < height; y++) {
				if (y == 0 || y == height-1) {
					rowList.add(new Tile("wall"));
				} else {
					Position gridPos = new Position(x, y);
					if (x == 0 || x == width-1) {
						rowList.add(new Tile("wall"));
					} else {
						boolean added = false;

						for (int c = 0; c < chests.length; c++) {
							if (chests[c].pos.equals(gridPos)) {
								rowList.add(chests[c].space);
								added = true;
							}
						}

						if (player.pos.equals(gridPos)) {
							rowList.add(player.space);
							added = true;
						}

						for (int c = 0; c < doors.length; c++) {
							if (doors[c].pos.equals(gridPos) && !added) {
								rowList.add(doors[c].space);
								added = true;
							}
						}

						for (int c = 0; c < walls.length; c++) {
							if (walls[c].checkSpaces(gridPos) && !added) {
								rowList.add(walls[c].space);
								added = true;
							}
						}

						if (!added) {
							rowList.add(new Tile("empty"));
						}
					}
				}
			}
			
			Tile[] rowArray = new Tile[rowList.size()];
			rowList.toArray(rowArray);
			mapList.add(rowArray);
		}
		map = new Tile[mapList.size()][];
		mapList.toArray(map);
	}

	/**
	 * this updates the x and y of the player on the map[][] array, then 
	 * recompiles the tiles to show the player's new position.
	 * @param player (Player type)
	 */
	public void update(Player player) {
		this.player = player;
		compileTiles();
	}

	public Tile getTile(Position tilePos) {
		return map[tilePos.x][tilePos.y];
	}
	
	public Tile[][] getMap() {
		return map;
	}
	
	public Chest[] getChests() {
		return chests;
	}
	
	public Wall[] getWalls() {
		return walls;
	}
	
	public Door[] getDoors() {
		return doors;
	}
}
