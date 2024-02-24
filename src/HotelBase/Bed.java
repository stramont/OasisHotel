package HotelBase;
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
	
	

}
