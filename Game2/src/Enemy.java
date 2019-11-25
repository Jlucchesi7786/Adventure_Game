/**
 * This class is holds a bunch of information about the enemies around the dungeon.
 * @author Dr. Cheese
 */
public class Enemy {
	public Position pos;

	int level; // holds how strong the monster should be, give or take
	int str; // holds how much damage the monster will do
	int def; // holds how much the monster will reduce damage taken
	Item drop; // holds what Item the monster will give on death
	String name; // holds what the monster is called in the text

	int playerX; // holds the player character's given x position
	int playerY; // holds the player character's given y position

	Tile space;

	boolean pursuing = false; // this changes the state of the monster

	/**
	 * This constructor initializes an enemy with a name and some level to base the stats on.
	 * @param name String
	 * @param level int
	 */
	Enemy(String name, int level) {
		this.name = name;
		this.level = level;
		str = (int)(Math.ceil(Math.random()*level)); // makes the damage the monster does randonly based on the level
		def = (int)(Math.ceil(Math.random()*level)); // makes the amount the monster reduces damage by randomly based on the level
		int x = (int)(Math.ceil(Math.random()*30)); // makes a random x position
		int y = (int)(Math.ceil(Math.random()*30)); // makes a random y position
		pos = new Position(x, y);
		drop = new Item(); // sets up the monster drop with a random item
		space = new Tile("monster");
	}

	/**
	 * This constructor initializes an enemy at some given x and y position and a level to base the stats on. The String name is not initialized.
	 * @param x int
	 * @param y int
	 * @param level int
	 */
	Enemy(int x, int y, int level) {
		pos = new Position(x, y);
		this.level = level; // sets the level
		str = (int)(Math.ceil(Math.random()*level)); // makes the damage the monster does randonly based on the level
		def = (int)(Math.ceil(Math.random()*level)); // makes the amount the monster reduces damage by randomly based on the level
		drop = new Item();
	}
}
