
public class Tile {
	public String character;
	public String type;

	private String[] types = {"horizontal door", "vertical door", "monster", "closed chest",
			"open chest", "player", "wall", "empty", "locked door"};
	private String[] characters = {"-", "|", "X", "H", "O", "@", "#", " ", "+"};

	public Tile(String type) {
		this.type = type;
		charSet();
	}

	public Tile() {
		this.type = types[(int) Math.ceil(Math.random()*types.length)];
		charSet();
	}

	public void update(String type) {
		this.type = type;
		charSet();
	}

	private void charSet() {
		boolean set = false;
		for (int i = 0; i < types.length; i++) {
			if (type.equals(types[i])) {
				character = characters[i];
				set = true;
			}
		}
		
		if (!set) {
			character = "%";
			type = "undefined";
		}
	}

	public boolean equals(Tile other) {
		if (this.character == other.character) {
			return true;
		}
		return false;
	}
}
