public class Main {
	public static void main(String[] args) {
		// create birth dates
		BirthDate Johndate = new BirthDate(1, 1, 1990);
		BirthDate Karendate = new BirthDate(8, 3, 1995);
		BirthDate Suedate = new BirthDate(2, 12, 1996);
		BirthDate Bobdate = new BirthDate(10, 10, 1995);
		BirthDate Koraldate = new BirthDate(5, 1, 1964);

		// create subclass objects
		SalariedEmployee salariedEmployee = new SalariedEmployee("John", "Smith", "111-11-1111", 800.00, Johndate);
		HourlyEmployee hourlyEmployee = new HourlyEmployee("Karen", "Price", "222-22-2222", 16.75, 40, Karendate);
		CommissionEmployee commissionEmployee = new CommissionEmployee("Sue", "Jones", "333-33-3333", 10000, .06,
				Suedate);
		BasePlusCommissionEmployee basePlusCommissionEmployee = new BasePlusCommissionEmployee("Bob", "Lewis",
				"444-44-4444", 5000, .04, 300, Bobdate);
		PieceWorker pieceWorkerEmployee = new PieceWorker("Koral", "Vered", "555-55-5555", Koraldate, 10);

		/*System.out.println("Employees processed individually:");

		System.out.printf("%n%s%n%s: $%,.2f%n%n", salariedEmployee, "earned", salariedEmployee.earnings());
		System.out.printf("%s%n%s: $%,.2f%n%n", hourlyEmployee, "earned", hourlyEmployee.earnings());
		System.out.printf("%s%n%s: $%,.2f%n%n", commissionEmployee, "earned", commissionEmployee.earnings());
		System.out.printf("%s%n%s: $%,.2f%n%n", basePlusCommissionEmployee, "earned",
				basePlusCommissionEmployee.earnings()); */

		// create four-element Employee array
		Employee[] employees = new Employee[5];

		// initialize array with Employees
		employees[0] = salariedEmployee;
		employees[1] = hourlyEmployee;
		employees[2] = commissionEmployee;
		employees[3] = basePlusCommissionEmployee;
		employees[4] = pieceWorkerEmployee;

		System.out.printf("Employees processed polymorphically:%n%n");

		// generically process each element in array employees
		for (Employee currentEmployee : employees) {
			System.out.println(currentEmployee); // invokes toString

			// determine whether element is a BasePlusCommissionEmployee
			if (currentEmployee instanceof BasePlusCommissionEmployee) {
				// downcast Employee reference to
				// BasePlusCommissionEmployee reference
				BasePlusCommissionEmployee employee = (BasePlusCommissionEmployee) currentEmployee;

				employee.setBaseSalary(1.10 * employee.getBaseSalary());

				System.out.printf("new base salary with 10%% increase is: $%,.2f%n", employee.getBaseSalary());
			}

			System.out.printf("earned $%,.2f%n%n", currentEmployee.earnings());
		}

		// get type name of each object in employees array
		for (int j = 0; j < employees.length; j++) {
			System.out.printf("Employee %d is a %s%n", j, employees[j].getClass().getName());
		}
	}
}