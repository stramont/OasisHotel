package SystemClasses;

import java.util.ArrayList;

import HotelBase.IdCache;
import HotelBase.Room;
import HotelBase.employees.Employee;
import HotelBase.Booking;

public class HotelDataLoader {

	private String loadFilePath;
	private XMLReader reader;
	
	public HotelDataLoader() {
		loadFilePath = "C:\\Users\\stram\\Documents\\test.xml";
		reader = new XMLReader(loadFilePath);
	}
	
	public ArrayList<Employee> getEmployees() {
		tryOpenReader();		
		return reader.readEmployees();
	}
	
	public ArrayList<Room> getRooms() {
		tryOpenReader();
		return reader.readRooms();
	}
	
	public ArrayList<Booking> getBookings() {
		tryOpenReader();
		return reader.readBookings();
	}
	
	public IdCache getIdCache() throws Exception {
		tryOpenReader();
		return reader.readIds();
	}
	
	private void tryOpenReader() {
		if (!reader.isOpen())
			reader.open();
	}
}
