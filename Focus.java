//<--4,1-->
package engez;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Focus extends JFrame implements ActionListener {

    JButton sitesButton,appsButton,schedButton,backButton;
    NewPage page;

    Focus(){
        page = new NewPage();
        //visibility
        page.setVisible(true);

        //Block sites button
        sitesButton = new JButton("Block sites");
        //Block apps button
        appsButton = new JButton("Block apps");
        //schedule button
        schedButton = new JButton("Schedule");
        // Back button
        backButton = new JButton("Back");

        //create a panel to hold the buttons
        JPanel buttonPanel = new JPanel( new GridLayout(9, 1, 4, 4));
        
        //add buttons to button panel
        buttonPanel.add(new JLabel());
        buttonPanel.add(sitesButton);
        buttonPanel.add(new JLabel());
        buttonPanel.add(appsButton);
        buttonPanel.add(new JLabel());
        buttonPanel.add(schedButton);
        buttonPanel.add(new JLabel());
        buttonPanel.add(backButton);
        buttonPanel.add(new JLabel());
        add(buttonPanel);

        // main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);

        //events
        appsButton.addActionListener(this);
        sitesButton.addActionListener(this);
        schedButton.addActionListener(this);
        backButton.addActionListener(this);

        //add button panel to the center of the NewPage frame
        page.getContentPane().add(mainPanel, BorderLayout.CENTER);
        setTitle("Focus");
        //center the screen
        page.setLocationRelativeTo(null);
        //elements size is not related to window size
        page.setLayout(new FlowLayout());
        //maximize not allowed
        page.setResizable(false);
        //window size
        page.setSize(300,350);  //set size of the frame
        // close the frame on exit
        page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //go to sites / apps block
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==sitesButton)
        {
            new ShowAddWebsites();
            //hide this page
            page.setVisible(false);
        }
        else if(e.getSource()==appsButton)
        {
            new BlockApps();
            //hide this page
            page.setVisible(false);
        }
        else if(e.getSource()==schedButton)
        {
            new ScheduleTimer();
            page.setVisible(false);
            //page.dispose();
        }
        else if(e.getSource()==backButton)
        {
            new Todo_Focus();
            page.setVisible(false);
        }
    }
}

