/**
 * This class stores information about any items in the game.
 * @author Dr. Cheese
 */
public class Item {
	public String type; // holds the type that the Item is
	public String name; // holds the name of the object

	private int baseMod; // holds what the item would give without any modifiers
	private int itemMod; // holds the bonus that the item has
	public int mod; // holds the total bonus for the object

	public boolean weapon; // holds whether or not the item is a weapon
	public boolean gear; // holds whether or not the item is a gear item
	public boolean dmgBoost; // holds whether or not the gear item increases damage

	public boolean shield; // holds whether or not the item is a shield

	private String[] types = {"Longsword", "Iron Shield", "Shortsword", "Amulet of Strength"}; // an array that hold all of the types that an Item can be

	/**
	 * This constructor is used when the author wants to make a specific item.
	 * @param type String
	 * @param itemMod int
	 */
	Item(String type, int itemMod) {
		this.type = type; // sets the type of the Item
		this.itemMod = itemMod; // sets the item's bonus
		typeCheck(); // runs the typeCheck() method to initialize some other variables
		mod = baseMod + itemMod; // sets the total modifier of the object
		setName(); // runs the setName() method to set the final variable
	}

	/**
	 * This constructor is used to make a random item.
	 */
	Item() {
		int rand = (int) Math.ceil(Math.random()*types.length)-1; // makes a random number to reference the types[] array
		type = types[rand]; // uses the random number to get a random type;
		itemMod = (int) Math.ceil(Math.random()*10); // makes a random bonus for the object, between 1 and 10
		typeCheck(); // runs the typeCheck() method to initialize some other variables
		mod = baseMod + itemMod; // sets the total modifier of the object
		setName(); // runs the setName() method to set the final variable
	}

	/**
	 * This method is used as the final part of construction to set the name of the item with a bonus and type.
	 */
	private void setName() {
		if (itemMod != 0) { // if the bonus on the object has a value
			name = "+" + itemMod + " " + type; // sets the name as +(bonus) type (Ex. +2 Longsword)
		} else { // otherwise just sets the name as Regular type (Ex. Regular Shortsword)
			name = "Regular " + type;
		}
	}

	/**
	 * This method is used to check what type the author or random number wants to set it too, then changes some variables based on which type it is.
	 */
	private void typeCheck() {
		if (type.equals("Longsword")) {
			baseMod = 4; // Longswords have a base of +4 damage
			weapon = true; // A Longsword is a weapon
			gear = false; // A Longsword can't be equipped as a gear item
		} else if (type.equals("Shortsword")) {
			baseMod = 2; // Shortswords have a base of +2 damage
			weapon = true; // A Shortsword is a weapon
			gear = false; // A Shortsword can't be equipped as a gear item
		}

		if (type.equals("Amulet of Strength")) {
			baseMod = 1; // An Amulet of Strength has a base of +1 damage
			weapon = false; // An Amulet of Strength is not a weapon
			gear = true; // An Amulet of Strength is a gear item
			shield = false; // An Amulet of Strength is not a shield

			dmgBoost = true; // An Amulet of Strength boosts damage despite not being a weapon
		} else if (type.equals("Iron Shield")) {
			baseMod = 2; // An Iron Shield has a base of +2 defense
			weapon = false; // An Iron Shield is not a weapon
			gear = true; // An Iron Shield is a gear item
			shield = true; // An Iron Shield is a shield

			dmgBoost = false; // An Iron Shield does not boost damage
		}
	}

	/**
	 * This toString method returns the details of the Item in question.
	 */
	public String toString() {
		return (type + ", +" + itemMod + "\n - It has a base mod of +" + baseMod +
			"\n - Combined with the +" + itemMod + ", it has a total mod of +" + mod);
	}

	/**
	 * This method changes the modifier on the item the method is being run with.
	 * @param itemMod int
	 */
	public void setItemMod(int itemMod) {
		this.itemMod = itemMod;
	}

	/**
	 * This method changes the name that the object is referred to as.
	 * @param name String
	 */
	public void setName(String name) {
		this.name = name;
	}
}
