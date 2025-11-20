import javax.swing.*;

public class NestedMenuExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Nested Menu Example");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();

        // Main menu
        JMenu fileMenu = new JMenu("File");

        // Submenu
        JMenu newMenu = new JMenu("New");

        // Submenu items
        JMenuItem projectItem = new JMenuItem("Project");
        JMenuItem classItem = new JMenuItem("Class");
        newMenu.add(projectItem);
        newMenu.add(classItem);

        // Other items in main menu
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem exitItem = new JMenuItem("Exit");

        // Add submenu and items to main menu
        fileMenu.add(newMenu);
        fileMenu.add(openItem);
        fileMenu.addSeparator(); // separator line
        fileMenu.add(exitItem);

        // Add main menu to menu bar
        menuBar.add(fileMenu);

        // Set menu bar to frame
        frame.setJMenuBar(menuBar);

        frame.setVisible(true);
    }
}

