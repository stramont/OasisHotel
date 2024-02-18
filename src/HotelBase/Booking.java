package HotelBase;
import java.time.LocalDateTime;

public class Booking {
	
	private int id;
	private Guest guest;
	private Room room;
	private Bill bill;
	
	public Booking(Guest guest) {
		id = 0;
		this.guest = guest;
		room = null;
		//bill = new Bill(guest);
	}
	
	public int getId() {
		return id;
	}
	
	public void setRoom(Room room) {
		this.room = room;		
	}
	
	public void setGuest(Guest newGuest) {
		guest = newGuest;
	}	
	
	public Bill getBill() {
		return bill;
	}

	public String toXMLElement() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	

}
