import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {
    private JList<String> lBoxOptions;

    public MainForm() {
        // Set up the main frame
        setTitle("Data Structures and Algorithms");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the list of options
        String[] options = {
                "Lists",
                "Stacks",
                "Queues",
                "Trees",
                "Graphs",
                "Algorithms"
        };

        lBoxOptions = new JList<>(options);
        lBoxOptions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add list selection listener
        lBoxOptions.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                openSelectedForm(lBoxOptions.getSelectedIndex());
            }
        });

        // Add list to scrollpane
        JScrollPane scrollPane = new JScrollPane(lBoxOptions);

        // Set layout
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    private void openSelectedForm(int selectedIndex) {
        switch (selectedIndex) {
            case 0:
                openForm(new ListsForm());
                break;
            case 1:
                openForm(new StacksForm());
                break;
            case 2:
                openForm(new QueuesForm());
                break;
            case 3:
                openForm(new TreesForm());
                break;
            case 4:
               openForm(new GraphsForm());
                break;
            case 5:
               // openForm(new AlgorithmsForm());
                break;
        }
    }

    private void openForm(JFrame form)
    {
        form.setVisible(true);
        this.setVisible(false);
    }


    public static void main(String[] args)
    {
        // Ensure GUI is created on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new MainForm().setVisible(true);
        });
    }
}