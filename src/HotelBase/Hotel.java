package HotelBase;
import SystemClasses.HotelDataSaver;
import SystemClasses.HotelDataLoader;
import java.util.ArrayList;

public class Hotel {
	
	private Address address;
	private int hotelId;
	
	private ArrayList<Employee> employees;
	private ArrayList<Booking> bookings;
	private ArrayList<Room> rooms;
	 
		
	public Hotel(int id) {
		rooms = new ArrayList<Room>();
		bookings = new ArrayList<Booking>();
		employees = new ArrayList<Employee>();
		
		address = new Address("6030 Ilif Northwest", "Albuquerque", "NM", "87121");
		hotelId = id;			
	}
	
	public void saveHotelData() throws Exception {
		HotelDataSaver dataSaver = new HotelDataSaver();
		dataSaver.setEmployees(employees);
		dataSaver.setRooms(rooms);
		dataSaver.setBookings(bookings);
		dataSaver.setIds(IdFactory.getAllIds());
		dataSaver.saveData();
	}
	
	public void loadHotelData() throws Exception {
		HotelDataLoader dataLoader = new HotelDataLoader();
		IdFactory.setAllIds(dataLoader.getIdCache());
		employees = dataLoader.getEmployees();
		rooms = dataLoader.getRooms();		
		bookings = dataLoader.getBookings();
	}
	
	public String getAddress() {
		return address.getAddressString();
	}
	
	public void addEmployee(Employee newEmployee) {
		employees.add(newEmployee);	
	}
	
	public void addBooking(Booking newBooking) {
		bookings.add(newBooking);
	}
	
	public void addRoom(Room newRoom) {
		rooms.add(newRoom);
	}
	
	public String listEmployees() {
		return buildEmployeesList();
	}
	
	public String listBookings() {
		return buildBookingsList();
	}
	
	public String listRooms() {
		return buildRoomsList();
	}
	
	public void changeRoomCost(int roomId, int newCost) throws Exception {
		Room r = getRoomById(roomId);		
		r.setCostPerNight(newCost);		
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
			= new GuestRoomMatcher(guest, rooms);
		return guestRoomMatcher.getListOfCompatibleRooms();
	}
	
	public Employee getEmployeeById(int id) throws Exception {		
		for (Employee e : employees) {
			if (e.getId() == id)
				return e;
		}
		
		throw new Exception("Employee with id " + id + " does not exists");
	}
	
	public Booking getBookingById(int id) throws Exception {
		for (Booking b : bookings) {
			if (b.getId() == id)
				return b;
		}
		
		throw new Exception("Booking with id " + id + " does not exists");
	}
	
	public Room getRoomById(int id) throws Exception {
		for (Room r : rooms) {
			if (r.getId() == id)
				return r;
		}
		
		throw new Exception("Room with id " + id + " does not exists");
	}
	
	private String buildEmployeesList() {
		StringBuilder listing = new StringBuilder("");
		
		for (Employee e : employees)
			listing.append(e.toString() + "\n");
		
		return listing.toString();
	}
	
	private String buildRoomsList() {
		StringBuilder listing = new StringBuilder("");
		
		for (Room r : rooms)
			listing.append(r.toString() + "\n");
		
		return listing.toString();
	}
	
	private String buildBookingsList() {
		StringBuilder listing = new StringBuilder("");
		
		for (Booking b : bookings)
			listing.append(b.toString() + "\n");
		
		return listing.toString();
	}

	
}
