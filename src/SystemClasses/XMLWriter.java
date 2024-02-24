package SystemClasses;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

import HotelBase.Employee;
import HotelBase.Room;
import HotelBase.Booking;

public class XMLWriter {
	
	private String saveFilePath;
	
	private String role1 = "testo1";
	private String role2 = "testo2";
	private String role3 = "testo3";

	private Boolean isOpen;
	
	private Document dom;
	private Element rootElt;
	
	public XMLWriter(String saveFilePath) {
		this.saveFilePath = saveFilePath;
		isOpen = false;
	}
	
	public void open() {
		try {
			initializeDom();
			createRootElt();
			isOpen = true;
		}
		catch (Exception ex) {
			System.out.println("Could not open xml file to save data!");
			System.out.println(ex.getMessage());
		}		
	}
	
	private void initializeDom() throws Exception{		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		dom = db.newDocument();	
	}
	
	private void createRootElt() {
		rootElt = dom.createElement("HotelData");
	}	
	
	private void writeToFile() throws Exception {
		
	    Transformer tr = TransformerFactory.newInstance().newTransformer();
	    tr.setOutputProperty(OutputKeys.INDENT, "yes");
	    tr.setOutputProperty(OutputKeys.METHOD, "xml");
	    tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	    tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	    
	    FileOutputStream fileOutputStream = new FileOutputStream(saveFilePath);
	
	    // send DOM to file
	    tr.transform(new DOMSource(dom), new StreamResult(fileOutputStream));
	    
	    fileOutputStream.close();

        
	}		
	
	public void writeEmployees(ArrayList<Employee> employees) {
        Element e = dom.createElement("Employees");
        e.appendChild(dom.createTextNode(role1));
        rootElt.appendChild(e);        
	}
	
	public void writeRooms(ArrayList<Room> rooms) {
        Element e = dom.createElement("Rooms");
        for (Room r : rooms)
        	e.appendChild(r.getXMLNode(dom));
        rootElt.appendChild(e);
	}
	
	public void writeBookings(ArrayList<Booking> bookings) {
        Element e = dom.createElement("Bookings");
        e.appendChild(dom.createTextNode(role3));
        rootElt.appendChild(e);
	}
	
	public void saveAndClose() {
		
		try {			
			dom.appendChild(rootElt);
			//createCopyOfPreviousFile
			writeToFile();
			//createBackup
			
			isOpen = false;
		}
		catch (Exception ex) {
			System.out.println("There as an issue saving and closing the xml writer.");
			System.out.println(ex.getMessage());
		}
		
	}
	
	
	

}
