// BeanIntrospectDemo.java
import java.beans.*;

public class BeanIntrospectDemo {
    public static void main(String[] args) {
        try {
            // Introspect the Student class
            BeanInfo info = Introspector.getBeanInfo(Student.class);

            System.out.println("---- Property Descriptors ----");
            for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
                System.out.println("Property: " + pd.getName());
                System.out.println("  Read Method: " + pd.getReadMethod());
                System.out.println("  Write Method: " + pd.getWriteMethod());
            }

            System.out.println("\n---- Method Descriptors ----");
            for (MethodDescriptor md : info.getMethodDescriptors()) {
                System.out.println("Method: " + md.getMethod().getName());
            }

            System.out.println("\n---- Event Set Descriptors ----");
            for (EventSetDescriptor ed : info.getEventSetDescriptors()) {
                System.out.println("Event: " + ed.getName());
                System.out.println("  Listener Type: " + ed.getListenerType());
                System.out.println("  Add Listener Method: " + ed.getAddListenerMethod());
                System.out.println("  Remove Listener Method: " + ed.getRemoveListenerMethod());
            }

        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }
}

