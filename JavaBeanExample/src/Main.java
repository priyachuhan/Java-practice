// Main.java
public class Main {
    public static void main(String[] args) {
        // Create Student bean object
        Student student = new Student();

        // Set properties using setters
        student.setName("Priya Chauhan");
        student.setAge(21);

        // Access properties using getters
        System.out.println("Student Name: " + student.getName());
        System.out.println("Student Age: " + student.getAge());
    }
}
