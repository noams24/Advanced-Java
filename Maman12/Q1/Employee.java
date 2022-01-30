import java.util.Calendar;

public abstract class Employee {
	private final String firstName;
	private final String lastName;
	private final String socialSecurityNumber;
	private BirthDate date;

	// constructor
	public Employee(String firstName, String lastName, String socialSecurityNumber, BirthDate date) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.socialSecurityNumber = socialSecurityNumber;
		this.date = new BirthDate(date);
	}

	// return first name
	public String getFirstName() {
		return firstName;
	}

	// return last name
	public String getLastName() {
		return lastName;
	}

	// return social security number
	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public int GetBirthdayBonus() {
		Calendar curDate = java.util.Calendar.getInstance();
		int month = curDate.get(Calendar.MONTH) + 1;
		int bonus = 0;
		if (month == this.date.GetMonth()) {
			System.out.println("Happy Birthday!  You got bonus ");
			bonus = 200;
		}
		return bonus;
	}

	// return String representation of Employee object
	@Override
	public String toString() {
		return String.format("%s %s%nsocial security number: %s", getFirstName(), getLastName(),
				getSocialSecurityNumber());
	}

	// abstract method must be overridden by concrete subclasses
	public abstract double earnings(); // no implementation here
}