package SystemClasses;
import java.util.ArrayList;

import HotelBase.Employee;
import HotelBase.Booking;
import HotelBase.Room;


public class HotelDataSaver {
	
	//change these to xml strings to send to XMLWriter! We shouldn't need these variables here
	private ArrayList<Employee> employees;
	private ArrayList<Room> rooms;
	private ArrayList<Booking> bookings;
	
	private String saveFilePath;
	
	public HotelDataSaver() {
		saveFilePath = "C:\\Users\\stram\\Documents\\test.xml";
		
	}
	
	public void saveData() throws Exception {
		if (!isRequiredDataSet())
			throw new Exception("The Data Saver is missing required data! (Have you set all the required fields?)");
		
		XMLWriter writer = new XMLWriter(saveFilePath);
		writer.writeToXMLFile();
	}
	
	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}
	
	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
		writer.writeToXMLFile();
	}
	
	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}
	
	public void setIds(String idsXML) {
		
	}
	
	private boolean isRequiredDataSet() {
		return employees != null && rooms != null && bookings != null;
	}
	
	

}
