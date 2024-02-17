package HotelBase;
import java.time.LocalDate;

public abstract class Employee {
	
	enum PayType {
		HOURLY,
		SALARY
	}
	
	private int id;
	private PayType employeePayType;
	private double pay;
	private LocalDate startDate;
	private LocalDate terminationDate;
	
	private String name;	
	private LocalDate dateOfBirth;
	private SSN ssn;
	private Address currentAddress;
	
	public int getId() {
		return id;
	}
	
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public void setTerminationDate(LocalDate terminationDate) {
		this.terminationDate = terminationDate;
	}
	
	private void setName(String name) {
		this.name = name;
	}
	
	private void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	private void setCurrentAddress(Address currentAddress) {
		this.currentAddress = currentAddress;
	}
	
	private void setSSN(String socialSecNo) {
		//ssn.setNumber(socialSecNo);
	}
	
	
	
	
	

}
