package HotelBase;

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

}
