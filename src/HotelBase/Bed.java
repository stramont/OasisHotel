package HotelBase;
import java.util.ArrayList;

import org.w3c.dom.*;

public class Bed {
	
	enum BedType {
		FULL,
		QUEEN,
		KING
	}
	
	private BedType bedType;
	
	public Bed(BedType bedType) {
		this.bedType = bedType;
	}
	
	public int getBedSpace() {
		return bedType == BedType.FULL ? 1 : 2;
	}
	
	public String toString() {
		return bedType.name();
	}
	
	public Node getXMLNode(Document dom) {
		Element bedElt = dom.createElement("Bed");
		
		Element bedTypeElt = dom.createElement("BedType");
		bedTypeElt.appendChild(dom.createTextNode(bedType.name()));
		bedElt.appendChild(bedTypeElt);
		
		return bedElt;
	}
	
	public static ArrayList<Bed> getBedsFromXML(Element elt) {
		
		ArrayList<Bed> beds = new ArrayList<Bed>();		
		NodeList nList = elt.getElementsByTagName("Bed");
		
		for (int nodeIndex = 0; nodeIndex < nList.getLength(); nodeIndex++)
			beds.add(getBedFromXML((Element) nList.item(nodeIndex)));
		
		
		return beds;
	}
	
	private static Bed getBedFromXML(Element elt) {
		BedType bedType = BedType.valueOf(elt.getTextContent().trim());
		return new Bed(bedType);
	}
	
	

}
