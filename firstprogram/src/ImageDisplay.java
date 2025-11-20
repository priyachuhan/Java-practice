import javax.swing.*;
        import java.awt.*;

public class ImageDisplay extends JFrame {
    public ImageDisplay() {
        setTitle("Image Display");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        ImageIcon imageIcon = new ImageIcon("C:\\Users\\LENOVO\\Downloads"); // Change to your image path
        JLabel label = new JLabel(imageIcon);

        add(label, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ImageDisplay();
    }
}
