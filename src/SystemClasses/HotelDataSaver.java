package SystemClasses;
import java.util.ArrayList;

import HotelBase.IdCache;
import HotelBase.Booking;
import HotelBase.Room;
import HotelBase.employees.Employee;


public class HotelDataSaver {
	
	//change these to xml strings to send to XMLWriter! We shouldn't need these variables here
	private ArrayList<Employee> employees;
	private ArrayList<Room> rooms;
	private ArrayList<Booking> bookings;
	
	private IdCache idCache;
	
	private String saveFilePath;
	
	public HotelDataSaver() {
		saveFilePath = "C:\\Users\\stram\\Documents\\test.xml";
		
	}
	
	public void saveData() throws Exception {
		if (!isRequiredDataSet())
			throw new Exception("The Data Saver is missing required data! (Have you set all the required fields?)");
		
		XMLWriter writer = new XMLWriter(saveFilePath);
		writer.open();
		writer.writeEmployees(employees);
		writer.writeRooms(rooms);
		writer.writeBookings(bookings);
		writer.writeIds(idCache);
		writer.saveAndClose();
	}
	
	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}
	
	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
		//writer.writeToXMLFile();
	}
	
	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}
	
	public void setIds(IdCache idCache) {
		this.idCache = idCache;
	}
	
	private boolean isRequiredDataSet() {
		return employees != null && rooms != null && bookings != null && idCache != null;
	}
	
	

}
