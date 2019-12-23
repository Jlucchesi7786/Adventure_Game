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
	
	static Chest[] chests2 = {new Chest(new Position(1, 19)), new Chest(new Position(2, 11)), new Chest(new Position(4, 5)), 
		new Chest(new Position(5, 5)), new Chest(new Position(11, 2)), new Chest(new Position(22, 20)), 
		new Chest(new Position(21, 27)), new Chest(new Position(18, 26)), new Chest(new Position(9, 27))};
	static Wall[] walls2 = {new Wall("horizontal", 7, new Position(8, 0)), 
		new Wall("horizontal", 2, new Position(8, 1)), new Wall("horizontal", 2, new Position(13, 1)),
		new Wall("vertical", 8, new Position(8, 0)), new Wall("vertical", 8, new Position(14, 0)), 
		new Wall("horizontal", 15, new Position(3, 7)), new Wall("horizontal", 6, new Position(3, 3)),
		new Wall("vertical", 5, new Position(3, 3)), new Wall("vertical", 3, new Position(12, 8)), 
		new Wall("vertical", 3, new Position(10, 8)), new Wall("horizontal", 10, new Position(1, 10)), 
		new Wall("vertical", 4, new Position(5, 7)), new Wall("vertical", 15, new Position(1, 10)), 
		new Wall("horizontal", 18, new Position(1, 25)), new Wall("vertical", 4, new Position(10, 25)), 
		new Wall("vertical", 4, new Position(12, 25)), new Wall("horizontal", 15, new Position(7, 28)), 
		new Wall("vertical", 19, new Position(15, 10)), new Wall("horizontal", 8, new Position(12, 10)), 
		new Wall("vertical", 19, new Position(7, 10)), new Wall("vertical", 5, new Position(3, 17)), 
		new Wall("vertical", 5, new Position(4, 17)), new Wall("vertical", 8, new Position(5, 14)), 
		new Wall("vertical", 3, new Position(0, 18)), new Wall("vertical", 2, new Position(2, 20)), 
		new Wall("vertical", 4, new Position(17, 7)), new Wall("vertical", 3, new Position(20, 10)), 
		new Wall("vertical", 2, new Position(21, 13)), new Wall("vertical", 15, new Position(19, 14)), 
		new Wall("vertical", 3, new Position(22, 26)), new Wall("vertical", 4, new Position(23, 23)), 
		new Wall("vertical", 2, new Position(22, 22)), new Wall("vertical", 1, new Position(22, 18)), 
		new Wall("vertical", 3, new Position(23, 19)), new Wall("horizontal", 4, new Position(20, 23)), 
		new Wall("horizontal", 2, new Position(20, 17)), new Wall("horizontal", 6, new Position(15, 14)), 
		new Wall("horizontal", 6, new Position(2, 14))};
	static Door[] doors2 = {new Door(new Position(11, 7), "locked", "horizontal"), 
		new Door(new Position(7, 7), "locked", "horizontal"), new Door(new Position(9, 10), "unlocked", "horizontal"), 
		new Door(new Position(13, 10), "unlocked", "horizontal"), new Door(new Position(16, 14), "unlocked", "horizontal"), 
		new Door(new Position(11, 25), "unlocked", "horizontal"), new Door(new Position(8, 25), "unlocked", "horizontal"), 
		new Door(new Position(6, 21), "unlocked", "horizontal"), new Door(new Position(6, 14), "unlocked", "horizontal"), 
		new Door(new Position(2, 17), "locked", "horizontal"), new Door(new Position(15, 27), "locked", "vertical"), 
		new Door(new Position(19, 20), "locked", "vertical"), new Door(new Position(19, 24), "unlocked", "vertical"), 
		new Door(new Position(15, 24), "unlocked", "vertical"), new Door(new Position(15, 12), "unlocked", "vertical"), 
		new Door(new Position(7, 24), "unlocked", "vertical"), new Door(new Position(12, 26), "unlocked", "vertical"), 
		new Door(new Position(5, 16), "unlocked", "vertical")};
	private static Room[] AmberTemple= {new Room(25, 29, chests2, walls2, doors2, new Position(11, 27))};
	
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
