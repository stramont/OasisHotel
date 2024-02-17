package HotelBase;
import java.util.ArrayList;

public class Room {
	
	enum RoomType {
			NORMAL,
			SUITE,
			EXECECUTIVE_SUITE,
			PRESIDENTIAL_SUITE,
			PENTHOUSE			
	}
	
	private int roomId; //Also the real-life room number
	private ArrayList<Bed> beds;
	private int bathroomCount;
	private RoomType roomType;
	private boolean adaAccessible;
	private boolean vacant;
	private int costPerNight;
	
	private Guest currentGuest;
	private ArrayList<Guest> upcomingGuests;
	
	public Room(ArrayList<Bed> beds, int bathroomCount, RoomType roomType) throws Exception {
		roomId = IdFactory.getNextRoomId();
		this.beds = beds;
		this.bathroomCount = bathroomCount;
		this.roomType = roomType;
		adaAccessible = false;
		vacant = true;
		costPerNight = 100;
		upcomingGuests = new ArrayList<Guest>();
	}
	
	public int getId() {
		return roomId;
	}
	
	public ArrayList<Bed> getBeds() {
		return beds;
	}
	
	public int getBathroomCount() {
		return bathroomCount;
	}
	
	public RoomType getRoomType() {
		return roomType;
	}
	
	public boolean isVacant() {
		return vacant;
	}
	
	public void makeAdaAccessible() {
		adaAccessible = true;
	}
	
	public void removeAdaAccessible() {
		adaAccessible = false;
	}
	
	public void setCostPerNight(int costPerNight) {
		this.costPerNight = costPerNight;
	}
	
	public void addUpcomingGuest(Guest upcomingGuest) {
		upcomingGuests.add(upcomingGuest);
	}
	
	public void updateCurrentGuest() {
		if (currentGuest != null && currentGuest.isGuestCheckedOut())
			currentGuest = null;
			
		if (!upcomingGuests.isEmpty() && upcomingGuests.get(0).isGuestCheckedIn()) {
			currentGuest = upcomingGuests.remove(0);
			vacant = false;
		}
	}
	
	public boolean isOpenForGuest(GuestCheckInOutRecord checkInOutRecord) {
		if (currentGuest != null && currentGuest.getCheckInOutRecord().doRecordsOverlap(checkInOutRecord))
			return false;
		
		for (Guest upcomingGuest : upcomingGuests) {
			if (upcomingGuest.getCheckInOutRecord().doRecordsOverlap(checkInOutRecord))
				return false;
		}
		
		return true;
	}
		
	public int getSumOfPeoplePerBed() {
		int sumOfBedSpace = 0;
		
		for (Bed bed : beds)
			sumOfBedSpace += bed.getBedSpace();
				
		return sumOfBedSpace;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder("");
		
		builder.append("Room Number (ID): " + roomId + "\n");
		builder.append("Room Type: " + roomType.name() + "\n");
		builder.append("Current Cost: $" + costPerNight + "\n");
		builder.append("Bed Count: " + beds.size() + "\n");
		builder.append("Beds: ");		
		for (Bed b : beds) {
			builder.append(b.toString());
			builder.append("|");
		}
		builder.deleteCharAt(builder.length() - 1);
		builder.append("\n");
		builder.append("Bathroom Count: " + bathroomCount + "\n");
		builder.append("ADA Accessible: " + adaAccessible + "\n");
		
		
		return builder.toString();
	}
	
}
