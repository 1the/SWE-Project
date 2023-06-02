/*
Alaa,Tasbeeh,Omar,Ayman,Mohammed
<--1,1-->
 */
package engez;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 class LoginPage extends JFrame implements ActionListener{
     // variables
    JButton b1, b2;
    JLabel userLabel, passLabel, pageLabel;
    final JTextField textField1, textField2;
    DataBase acc = new DataBase(); // accessing accounts data by creating objects

    // constructor
    LoginPage() {
        // header label
        pageLabel = new JLabel("Login Page");
        JPanel headerPanel = new JPanel();
        headerPanel.add(pageLabel);

        // username
        // label
        userLabel = new JLabel();
        userLabel.setText("Username"); // set label value for textField1
        // text field
        textField1 = new JTextField(15); // set length of the text
        userLabel.setHorizontalAlignment(JLabel.CENTER);

        // password
        // label
        passLabel = new JLabel();
        passLabel.setText("Password"); // set label value for textField2
        // text field
        textField2 = new JPasswordField(15); // set length for the password
        passLabel.setHorizontalAlignment(JLabel.CENTER);

        // button
        b1 = new JButton("Login"); // set label to button
        b2 = new JButton("Register"); // set label to button

        // panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(userLabel); // set username label to panel
        inputPanel.add(textField1); // set text field to panel
        inputPanel.add(passLabel); // set password label to panel
        inputPanel.add(textField2); // set text field to panel

        // button panel
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1));
        buttonPanel.add(new JLabel()); // set button to panel
        buttonPanel.add(b1); // set button to panel
        buttonPanel.add(new JLabel()); // set button to panel
        buttonPanel.add(b2); // set button to panel

        // main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // border panel
        add(mainPanel);

        // perform action on button click
        b1.addActionListener(this); // add action listener to button
        b2.addActionListener(this); // add action listener to button

        setTitle("LOGIN PAGE"); // set title to the login form
        // center the screen
        setLocationRelativeTo(null);
        // elements size is not related to window size
        setLayout(new FlowLayout());
        // maximize not allowed
        setResizable(false);
        // window size
        setSize(500, 300); // set size of the frame
        // make the frame visible
        setVisible(true);

        // close the frame on exit
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // define abstract method actionPerformed() which will be called on button click
    public void actionPerformed(ActionEvent ae) // pass action listener as a parameter
    {
        String userValue = textField1.getText(); // get user entered username from the textField1
        String passValue = textField2.getText(); // get user entered password from the textField2
        //login button
        if(ae.getSource()==b1) {
            // authorization
            if (DataBase.accounts.containsKey(userValue) && DataBase.accounts.get(userValue).equals(passValue)) {
                // if authorized,move to next page => todo and focus page
                // create instance of the NewPage
                new Todo_Focus();
                // hide this page
                setVisible(false);
            } else {
                // show error message
                // GUI
                JOptionPane.showMessageDialog(this, "Wrong username or password");
                // Terminal
                System.out.println("Please enter valid username and password");
            }
        } else if(ae.getSource()==b2)
        {
            //go to register page by creating instance from it
            new RegistrationPage();
            //hide this page
            setVisible(false);
        }
    }
}

// main class
class Logindemo {
        public static void main(String arg[]) {
        try {
            // create instance of the CreateLoginForm
            LoginPage form = new LoginPage();
            form.setVisible(true); // make form visible to the user
        } catch (Exception e) {
            // handle exception
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
}
