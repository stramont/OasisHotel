package HotelBase;
import java.time.LocalDateTime;

public class Guest {
	
	private int id;
	private int partySize;
	private String partyName;
	private GuestCheckInOutRecord checkInOutRecord;
	
	
	public Guest(String partyName, int partySize) {
		id = 0;
		this.partyName = partyName;
		this.partySize = partySize;		
	}
	
	public int getId() {
		return id;	
	}
	
	public int getPartySize() {
		return partySize;
	}
	
	public void setPartySize(int newPartySize) {
		partySize = newPartySize;
	}
	
	public void setPartyName(String newPartyName) {
		partyName = newPartyName;
	}
	
	public void setCheckInOutRecord(GuestCheckInOutRecord checkInOutRecord) {
		this.checkInOutRecord = checkInOutRecord;
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
	
	public boolean isGuestCheckedIn() {
		return checkInOutRecord.getActualCheckInTime() != null;
	}
	
	public boolean isGuestCheckedOut() {
		return checkInOutRecord.getActualCheckOutTime() != null;
	}
	
	public GuestCheckInOutRecord getCheckInOutRecord() {
		return checkInOutRecord;
	}
	
	
	
	

}
