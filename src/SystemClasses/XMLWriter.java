package SystemClasses;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

public class XMLWriter {
	
	private String saveFilePath;
	
	private String role1 = "test1";
	private String role2 = "test2";
	private String role3 = "test3";
	private ArrayList<String> rolev;
	
	public XMLWriter(String saveFilePath) {
		this.saveFilePath = saveFilePath;
	}
	
	public void writeToXMLFile() {
		
		Document dom;
	    Element e = null;

	    // instance of a DocumentBuilderFactory
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    try {
	        // use factory to get an instance of document builder
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        // create instance of DOM
	        dom = db.newDocument();

	        // create the root element
	        Element rootEle = dom.createElement("HotelData");

	        // create data elements and place them under root
	        e = dom.createElement("Rooms");
	        e.appendChild(dom.createTextNode(role1));
	        rootEle.appendChild(e);

	        e = dom.createElement("Employees");
	        e.appendChild(dom.createTextNode(role2));
	        rootEle.appendChild(e);

	        e = dom.createElement("Bookings");
	        e.appendChild(dom.createTextNode(role3));
	        rootEle.appendChild(e);

	        dom.appendChild(rootEle);

	        try {
	            Transformer tr = TransformerFactory.newInstance().newTransformer();
	            tr.setOutputProperty(OutputKeys.INDENT, "yes");
	            tr.setOutputProperty(OutputKeys.METHOD, "xml");
	            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

	            // send DOM to file
	            tr.transform(new DOMSource(dom), 
	                                 new StreamResult(new FileOutputStream(saveFilePath)));

	        } catch (TransformerException te) {
	            System.out.println(te.getMessage());
	        } catch (IOException ioe) {
	            System.out.println(ioe.getMessage());
	        }
	    } catch (ParserConfigurationException pce) {
	        System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
	    }
        
	}
	
	
	

}
