package SystemClasses;

import java.util.ArrayList;

import HotelBase.Employee;
import HotelBase.Room;
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
	
	private void tryOpenReader() {
		if (!reader.isOpen())
			reader.open();
	}
}