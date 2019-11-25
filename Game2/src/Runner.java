import java.util.Scanner;

/**
 * This runs the whole program using some while loops.
 * @author Dr. Cheese
 *
 */
public class Runner {
	static Room currentRoom;
	static RoomStorage storage = new RoomStorage();
	
	static Scanner reader = new Scanner(System.in);
	static public Player you; // this is what the player controls, but it hasn't been finalized yet
	static String name = ""; // for whenever the Runner needs to reference the character's name

	static int turn; // keeps track of how many turns the player survived

	static long ticks = 2000000000L; // provides a delay between taking actions and reprinting the frame

	static boolean PlayerTurn = true; // keeps track of when it's the Player's turn
	static boolean EnemyTurn = false; // keeps track of when it's the monster's turn
	static boolean startup = false; // tells the program to display the startup messages, or just go to default settings
	static boolean running = false; // tells the program when the game part is running. Program will not stop if true

	static String action = ""; // used to keep track of what the player has entered for the character to do
	static String[] actions = {"move", "open", "attack", "help", "help2", "inventory",
		"check inventory", "weapon", "check weapon", "gear", "check gear", "wait", "stats", "die", "show frame"}; // keeps track of all of the acceptable actions

	static boolean inventHelp = true; // keeps track of whether the player has seen the inventory help message or not
	static boolean freeMoveHelp = true;

	public static void main(String[] args) {
		startup(); // runs the startup method created below

		do {
			//frame.updatePlayer(you); // tells the frame where the player is
			currentRoom = storage.getCurrent();
			currentRoom.update(you);
			you.getRoom(currentRoom);
			//System.out.println(currentRoom); // prints the frame, letting the player know what's going on
			System.out.println(currentRoom);

			if (PlayerTurn) {
				takeAction(); // if it's the player's turn, they can enter in commands, then it lets the enemies have a go
				EnemyTurn = true;
			}

			if (EnemyTurn) {
				PlayerTurn = true; // lets the player have a go
			}

			if (you.HP <= 0) { // checks if the player's character has died or not, and shuts down the game if it has
				print("Your character died! Better luck next time");
				running = false;
			}
			turn++;
		} while (running);
	}

	/**
	 * Used by the program to see what the player wants to do.
	 */
	static void takeAction() {
		if (turn == 0) { // prints some example commands if it's the player's first turn
			print("What would you like to do? (give commands in lowercase only) (example: move, attack, help)");
		} else {
			print("What would you like to do now?");
		}

		action = reader.nextLine(); // sets the action variable to the player's input
		if (action.equals("")) { // catches a bug where the program might skip a player's turn
			action = reader.nextLine();
		}

		catchActionException(); // makes sure the player has entered an acceptable command, and the program won't move past until a command is acceptable

		while (notAnAction()) { // if the player wants to check their inventory or anything that does not qualify as taking up their turn, it won't take up their turn
			boolean checkInvent = false; // keeps track of whether the player is checking their inventory or not
			boolean canEquip = false; // keeps track of whether the player can equip things from the command line or not

			if (action.equals("help")) {
				help(); // prints the first help message, describing all of the possible actions
			} else if (action.equals("help2")) {
				opening(); // prints the second help message, describing all of the symbols and what they represent
			} else if (action.equals("show frame")) {
				line();
				System.out.println(currentRoom); // prints out the frame again
			} else if (action.equals("stats")) {
				stats(); // prints out the character's current stats
			} else if (action.equals("check inventory") || action.equals("inventory")) {
				inventory(); // prints out the list of items that are currently in the player's inventory
				checkInvent = true;
				canEquip = true; // because the player is looking at a list of all of their items, they can equip things
			} else if (action.equals("check weapon") || action.equals("weapon")) {
				print("you currently have a " + you.weapon.name + " equipped as your weapon."); // prints what the character is currently using as their weapon
				checkInvent = true;
			} else if (action.equals("check gear") || action.equals("gear")) {
				if (you.gearset) { // if the character has a gear item equipped, it prints what it is
					print("you currently have a " + you.gear.name + " equipped in your gear slot.");
				} else { // otherwise it prints this
					print("you currently have no gear equipped.");
				}
				checkInvent = true;
			}

			if (!checkInvent) { // if the character was not checking their inventory, ask them what to do next
				print("What would you like to do now?");
				action = reader.nextLine();
			}

			while (checkInvent) { // if the character is checking their inventory, do this
				if (canEquip & inventHelp) { // prints a help message if it is the player's first time in this screen
					print("What would you like to do now? (you can equip other items in this menu. Enter equip to do so)");
					inventHelp = false; // turns off the help message
				} else {
					print("What would you like to do now?");
				}
				action = reader.nextLine();

				if (canEquip) { // if the player is looking at their full inventory
					if (action.equals("equip")) { // if their next command is equip, lets them equip something
						print("Would you like to equip a weapon or a gear item?");
						action = reader.nextLine();

						if (action.equals("weapon")) { // the program asks the player to tell it whether to equip a weapon or gear item
							print("Enter the name of the weapon you'd like to equip, including any bonus or Regular if there are none.");
							action = reader.nextLine();
							you.setWeaponByName(action);
							print("You equipped a " + you.weapon.name);
						} else if (action.equals("gear")) {
							print("Enter the name of the gear you'd like to equip, including any bonus or Regular if there are none.");
							action = reader.nextLine();
							you.setGearByName(action);
							print("You equipped a " + you.gear.name);
						}

						print("What would you like to do now?"); // once the process is done, asks for a new command
						action = reader.nextLine();
					}
				}
				checkInvent = false; // lets the program know the player is not longer checking the character's inventory
			}

			catchActionException(); // once again checks if the command entered by the player is acceptable, but on the new command
		}

		if (action.equals("move")) { //checks which of the acceptable commands the player has entered that will take the character's full action
			move();
		} else if (action.equals("wait")) { // this skips to the enemies' turn
			print(name + " waited");
		} else if (action.equals("open")) {
			open();
		} else if (action.equals("die")) { // if the character wants to end the game, the game kills their character by setting the character's HP = 0
			you.HP = 0;
		}
		line();

		for (long i = 0; i < ticks; i++) {} // waits for a small amount of time based on the ticks variable
	}
	
	static void exitRoom() {
		storage.moveUp();
	}

	/**
	 * This method checks if the player's command is going to take the character's full action
	 * @return boolean notAnAction
	 */
	static boolean notAnAction() {
		boolean notAnAction = false;
		if (!action.equals("move") && !action.equals("wait") && !action.equals("open") &&
				!action.equals("die") && !action.equals("attack")) { // if the command is not move, wait, open, die, or attack
			notAnAction = true;
		}
		return notAnAction; // if the command is not one of the full action commands, it returns true.
	}

	/**
	 * This makes sure that the command the player has entered can be processed, and if it can't lets the player re-enter commands until one can
	 */
	static void catchActionException() {
		boolean unrecognizedAction = true;
		do {
			for (int i = 0; i < actions.length; i++) { // checks the actions[] array for a command that matches the one entered
				if (action.equals(actions[i])) {
					unrecognizedAction = false;
				}
			}

			if (unrecognizedAction) { // if the command is not recognized, lets the player re-enter one
				print("That is not a recognized action. Please retype, or try something else.");
				action = reader.nextLine();
			}
		} while (unrecognizedAction); // repeats the process until the command is recognized
	}

	/**
	 * This displays the startup text and initializes some variables. It also has the option to skip the startup process entirely in favor of some default settings
	 */
	static void startup() {
		if (startup == true) { // if wanting to display the startup text
			print("Welcome to Jackson's Adventure Game! What would you like the character's name to be?");
			name = reader.nextLine(); // asks the player to name their character
			print("What race would you like to be? Your choices are human or orc.");
			String race = reader.nextLine(); // asks the player what race he/she would like their character to be
			you = new Player(race);

			line();
			opening(); // prints a message with all of the symbols of the dungeon and what they represent
			stats(); // prints the character's new stats

			do { // asks if the player is ready to start playing or not
				print("Are you ready to proceed?");
			} while (!reader.nextLine().equals("yes"));
			
			startup = false; // tells the program not to do the startup process again
			running = true; // tells the program that the game is now running
			line();
		} else { // these are the default settings
			name = "J"; // sets the name to J
			you = new Player("goblin");
			running = true;
		}
		currentRoom = storage.getRoom(0);
	}

	/**
	 * This prints the stats of the character
	 */
	static void stats() {
		line();
		System.out.println(you); // prints the stats of the character using the player class' toString() method
	}

	/**
	 * This prints all of the actions the player can take
	 */
	static void help() {
		String s = "";
		s += "The list of commands is as follows: \n";
		s += " - \'command\' (description) \n"; // a formatting example
		s += " - \'move\' (lets your character move a space in one direction that you specify) \n";
		s += " - \'attack\' (makes your character attack one of the tiles next to him/her) \n";
		s += " - \'open\' (opens a chest that is next to your character) \n";
		s += " - \'stats\' (lets you see your character's stats) \n";
		s += " - \'wait\' (skips straight to the enemies' turn) \n";
		s += " - \'inventory\' or \'check inventory\' (lets you check your inventory and equip items. You can only equip"
				+ "\n     one item at a time) \n";
		s += " - \'weapon\' or \'check weapon\' (lets you check what your currently equipped weapon is) \n";
		s += " - \'gear\' or \'gear\' (lets you check what your currently equipped gear is) \n";
		s += " - \'show frame\' (prints out the current frame again, so you don't have to scroll up to see the map) \n";
		s += "Make sure you give all your commands in lowercase! Enter help2 to see a list of all of the symbols and \n";
		s += "what they mean."; // also directs the player to the help2 command if they need to see the list of symbols again
		print(s);
	}

	/**
	 * This prints a list of all of the symbols in the frame and what they represent. It also adds some things if it's during the startup phase
	 */
	static void opening() {
		String s = "";
		if (startup) { // adds a Welcome statement if it's during the startup
			s += "Welcome, " + name + ". In this game, you are an adventurer who has delved into a dungeon. The symbols in this";
			s += " game \nrepresent a number of things. Here's a list: \n";
		} else {
			s += "Here is a list of all of the symbols you'll see on your journey: \n";
		}
		s += " - the \"#\"s represent the walls of the dungeon. \n";
		s += " - the \"|\" and \"-\" represent unlocked doors, while a \"+\" represents a locked door. \n";
		s += " - the \"@\" symbol is your character. \n";
		s += " - any \"X\" symbols represent monsters that roam the dungeon. \n";
		s += " - the \"H\" character represents a closed chest, while the \"O\" character represents an open chest.";
		if (startup) { // tacks on a piece explaining how to see this info again if it's displaying during the startup
			s += " \nYou can enter help2 at any time to see this message again.";
		}
		print(s);
	}

	/**
	 * This method prints out the list of items in the character's inventory, then tells the player what the character has equipped
	 */
	static void inventory() {
		String s = "In your inventory, you have:";
		for (int i = 0; i < you.inventory.length; i++) { // this for loop adds all of the items from the player's inventory
			s += "\n - a " + you.inventory[i].name;
		}
		if (you.gearset) { // if the player has gear equipped, add that to the equipped items list
			s += "\nCurrently Equipped: \n - Weapon: " + you.weapon.name + "\n - Gear Slot: " + you.gear.name;
		} else { // otherwise, just put a "None" in there
			s += "\nCurrently Equipped: \n - Weapon: " + you.weapon.name + "\n - Gear Slot: None";
		}
		s += "\n";
		print(s);
	}

	/**
	 * This method checks if there is a chest 1 space away in any of the 4 directions, then gives the player the contents of the chest
	 */
	static void open() {
		you.open();
	}

	/**
	 * This method lets the player move the character up to its speed in one direction
	 */
	static void move() {
		String action;
		print("Would you like to move freely or in one direction?");
		action = reader.nextLine();
		if (action.equals("free")) {freeMove();}
		else if (action.equals("direction")) directionMove();
	}
	
	static void freeMove() {
		String action;
		int movementRemaining = you.spd;
		if (freeMoveHelp) {
			print("This mode will let you move freely, and not just in one line."
					+ " You can move up to your speed.");
			freeMoveHelp = false;
		}
		
		do {
			do {
				print("Which direction would you like to move? (up, down, left, or right)"); // asks the player to input a direction
				action = reader.nextLine();
			} while (!action.equals("up") && !action.equals("down") && !action.equals("left") && !action.equals("right")); // keeps asking for a direction until given one

			you.move(action, 1);
			movementRemaining--;
			line();
			currentRoom.update(you);
			System.out.println(currentRoom);
		} while (movementRemaining > 0);
	}
	
	static void directionMove() {
		String action;
		do {
			print("Which direction would you like to move? (up, down, left, or right)"); // asks the player to input a direction
			action = reader.nextLine();
		} while (!action.equals("up") && !action.equals("down") && !action.equals("left") && !action.equals("right")); // keeps asking for a direction until given one

		if (you.spd == 1) { // if the character's speed is 1, they don't get to choose how many spaces to move
			you.move(action, 1); // makes the character move 1 space in the chosen direction
			print(name + " moved " + action);
		} else {
			int spaces;

			do { // if the character can move more than 1 space per turn, gives the player the option
				print("How many spaces would you like to move?");
				spaces = reader.nextInt();

				if (spaces > you.spd) { // if the player wants to move faster than the character's speed, say no
					print("you can't move faster than your speed will allow.");
					line();
				} else if (spaces == 0) { // if the player wants to not move in this context, say no
					print("you can't move 0 spaces.");
					line();
				} else if (spaces < 0) { // if the player wants to move negative spaces, say no
					print("you can't move negative spaces.");
					line();
				}
			} while ((spaces < 0) || (spaces == 0) || (spaces>you.spd));

			you.move(action, spaces); // once a direction and number of spaces have been selected, makes the character move

			if (spaces != 1) {
				print(name + " moved " + action + " " + spaces + " spaces");
			} else {
				print(name + " moved " + action + " " + spaces + " space");
			}
		}
	}

	/**
	 * This method is used to do a full line break without having to type out the System.out.println() command every time.
	 */
	static void line() {
		System.out.println();
	}

	/**
	 * This method fulfills the same function as the System.out.println(String s) method, but without having to type out most of the method call
	 * @param s String
	 */
	static void print(String s) {
		System.out.println(s);
	}
}
