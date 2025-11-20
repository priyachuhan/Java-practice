import javax.swing.*;
import java.awt.*;

public class GridLayoutDemo {

public static void main(String[] args) {
    JFrame frame = new JFrame("GridLayout Demo");
    frame.setLayout(new GridLayout(2, 3));

    for (int i = 1; i <= 6; i++) {
        frame.add(new JButton("Button " + i));
    }

    frame.setSize(400, 150);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
}
}

