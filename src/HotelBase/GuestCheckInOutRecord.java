package HotelBase;
import java.time.LocalDateTime;

public class GuestCheckInOutRecord {
	
	private LocalDateTime expectedCheckIn;
	private LocalDateTime actualCheckIn;
	private LocalDateTime expectedCheckOut;
	private LocalDateTime actualCheckOut;
	
	public GuestCheckInOutRecord() {
		
	}
	
	public GuestCheckInOutRecord(LocalDateTime expectedCheckIn, LocalDateTime expectedCheckOut) {
		this.expectedCheckIn = expectedCheckIn;
		this.expectedCheckOut = expectedCheckOut;
	}
	
	public void setExpectedCheckInTime(LocalDateTime expectedCheckIn) {
		this.expectedCheckIn = expectedCheckIn;
	}
	
	public void setActualCheckInTime(LocalDateTime actualCheckIn) {
		this.actualCheckIn = actualCheckIn;
	}
	
	public void setExpectedCheckOutTime(LocalDateTime expectedCheckOut) {
		this.expectedCheckOut = expectedCheckOut;
	}
	
	public void setActualCheckOutTime(LocalDateTime actualCheckOut) {
		this.actualCheckOut = actualCheckOut;
	}
	
	public boolean doRecordsOverlap(GuestCheckInOutRecord argRecord) {
		return doesArgRecordStartDuringThisRecord(argRecord) || doesThisRecordStartDuringArgRecord(argRecord);		
	}
	
	private boolean doesArgRecordStartDuringThisRecord(GuestCheckInOutRecord argRecord) {
		return argRecord.getExpectedCheckInTime().isAfter(expectedCheckIn) && argRecord.getExpectedCheckInTime().isBefore(expectedCheckOut);
	}
	
	private boolean doesThisRecordStartDuringArgRecord(GuestCheckInOutRecord argRecord) {
		return expectedCheckIn.isAfter(argRecord.getExpectedCheckInTime()) && expectedCheckOut.isBefore(argRecord.getExpectedCheckOutTime());
	}
	
	public LocalDateTime getExpectedCheckInTime() {
		return expectedCheckIn;
	}
	
	public LocalDateTime getExpectedCheckOutTime() {
		return expectedCheckOut;
	}
	
	public LocalDateTime getActualCheckInTime() {
		return actualCheckIn;
	}
	
	public LocalDateTime getActualCheckOutTime() {
		return actualCheckOut;
	}

}
