import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread for safety
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create and show the main window
                new MainFrame().setVisible(true);
            }
        });
    }
}

