package HotelBase;

public class IdFactory {
	
	private static int nextBillId = 1;
	private static int nextBookingId = 1;
	private static int nextEmployeeId = 1;
	private static int nextRoomMajorId = 100;
	private static int nextRoomMinorId = 1;
	
	private final static int ROOMS_PER_FLOOR = 25;
	private final static int FLOORS = 5;
	
	
	
	public static int getNextBillId() {
		return nextBillId++;
		
	}
	
	public static int getNextBookingId() {
		return nextBookingId++;
	}
	
	public static int getNextEmployeeId() {
		return nextEmployeeId++;
	}
	
	public static int getNextRoomId() throws Exception {
		if (nextRoomMinorId >= 25) {
			nextRoomMinorId = 1;
			nextRoomMajorId += 100;
			if (nextRoomMajorId == 600) {
				throw new Exception("The hotel has the maximum number of rooms!");
			}
		}
		
		return (nextRoomMajorId) + (nextRoomMinorId++);
	}
	

}
