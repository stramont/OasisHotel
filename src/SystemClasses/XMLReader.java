package SystemClasses;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

import HotelBase.Employee;
import HotelBase.Room;
import HotelBase.Booking;


public class XMLReader {

	private String loadFilePath;
	private boolean isOpen;
	
	private Document dom;
	
	
	public XMLReader(String loadFilePath) {
		this.loadFilePath = loadFilePath;
		isOpen = false;
	}
	
	public void open() {
		
		try {
			File file = new File(loadFilePath);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			dom = db.parse(file);
			
			isOpen = true;
		}
		catch (Exception ex) {
			System.out.println("Failed to open XML Reader!");
			System.out.println(ex.getMessage());
		}
		
	}
	
	public ArrayList<Employee> readEmployees() {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		
		return employees;
		
	}
	
	public ArrayList<Room> readRooms() {
		ArrayList<Room> rooms = new ArrayList<Room>();
		
		NodeList nList = dom.getElementsByTagName("Employee");
		
		for (int nodeIndex = 0; nodeIndex < nList.getLength(); nodeIndex++) {
			Node n = nList.item(nodeIndex);
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				Element elt = (Element) n;
				
			}
		}
		
		
		return rooms;
	}
	
	public ArrayList<Booking> readBookings() {
		ArrayList<Booking> bookings = new ArrayList<Booking>();
		
		return bookings;
	}
	
	public void close() {
		isOpen = false;
	}
}
