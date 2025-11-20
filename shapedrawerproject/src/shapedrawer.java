import javax.swing.*;
import java.awt.*;

    public class shapedrawer extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Rectangle
            g2d.setColor(Color.BLUE);
            g2d.fillRect(50, 50, 100, 70);

            // Circle
            g2d.setColor(Color.RED);
            g2d.fillOval(200, 50, 80, 80);

            // Polygon (Triangle)
            g2d.setColor(Color.GREEN);
            int[] xPoints = {100, 150, 50};
            int[] yPoints = {200, 250, 250};
            g2d.fillPolygon(xPoints, yPoints, 3);

            // Borders
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawRect(50, 50, 100, 70);
            g2d.drawOval(200, 50, 80, 80);
            g2d.drawPolygon(xPoints, yPoints, 3);
        }

        public static void main(String[] args) {
            JFrame frame = new JFrame("2D Shape Drawer");
            shapedrawer panel = new shapedrawer();

            frame.add(panel);
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
    }


