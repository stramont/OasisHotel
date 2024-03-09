package SystemClasses;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

import HotelBase.IdCache;
import HotelBase.Room;
import HotelBase.employees.Employee;
import HotelBase.Booking;


public class XMLReader {

	private String loadFilePath;
	private boolean isOpen;
	
	private Document dom;
	
	
	public XMLReader(String loadFilePath) {
		this.loadFilePath = loadFilePath;
		isOpen = false;
	}
	
	public boolean isOpen() {
		return isOpen;
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
		
		NodeList nList = dom.getElementsByTagName("Room");
		
		for (int nodeIndex = 0; nodeIndex < nList.getLength(); nodeIndex++) {
			Node n = nList.item(nodeIndex);
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				Element elt = (Element) n;
				rooms.add(Room.GetRoomFromXML(elt));				
			}
		}
		
		
		return rooms;
	}
	
	public ArrayList<Booking> readBookings() {
		ArrayList<Booking> bookings = new ArrayList<Booking>();
		
		return bookings;
	}
	
	public IdCache readIds() throws Exception {
		int billId = 0;
		int bookingId = 0;
		int employeeId = 0;
		int roomMajorId = 0;
		int roomMinorId = 0;
		
		NodeList nList = dom.getElementsByTagName("IdCache");
		if (nList.getLength() >= 1) {
			Element parent = (Element) nList.item(0);
			NodeList children = parent.getChildNodes();
			for (int i = 0; i < children.getLength(); i++) {
				if (children.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element child = (Element) (children.item(i));
					
					switch (child.getTagName()) {
					case "BillId":
						billId = Integer.parseInt(child.getChildNodes().item(0).getTextContent());
						break;
					case "BookingId":
						bookingId = Integer.parseInt(child.getChildNodes().item(0).getTextContent());
						break;
					case "EmployeeId":
						employeeId = Integer.parseInt(child.getChildNodes().item(0).getTextContent());
						break;
					case "RoomMajorId":
						roomMajorId = Integer.parseInt(child.getChildNodes().item(0).getTextContent());
						break;
					case "RoomMinorId":
						roomMinorId = Integer.parseInt(child.getChildNodes().item(0).getTextContent());
						break;
					default:
						throw new Exception("Unknown Id tag encountered -- aborting process.");
						
					}
				}

			}

		}
		else {
			throw new Exception("Could not fetch Id Cache!");
		}
		
		return new IdCache(billId, bookingId, employeeId, roomMajorId, roomMinorId);
		
	}
	
	public void close() {
		isOpen = false;
	}
}
