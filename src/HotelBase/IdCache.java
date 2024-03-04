package HotelBase;

import java.util.ArrayList;

public class IdCache {
	
	public class Tuple<X, Y> { 
	  public final X x; 
	  public final Y y; 
	  public Tuple(X x, Y y) { 
	    this.x = x; 
	    this.y = y; 
	  } 
	} 
	
	public int billId;
	public int bookingId;
	public int employeeId;
	public int roomMajorId;
	public int roomMinorId;
	
	private ArrayList<Tuple<String, Integer>> idNames;
	
	public IdCache(int billId, int bookingId, int employeeId, int roomMajorId, int roomMinorId) {
		this.billId = billId;
		this.bookingId = bookingId;
		this.employeeId = employeeId;
		this.roomMajorId = roomMajorId;
		this.roomMinorId = roomMinorId;
		
		idNames = new ArrayList<Tuple<String, Integer>>();
		idNames.add(new Tuple<String, Integer>("BillId", billId));
		idNames.add(new Tuple<String, Integer>("BookingId", bookingId));
		idNames.add(new Tuple<String, Integer>("EmployeeId", employeeId));
		idNames.add(new Tuple<String, Integer>("RoomMajorId", roomMajorId));
		idNames.add(new Tuple<String, Integer>("RoomMinorId", roomMinorId));
		
		
	}
	
	public ArrayList<Tuple<String, Integer>> getTagNamesWithValues() {
		return idNames;
	}

}
