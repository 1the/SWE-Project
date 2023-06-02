//<--4,1,2,3-->
package engez;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ShowAddWebsites extends JFrame implements ActionListener {

    //variables
    JButton showWebsitesButton,addWebsiteButton;
    NewPage page;

    ShowAddWebsites() {
        page = new NewPage();
        //visibility
        page.setVisible(true);
        
        //ToDo button
        showWebsitesButton = new JButton("Show Websites");
        //Focus button
        addWebsiteButton = new JButton("Edit Website");

        //create a panel to hold the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 4, 4));
        buttonPanel.add(new JLabel());
        buttonPanel.add(showWebsitesButton);
        buttonPanel.add(new JLabel());
        buttonPanel.add(addWebsiteButton);
        add(buttonPanel);
        
        // main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);

        //events
        showWebsitesButton.addActionListener(this);
        addWebsiteButton.addActionListener(this);

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

    //go to 
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==addWebsiteButton)
        {
            new BlockedWebsitesPage();
            //hide this page
            page.setVisible(false);
        }
        else if(e.getSource()==showWebsitesButton)
        {
            new BlockWebsites();
            //hide this page
            page.setVisible(false);
        }
    }
}
