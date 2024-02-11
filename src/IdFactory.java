
public class IdFactory {
	
	private static int nextBillId = 1;
	private static int nextBookingId = 1;
	private static int nextEmployeeId = 1;
	private static int nextRoomId = 100;
	
	private final static int ROOMS_PER_FLOOR = 25;
	
	
	
	public static int getNextBillId() {
		return nextBillId++;
		
	}
	
	public static int getNextBookingId() {
		return nextBookingId++;
	}
	
	public static int getNextEmployeeId() {
		return nextEmployeeId++;
	}
	
	public static int getNextRoomId() {
		return 0;
	}
	

}
