import java.time.LocalDateTime;

public class Booking {
	
	private int id;
	private Guest guest;
	private GuestCheckInOutRecord checkInOutRecord;
	private Room room;
	private Bill bill;
	
	public Booking(Guest guest) {
		id = 0;
		this.guest = guest;
		checkInOutRecord = new GuestCheckInOutRecord();
		room = null;
		bill = new Bill(guest);
	}
	
	public int getId() {
		return id;
	}
	
	public void setRoom(Room room) {
		room.occupy();
		this.room = room;		
	}
	
	public void changeRoom(Room newRoom) {
		this.room.unOccupy();
		setRoom(newRoom);		
	}
	
	public void setExpectedCheckInTime(LocalDateTime expectedCheckIn) {
		checkInOutRecord.setExpectedCheckInTime(expectedCheckIn);
	}
	
	public void setExpectedCheckOutTime(LocalDateTime expectedCheckOut) {
		checkInOutRecord.setExpectedCheckOutTime(expectedCheckOut);
	}
	
	public void checkIn() {
		checkInOutRecord.setActualCheckInTime(LocalDateTime.now());
	}
	
	public void checkOut() {
		checkInOutRecord.setActualCheckOutTime(LocalDateTime.now());
	}
	
	public Bill getBill() {
		return bill;
	}
	
	
	
	
	
	

}
