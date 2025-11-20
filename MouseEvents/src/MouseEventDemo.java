 import javax.swing.*;
        import java.awt.event.*;

public class MouseEventDemo extends JFrame implements MouseListener {
    JLabel label;

    public MouseEventDemo() {
        setTitle("Mouse Event Demo");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        label = new JLabel("Click anywhere...", JLabel.CENTER);
        add(label);
        addMouseListener(this);
        setVisible(true);
    }

    public void mouseClicked(MouseEvent e) {
        label.setText("Mouse Clicked at (" + e.getX() + ", " + e.getY() + ")");
    }

    public void mousePressed(MouseEvent e) {
        label.setText("Mouse Pressed");
    }

    public void mouseReleased(MouseEvent e) {
        label.setText("Mouse Released");
    }

    public void mouseEntered(MouseEvent e) {
        label.setText("Mouse Entered the window");
    }

    public void mouseExited(MouseEvent e) {
        label.setText("Mouse Exited the window");
    }

    public static void main(String[] args) {
        new MouseEventDemo();
    }
}
