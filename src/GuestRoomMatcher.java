import java.util.ArrayList;

public class GuestRoomMatcher {
	
	private Guest guest;
	private ArrayList<Room> allRooms;
	
	public GuestRoomMatcher(Guest guest, ArrayList<Room> allRooms) {
		this.guest = guest;
		this.allRooms = allRooms;
	}
		
	/*
	 * Things that need to be taken into account:
	 * - Guest's necessary accommodations. 
	 * - Guest's party size
	 * - Guest's check-in and check-out times
	 * - Guest's ada requirements
	 * - Rooms that are available during Guest's stay period
	 */
	public ArrayList<Room> getListOfCompatibleRooms() {
		ArrayList<Room> compatibleRooms = new ArrayList<Room>();
		
		for (Room room : allRooms) {
			if (isGuestCompatibleWithRoom(room))
				compatibleRooms.add(room);
		}
		
		return compatibleRooms;		
	}
	
	private boolean isGuestCompatibleWithRoom(Room room) {
		return room.isOpenForGuest(guest.getCheckInOutRecord());
	}
	

}
