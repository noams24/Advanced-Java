public class PieceWorker extends Employee {
	private int items;
	private final int itemPrice = 30;

	public PieceWorker(String firstname, String lastName, String socialSecurityNumber, BirthDate date, int items) {
		super(firstname, lastName, socialSecurityNumber, date);

		if (items < 0) {
			throw new IllegalArgumentException("items must be >= 0");
		}
		this.items = items;
	}

	public int getItems() {
		return this.items;
	}

	@Override
	public double earnings() {
		return getItems() * itemPrice + GetBirthdayBonus();
	}

	public String toString() {
		return String.format("piece employee: %s%n%s: %s%s; %s: %s", super.toString(), "created", getItems(), " items",
				"item's price", itemPrice);
	}
}
