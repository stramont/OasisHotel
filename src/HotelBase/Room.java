package HotelBase;
import java.util.ArrayList;
import org.w3c.dom.*;

public class Room {
	
	enum RoomType {
			NORMAL,
			SUITE,
			EXECECUTIVE_SUITE,
			PRESIDENTIAL_SUITE,
			PENTHOUSE			
	}
	
	private int roomId; //Also the real-life room number
	private ArrayList<Bed> beds;
	private int bathroomCount;
	private RoomType roomType;
	private boolean adaAccessible;
	private boolean vacant;
	private int costPerNight;
	
	private Guest currentGuest;
	private ArrayList<Guest> upcomingGuests;
	
	public Room(ArrayList<Bed> beds, int bathroomCount, RoomType roomType) throws Exception {
		roomId = IdFactory.getNextRoomId();
		this.beds = beds;
		this.bathroomCount = bathroomCount;
		this.roomType = roomType;
		adaAccessible = false;
		vacant = true;
		costPerNight = 100;
		upcomingGuests = new ArrayList<Guest>();
	}
	
	private Room(int id, RoomType roomType, int bathroomCount, ArrayList<Bed> beds, 
					boolean adaAccessible, boolean vacant, int costPerNight) {
		roomId = id;
		this.roomType = roomType;
		this.bathroomCount = bathroomCount;
		this.beds = beds;
		this.adaAccessible = adaAccessible;
		this.vacant = vacant;
		this.costPerNight = costPerNight;
	}
	
	public static Room GetRoomFromXML(Element elt) {
		return new Room(
					Integer.parseInt(elt.getElementsByTagName("Id").item(0).getTextContent()),
					RoomType.valueOf(elt.getElementsByTagName("RoomType").item(0).getTextContent()),
					Integer.parseInt(elt.getElementsByTagName("BathroomCount").item(0).getTextContent()),
					Bed.getBedsFromXML((Element)elt.getElementsByTagName("Beds").item(0)),
					Boolean.valueOf(elt.getElementsByTagName("ADAAccessible").item(0).getTextContent()),
					Boolean.valueOf(elt.getElementsByTagName("Vacant").item(0).getTextContent()),
					Integer.parseInt(elt.getElementsByTagName("Cost").item(0).getTextContent())					
				);					
	}
	
	public int getId() {
		return roomId;
	}
	
	public ArrayList<Bed> getBeds() {
		return beds;
	}
	
	public int getBathroomCount() {
		return bathroomCount;
	}
	
	public RoomType getRoomType() {
		return roomType;
	}
	
	public boolean isVacant() {
		return vacant;
	}
	
	public void makeAdaAccessible() {
		adaAccessible = true;
	}
	
	public void removeAdaAccessible() {
		adaAccessible = false;
	}
	
	public void setCostPerNight(int costPerNight) {
		this.costPerNight = costPerNight;
	}
	
	public void addUpcomingGuest(Guest upcomingGuest) {
		upcomingGuests.add(upcomingGuest);
	}
	
	public void updateCurrentGuest() {
		if (currentGuest != null && currentGuest.isGuestCheckedOut())
			currentGuest = null;
			
		if (!upcomingGuests.isEmpty() && upcomingGuests.get(0).isGuestCheckedIn()) {
			currentGuest = upcomingGuests.remove(0);
			vacant = false;
		}
	}
	
	public boolean isOpenForGuest(GuestCheckInOutRecord checkInOutRecord) {
		if (currentGuest != null && currentGuest.getCheckInOutRecord().doRecordsOverlap(checkInOutRecord))
			return false;
		
		for (Guest upcomingGuest : upcomingGuests) {
			if (upcomingGuest.getCheckInOutRecord().doRecordsOverlap(checkInOutRecord))
				return false;
		}
		
		return true;
	}
		
	public int getSumOfPeoplePerBed() {
		int sumOfBedSpace = 0;
		
		for (Bed bed : beds)
			sumOfBedSpace += bed.getBedSpace();
				
		return sumOfBedSpace;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder("");
		
		builder.append("Room Number (ID): " + roomId + "\n");
		builder.append("Room Type: " + roomType.name() + "\n");
		builder.append("Current Cost: $" + costPerNight + "\n");
		builder.append("Bed Count: " + beds.size() + "\n");
		builder.append("Beds: ");		
		for (Bed b : beds) {
			builder.append(b.toString());
			builder.append("|");
		}
		builder.deleteCharAt(builder.length() - 1);
		builder.append("\n");
		builder.append("Bathroom Count: " + bathroomCount + "\n");
		builder.append("ADA Accessible: " + adaAccessible + "\n");
		
		
		return builder.toString();
	}

	
	public Node getXMLNode(Document dom) {
		Element roomElt = dom.createElement("Room");
		
		Element idElt = dom.createElement("Id");
		idElt.appendChild(dom.createTextNode(Integer.toString(roomId)));
		roomElt.appendChild(idElt);
		
		Element roomTypeElt = dom.createElement("RoomType");
		roomTypeElt.appendChild(dom.createTextNode(roomType.name()));
		roomElt.appendChild(roomTypeElt);
		
		Element costElt = dom.createElement("Cost");
		costElt.appendChild(dom.createTextNode(Integer.toString(costPerNight)));
		roomElt.appendChild(costElt);		
		
		Element bathroomCountElt = dom.createElement("BathroomCount");
		bathroomCountElt.appendChild(dom.createTextNode(Integer.toString(bathroomCount)));
		roomElt.appendChild(bathroomCountElt);
		
		Element adaElt = dom.createElement("ADAAccessible");
		adaElt.appendChild(dom.createTextNode(Boolean.toString(adaAccessible)));
		roomElt.appendChild(adaElt);
		
		Element vacantElt = dom.createElement("Vacant");
		vacantElt.appendChild(dom.createTextNode(Boolean.toString(vacant)));
		roomElt.appendChild(vacantElt);
		
		Element bedsElt = dom.createElement("Beds");
		for (Bed b : beds)
			bedsElt.appendChild(b.getXMLNode(dom));
		roomElt.appendChild(bedsElt);
		
		//to do: guest info, etc.
		
		
		return roomElt;
	}
	
}
