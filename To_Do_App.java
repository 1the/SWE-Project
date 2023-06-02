//<--4,2-->
package engez;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class To_Do_App extends JFrame {

    // costractor
    public To_Do_App() {
        // Create the main frame
        JFrame frame = new JFrame("To-Do-App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        // Create a panel to hold the buttons
        JPanel panel = new JPanel();

        // Create the buttons
        JButton tasksButton = new JButton("Tasks Menu");
        JButton notesButton = new JButton("Notes Menu");
        JButton backButton = new JButton("Back");

        // Add ActionListener to Tasks List button
        tasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Execute the code for "Tasks List" button
                ToDoAppGUI toDoAppGUI = new ToDoAppGUI(frame);
                toDoAppGUI.setVisible(true);
                frame.setVisible(false);
            }
        });

        // Add ActionListener to the Notes button
        notesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Execute the code for "Notes" button
                frame.setVisible(false); // Hide the main menu
                NoteAppGUI notesGUI = new NoteAppGUI(frame);
                notesGUI.setVisible(true);
            }

            
        });

        // Add ActionListener to the Back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Execute the code for "Back" button
                new Todo_Focus();
                frame.setVisible(false); // Hide the main menu
            }
        });

        // Add the buttons to the panel
        panel.add(tasksButton);
        panel.add(notesButton);
        panel.add(backButton);

        // Add the panel to the frame
        frame.add(panel);

        // Set the frame size
        frame.setSize(400, 100);

        // Center the frame
        frame.setLocationRelativeTo(null);
        setLocationRelativeTo(null); // Center the window
        

        // Make the frame visible
        frame.setVisible(true);
    }
}