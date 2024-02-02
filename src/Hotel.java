import java.util.ArrayList;
import java.util.HashMap;

public class Hotel {
	
	private Address address;
	private int hotelId;
	
	private HashMap<Integer, Employee> employeesTable;
	private HashMap<Integer, Elevator> elevatorsTable;
	private HashMap<Integer, Booking> bookingsTable;
	private HashMap<Integer, Floor> floorsTable;
	 
		
	public Hotel(int id) {
		floorsTable = new HashMap<Integer, Floor>();
		elevatorsTable = new HashMap<Integer, Elevator>();
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
	
	public void addFloor(Floor newFloor) {
		floorsTable.put(newFloor.getId(), newFloor);
	}
	
	public void addElevator(Elevator newElevator) {
		elevatorsTable.put(newElevator.getId(), newElevator);
	}
	
	public String listEmployees() {
		return listHashMapElements(employeesTable);
	}
	
	public String listBookings() {
		return listHashMapElements(bookingsTable);
	}
	
	public String listFloors() {
		return listHashMapElements(floorsTable);
	}
	
	public String listElevators() {
		return listHashMapElements(elevatorsTable);
	}
	
	public String getEmployeeById(int id) {		
		return toStringWithNullCheck(employeesTable.get(id));
	}
	
	public String getBookingById(int id) {
		return toStringWithNullCheck(bookingsTable.get(id));
	}
	
	public String getFloorById(int id) {
		return toStringWithNullCheck(floorsTable.get(id));
	}
	
	public String getElevatorById(int id) {
		return toStringWithNullCheck(elevatorsTable.get(id));
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
