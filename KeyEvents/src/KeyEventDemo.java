 import javax.swing.*;
        import java.awt.event.*;

public class KeyEventDemo extends JFrame implements KeyListener {
    JLabel label;

    public KeyEventDemo() {
        setTitle("Key Event Demo");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        label = new JLabel("Press any key...", JLabel.CENTER);
        add(label);
        addKeyListener(this);
        setFocusable(true); // Important for key events
        setVisible(true);
    }

    public void keyTyped(KeyEvent e) {
        label.setText("Key Typed: " + e.getKeyChar());
    }

    public void keyPressed(KeyEvent e) {
        label.setText("Key Pressed: " + e.getKeyChar());
    }

    public void keyReleased(KeyEvent e) {
        label.setText("Key Released: " + e.getKeyChar());
    }

    public static void main(String[] args) {
        new KeyEventDemo();
    }
}
