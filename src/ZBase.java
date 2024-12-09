import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class ZBase extends JFrame {
    public ZBase() {
        // Add window listener to handle form closing
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // Check if the closed window is not the main form
                if (!(e.getSource() instanceof MainForm)) {
                    // Find and show the main form
                    showMainForm();
                }
            }
        });
    }

    private void showMainForm() {
        // Iterate through all frames to find the main form
        for (java.awt.Frame frame : java.awt.Frame.getFrames()) {
            if (frame instanceof MainForm) {
                frame.setVisible(true);
                break;
            }
        }
    }
}