//<--3-->
package engez;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Todo_Focus extends JFrame implements ActionListener {

    //variables
    JButton todoButton,focusButton;
    NewPage page;

    Todo_Focus() {
        page = new NewPage();
        //visibility
        page.setVisible(true);
        
        //ToDo button
        todoButton = new JButton("ToDo");
        //Focus button
        focusButton = new JButton("Focus mode");

        //create a panel to hold the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 4, 4));
        buttonPanel.add(new JLabel());
        buttonPanel.add(todoButton);
        buttonPanel.add(new JLabel());
        buttonPanel.add(focusButton);
        add(buttonPanel);
        
        // main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);

        //events
        todoButton.addActionListener(this);
        focusButton.addActionListener(this);

        //add button panel to the center of the NewPage frame
        page.getContentPane().add(buttonPanel, BorderLayout.CENTER);
        // title
        setTitle("ToDo or Focus");
        //center the screen
        page.setLocationRelativeTo(null);
        //elements size is not related to window size
        page.setLayout(new FlowLayout());
        //maximize not allowed
        page.setResizable(false);
        //window size
        page.setSize(300,200);  //set size of the frame
        // close the frame on exit
        page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //go to todo / focus page
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==focusButton)
        {
            new Focus();
            //hide this page
            page.setVisible(false);
        }
        else if(e.getSource()==todoButton)
        {
            // open TO_DO_App page
            new To_Do_App();

            //hide this page
            page.setVisible(false);
        }
    }
}
