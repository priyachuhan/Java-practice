        import javax.swing.*;
        import java.awt.*;

public class MenuExample extends JFrame {
    public MenuExample() {
        setTitle("MenuBar, Menu and MenuItems");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");

        JMenu saveAsMenu = new JMenu("Save-as");
        JMenuItem jpegItem = new JMenuItem(".jpeg");
        JMenuItem pngItem = new JMenuItem(".png");
        JMenuItem pdfItem = new JMenuItem(".pdf");

        saveAsMenu.add(jpegItem);
        saveAsMenu.add(pngItem);
        saveAsMenu.add(pdfItem);

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsMenu);

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MenuExample();
    }
}

