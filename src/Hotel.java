import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Hotel {
	
	private Address address;
	private int hotelId;
	
	private HashMap<Integer, Employee> employeesTable;
	private HashMap<Integer, Booking> bookingsTable;
	private HashMap<Integer, Room> roomsTable;
	 
		
	public Hotel(int id) {
		roomsTable = new HashMap<Integer, Room>();
		bookingsTable = new HashMap<Integer, Booking>();
		employeesTable = new HashMap<Integer, Employee>();
		
		address = new Address("6030 Ilif Northwest", "Albuquerque", "NM", "87121");
		hotelId = id;			
	}
	
	public String getAddress() {
		return address.getAddressString();
	}
	
	public void addEmployee(Employee newEmployee) {
		employeesTable.put(newEmployee.getId(), newEmployee);	
	}
	
	public void addBooking(Booking newBooking) {
		bookingsTable.put(newBooking.getId(), newBooking);
	}
	
	public void addRoom(Room newRoom) {
		roomsTable.put(newRoom.getId(), newRoom);
	}
	
	public String listEmployees() {
		return listHashMapElements(employeesTable);
	}
	
	public String listBookings() {
		return listHashMapElements(bookingsTable);
	}
	
	public String listRooms() {
		return listHashMapElements(roomsTable);
	}
	
	public String bookGuestWithResponseMessage(Guest guest, Room room) {
		try {
			bookGuest(guest, room);
			return "The reservation has been successfully booked!";
		}
		catch (Exception ex) {
			return "The reservation could not be booked.";
		}
	}
	
	private void bookGuest(Guest guest, Room room) {
		Booking newBooking = new Booking(guest);				
		newBooking.setRoom(room);
	}
	
	public ArrayList<Room> getCompatibleRooms(Guest guest) {
		GuestRoomMatcher guestRoomMatcher
			= new GuestRoomMatcher(guest, (ArrayList<Room>)setToArrayList(roomsTable.keySet()));
		return guestRoomMatcher.getListOfCompatibleRooms();
	}
	
	public String getEmployeeById(int id) {		
		return toStringWithNullCheck(employeesTable.get(id));
	}
	
	public String getBookingById(int id) {
		return toStringWithNullCheck(bookingsTable.get(id));
	}
	
	public String getRoomById(int id) {
		return toStringWithNullCheck(roomsTable.get(id));
	}
	
	private ArrayList<?> setToArrayList(Set<?> items) {
		return new ArrayList<Object>(items);			
	}
	
	private String toStringWithNullCheck(Object element) {
		if (element != null)
			return element.toString();		
		return "";
	}
	
	private String listHashMapElements(HashMap<Integer, ?> itemList) {
		final StringBuilder listing = new StringBuilder("");
		itemList.values().forEach(i -> listing.append(i.toString()));		
		return listing.toString();
	}
	
}
