import java.util.*;

public class RoomStorage {
	Room[] rooms;
	int index;
	private static final Room[] defaultRoom = {new Room()};
	static Chest[] chests = {new Chest(new Position(8, 2))};
	static Wall[] walls = {new Wall("vertical", 11, new Position(0, 4)), new Wall("horizontal", 15, new Position(0, 9)),
		new Wall("horizontal", 5, new Position(0, 4)), new Wall("horizontal", 15, new Position(0, 14)),
		new Wall("vertical", 6, new Position(14, 9)), new Wall("vertical", 6, new Position(12, 9)),
		new Wall("vertical", 6, new Position(3, 9)), new Wall("vertical", 10, new Position(4, 0)), 
		new Wall("vertical", 6, new Position(9, 0)), new Wall("horizontal", 8, new Position(4, 5)), 
		new Wall("vertical", 5, new Position(11, 5)), new Wall("horizontal", 6, new Position(4, 0))};
	static Door[] doors = {new Door(new Position(2, 4), "unlocked", "horizontal"), 
		new Door(new Position(2, 9), "locked", "horizontal"), new Door(new Position(3, 11), "locked", "vertical"),
		new Door(new Position(6, 5), "locked", "horizontal"), new Door(new Position(10, 9), "unlocked", "horizontal"),
		new Door(new Position(12, 12), "unlocked", "vertical"), new Door(new Position(14, 10), "unlocked", "vertical")};
	private static Room[] ShadarKai = {new Room(15, 15, chests, walls, doors, new Position(2, 5))};
	
	static Chest[] chests2 = {};
	static Wall[] walls2 = {new Wall("horizontal", 7, new Position(8, 0)), 
		new Wall("horizontal", 2, new Position(8, 1)), new Wall("horizontal", 2, new Position(13, 1)),
		new Wall("vertical", 8, new Position(8, 0)), new Wall("vertical", 8, new Position(14, 0)), 
		new Wall("horizontal", 15, new Position(3, 7)), new Wall("horizontal", 6, new Position(3, 3)),
		new Wall("vertical", 5, new Position(3, 3))};
	static Door[] doors2 = {new Door(new Position(11, 7), "locked", "horizontal")};
	private static Room[] AmberTemple= {new Room(29, 25, chests2, walls2, doors2, new Position())};
	
	public RoomStorage() {
		rooms = AmberTemple;
		index = 0;
	}
	
	public RoomStorage(Room[] rooms) {
		ArrayList<Room> roomlist = new ArrayList<Room>();
		for (Room item: rooms) {
			roomlist.add(item);
		}
		this.rooms = new Room[roomlist.size()];
		roomlist.toArray(this.rooms);
		index = 0;
	}
	
	public void moveUp() {
		index++;
	}

	public int index() {
		return index;
	}
	
	public Room[] getRooms() {
		return rooms;
	}
	
	public Room getCurrent() {
		return rooms[index];
	}
	
	public Room getRoom(int index) {
		this.index = index;
		return rooms[index];
	}
}
