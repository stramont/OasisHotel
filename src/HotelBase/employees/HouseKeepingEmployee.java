package HotelBase.employees;

import java.time.LocalDate;

import HotelBase.Address;

public class HouseKeepingEmployee extends Employee {
	
	public HouseKeepingEmployee(String name, LocalDate dob, LocalDate startDate) {
		super(name, dob, startDate);		
		hourlyPay = 30.0;
	}
	
	protected String getPositionName() {
		return "House Keeping";
	}

}
