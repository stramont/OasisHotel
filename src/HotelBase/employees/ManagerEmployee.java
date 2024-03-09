package HotelBase.employees;

import java.time.LocalDate;

import HotelBase.Address;

public class ManagerEmployee extends Employee {
	
	public ManagerEmployee(String name, LocalDate dob, LocalDate startDate) {
		super(name, dob, startDate);
		hourlyPay = 60.00;
	}
	
	protected String getPositionName() {
		return "Manager";
	}

}
