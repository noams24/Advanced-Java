import java.util.Calendar;

public class BirthDate {
	private int day;
	private int month;
	private int year;

	public BirthDate(int day, int month, int year) {
		Calendar curDate = java.util.Calendar.getInstance();
		if (day <= 0 || month >= 13 || year > curDate.get(Calendar.YEAR))
			throw new IllegalArgumentException("Wrong date");
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public BirthDate(BirthDate date) {
		this.day = date.day;
		this.month = date.month;
		this.year = date.year;
	}

	public int GetMonth() {
		return this.month;
	}

	public String toString() {
		return String.format("Birthday: %s.%s.%s", this.day, this.month, this.year);
	}
}
