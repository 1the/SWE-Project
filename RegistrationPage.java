//<--1,2-->
package engez;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class RegistrationPage extends JFrame implements ActionListener {
    // Variables
    JButton registerButton, backButton;
    JLabel userLabel, passLabel, confirmPassLabel;
    final JTextField textField1;
    final JPasswordField textField2, textField3;

    // Constructor
    RegistrationPage() {

        setVisible(true);
        // Set the size of the frame
        this.setSize(500, 250);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        // Header label
        JLabel pageLabel = new JLabel("Registration Page");
        JPanel headerPanel = new JPanel();
        headerPanel.add(pageLabel);

        // Username
        userLabel = new JLabel();
        userLabel.setText("Username");
        textField1 = new JTextField(15);

        // Password
        passLabel = new JLabel();
        passLabel.setText("Password");
        textField2 = new JPasswordField(15);

        // Confirm Password
        confirmPassLabel = new JLabel();
        confirmPassLabel.setText("Confirm Password");
        textField3 = new JPasswordField(15);

        // Register Button
        registerButton = new JButton("Register");

        // Back Button
        backButton = new JButton("Back to Login");

        // Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(userLabel);
        inputPanel.add(textField1);
        inputPanel.add(passLabel);
        inputPanel.add(textField2);
        inputPanel.add(confirmPassLabel);
        inputPanel.add(textField3);

        // panel2
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);

        // Main Panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);

        mainPanel.add(headerPanel, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        mainPanel.add(inputPanel, gridBagConstraints);

        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = GridBagConstraints.CENTER; // Align buttons to the right
        mainPanel.add(buttonPanel, gridBagConstraints);

        // Border Panel
        add(mainPanel);

        // perform action on button click
        registerButton.addActionListener(this); // add action listener to button
        backButton.addActionListener(this); // add action listener to button

        setTitle("REGISTRATION PAGE");
        // close the frame on exit
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Define actionPerformed() method
    public void actionPerformed(ActionEvent ae) {
        String username = textField1.getText();

        // get password value from the Password field
        String password = new String(textField2.getPassword());
        String confirmPassword = new String(textField3.getPassword());


        if(ae.getSource()==registerButton) {
            System.out.println("Username: " + username + "\nPassword: " + password + "\nConfirm Password: "
                    + confirmPassword);
            // Check if username already exists
            if (DataBase.accounts.containsKey(username)) {
                JOptionPane.showMessageDialog(null, "Username already exists. Please try again.");
                System.out.println(DataBase.accounts);
            }
            // Check if username null
            else if (username.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter a username.");
            }
            // Check if passwords null
            else if (password.equals("") || confirmPassword.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter a password.");
            }
            // Registration successful
            else if (password.equals(confirmPassword)) {
                DataBase.accounts.put(username, password);
                JOptionPane.showMessageDialog(null,
                        "Registration Successful!\nUsername: " + username + "\nPassword: " + password);
                System.out.println(DataBase.accounts);
            }
            // Passwords do not match
            else {
                JOptionPane.showMessageDialog(null, "Passwords do not match. Please try again.");
            }
        }else if(ae.getSource()==backButton)
        {
           new LoginPage();
           setVisible(false);
        }
    }
}
