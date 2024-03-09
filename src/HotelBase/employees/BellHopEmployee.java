package HotelBase.employees;

import java.time.LocalDate;

import HotelBase.Address;

public class BellHopEmployee extends Employee{
	
	public BellHopEmployee(String name, LocalDate dob, LocalDate startDate) {
		super(name, dob, startDate);
		hourlyPay = 20.0;
	}
	
	protected String getPositionName() {
		return "Bell Hop";
	}

}
