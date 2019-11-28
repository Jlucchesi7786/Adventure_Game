import java.util.*;

/**
 * This class takes all of the random tiles and characters lying around the dungeon 
 * and organizes them into one two-stage array, called map[][].
 * @author Dr. Cheese
 */
public class Compiler {
	private Chest[] chests;
	private Wall[] walls;
	private Door[] doors;
	private Player player;

	private int width;
	private int height;

	public Tile[][] map;

	/**
	 * The one constructor for the Compiler class takes in width, height, and a set 
	 * of arrays and then organizes them into one simple array of tiles that the Room
	 * class can then use to print out the dungeon to the console.
	 * @param width the total width of the dungeon
	 * @param height the total height of the dungeon
	 * @param chests an array containing all of the Chests in the room
	 * @param walls an array containing all of the Walls in the room
	 * @param doors an array containing all of the Doors in the room
	 */
	public Compiler(int width, int height, Chest[] chests, Wall[] walls, Door[] doors) {
		this.width = width;
		this.height = height;
		this.chests = chests;
		this.walls = walls;
		this.doors = doors;
		this.player = new Player();
		compileTiles();
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
				Position gridPos = new Position(x, y);
				boolean added = false;

				for (Chest chest: chests) {
					if (chest.pos.equals(gridPos)) {
						rowList.add(chest.space);
						added = true;
					}
				}

				if (player.pos.equals(gridPos)) {
					rowList.add(player.space);
					added = true;
				}

				for (Door door: doors) {
					if (door.pos.equals(gridPos) && !added) {
						rowList.add(door.space);
						added = true;
					}
				}

				for (Wall wall: walls) {
					if (wall.checkSpaces(gridPos) && !added) {
						rowList.add(wall.space);
						added = true;
					}
				}

				if (!added) {
					rowList.add(new Tile("empty"));
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

	/**
	 * This returns a Tile in a specific position on the map. Useful for 
	 * navigation.
	 * @param tilePos the Position (object) of the Tile you'd like to know
	 * @return the Tile object that you wanted
	 */
	public Tile getTile(Position tilePos) {
		return map[tilePos.x][tilePos.y];
	}

	/**
	 * This gets the map. Pretty self-explanatory.
	 * @return the map
	 */
	public Tile[][] getMap() {
		return map;
	}

	/**
	 * This gets the chests array specific to this compiler, for whenever 
	 * that would be necessary.
	 * @return the array of chests
	 */
	public Chest[] getChests() {
		return chests;
	}

	/**
	 * This gets the walls array specific to this compiler, for whenever 
	 * that would be necessary.
	 * @return the array of walls
	 */
	public Wall[] getWalls() {
		return walls;
	}

	/**
	 * This gets the doors array specific to this compiler, for whenever 
	 * that would be necessary.
	 * @return the array of doors
	 */
	public Door[] getDoors() {
		return doors;
	}
}
