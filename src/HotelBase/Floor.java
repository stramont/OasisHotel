package HotelBase;
import java.util.ArrayList;

public class Floor {
	
	private ArrayList<Room> rooms;
	
	public Floor() {
		rooms = new ArrayList<Room>();		
	}
	
	public ArrayList<Room> getAllRooms() {
		return rooms;
	}
	
	public ArrayList<Room> getVacantRooms() {
		ArrayList<Room> vacantRooms = new ArrayList<Room>();
		for (Room room : rooms) {
			if (room.isVacant())
				vacantRooms.add(room);
		}
		
		return vacantRooms;
	}
	
	public ArrayList<Room> getBookedRooms() {
		ArrayList<Room> bookedRooms = new ArrayList<Room>();
		for (Room room : rooms) {
			if (!room.isVacant())
				bookedRooms.add(room);
		}
		
		return bookedRooms;
	}
	
	

}
