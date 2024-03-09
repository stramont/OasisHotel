package HotelBase.employees;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import HotelBase.Address;
import HotelBase.IdFactory;

public abstract class Employee {
	
	private int id;
	protected double hourlyPay;
	private LocalDate startDate;
	private LocalDate terminationDate;
	
	private String name;	
	private LocalDate dateOfBirth;
	
	private LocalDateTime currentStartShiftTime;
	private LocalDateTime currentEndShiftTime;
	
	public Employee(String name, LocalDate dob, LocalDate startDate) {
		id = IdFactory.getNextEmployeeId();
		this.name = name;
		this.dateOfBirth = dob;
		this.startDate = startDate;
	}
	
	public int getId() {
		return id;
	}
	
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public void setTerminationDate(LocalDate terminationDate) {
		this.terminationDate = terminationDate;
	}
	
	public void startShift() throws Exception {
		if (currentStartShiftTime == null)
			currentStartShiftTime = LocalDateTime.now();
		else
			throw new Exception("Employee has already started shift!");
	}
	
	public void endShift() throws Exception {
		if (currentStartShiftTime != null)
			currentEndShiftTime = LocalDateTime.now();
		else
			throw new Exception("Employee has not started their shift yet!");
	}
	
	public void enterHoursManual(LocalDateTime shiftStart, LocalDateTime shiftEnd) {
		currentStartShiftTime = shiftStart;
		currentEndShiftTime = shiftEnd;
	}
	
	public long getCurrentShiftHoursAndResetShift() throws Exception {
		if (!isShiftValid())
			throw new Exception("The shift is not valid.  Please re-enter the employee's hours");
		
		long hours = ChronoUnit.HOURS.between(currentStartShiftTime, currentEndShiftTime);
		currentStartShiftTime = null;
		currentEndShiftTime = null;
		
		return hours;	
	}
	
	private boolean isShiftValid() {
		long hours = ChronoUnit.HOURS.between(currentStartShiftTime, currentEndShiftTime);
		return currentStartShiftTime != null && currentEndShiftTime != null && hours > 0;
	}
	
	public boolean isCurrentlyWorking() {
		if (currentStartShiftTime != null && currentEndShiftTime == null)
			return true;
		
		return false;
	}
	
	protected abstract String getPositionName();

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee Id: " + id + "\n");
		builder.append("Employee Name: " + name + "\n");
		builder.append("Employee Position: " + this.getPositionName() + "\n");
		builder.append("On Shift: " + isCurrentlyWorking() + "\n");
		
		return builder.toString();
		
	}
	
	
	
	
	

}
