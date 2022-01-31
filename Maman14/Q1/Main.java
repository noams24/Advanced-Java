import java.util.Iterator;
import java.util.Map;

public class Main {
    
    public static void main(String[] args) {
    	
    	 Student[] students = new Student[3];
    	students[0] = new Student("Yakir","Jerbi","204325423","1998");
        students[1] = new Student("Maoz","Perez","112325423","1995");
        students[2] = new Student("Matan","Tarif","304532623","1993");
        String[] phoneNums = new String[]{"0502486325","0528642057","0505563227"};
        AssociationTable<Student, String> assoTable;
        try {
            assoTable = new AssociationTable<>(students,phoneNums);
            Student s4 = new Student("Harry","Potter","271236703","2001");
            assoTable.add(s4, "0528654793");
            Iterator<Map.Entry<Student,String>> iter = assoTable.keyIterator();
            while(iter.hasNext()) {
                Map.Entry<Student,String> next = iter.next();
                System.out.println("key - student: " + next.getKey());
                System.out.println("value - phone number: " + next.getValue() + "\n");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); 
        }
    }
}