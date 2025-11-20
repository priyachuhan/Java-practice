public class App {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new gui.MainFrame().setVisible(true);
        });
    }
}

