import java.util.*;

public class RoomStorage {
	Room[] rooms;
	int index;
	private static final Room[] defaultRoom = {new Room()};
	
	public RoomStorage() {
		rooms = defaultRoom;
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
