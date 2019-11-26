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

	public Enemy() {
		this("default", 1);
	}
	
	/**
	 * This constructor initializes an enemy with a name and some level to base the stats on.
	 * @param name String
	 * @param level int
	 */
	public Enemy(String name, int level) {
		this(new Position((int)(Math.ceil(Math.random()*30)), (int)(Math.ceil(Math.random()*30))), name, level);
	}

	/**
	 * This constructor initializes an enemy at some given x and y position and a level to 
	 * base the stats on. The String name is not initialized.
	 * @param x int
	 * @param y int
	 * @param level int
	 */
	public Enemy(int x, int y, int level) {
		this(new Position(x, y), level);
	}
	
	public Enemy(Position pos, int level) {
		this(pos, "default", level);
	}
	
	public Enemy(Position pos, String name, int level) {
		this.pos = pos;
		this.name = name;
		str = (int)(Math.ceil(Math.random()*level)); // makes the damage the monster does randonly based on the level
		def = (int)(Math.ceil(Math.random()*level)); // makes the amount the monster reduces damage by randomly based on the level
		drop = new Item();
		space = new Tile("Monster");
	}
	
	public void detect(Player player) {
		double xDist = this.pos.x - player.pos.x;
		double yDist = this.pos.x - player.pos.y;
		double dist = Math.sqrt((Math.pow(xDist, 2) + Math.pow(yDist, 2)));
		if (dist <= 5) {
			pursuing = true;
		}
	}
}
