// Student.java
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Student {
    private String name;
    private int age;

    public Student() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Method to demonstrate MethodDescriptor
    public void display() {
        System.out.println("Student: " + name + ", Age: " + age);
    }

    // Dummy add/remove listener methods for EventSetDescriptor
    public void addActionListener(ActionListener listener) {
        System.out.println("ActionListener added");
    }

    public void removeActionListener(ActionListener listener) {
        System.out.println("ActionListener removed");
    }
}

