public class Student implements Comparable<Student> {
    
    private String firstName;
    private String lastName;
    private String id;
    private String birthYear;
    
    public Student(String firstName, String lastName, String id, String birthYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.birthYear = birthYear;
    }

    @Override
    public int compareTo(Student student) {
        return id.compareTo(student.id);
    }

    @Override
    public String toString() {
        return "name: " + firstName + " " + lastName + ", id: " + id + ", birth year: " + birthYear;
    }
}