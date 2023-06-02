//<--4,1,2,2-->
package engez;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author oed51
 */
public class BlockedWebsitesPage extends JFrame {
    private JTextField nameTextField;
    private JTextField urlTextField;
    private JButton addButton, removeButton, backButton;

    public BlockedWebsitesPage() {

        setTitle("Blocked Websites");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        nameTextField = new JTextField(20);
        urlTextField = new JTextField(20);
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        backButton = new JButton("Back");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText().trim();
                String url = urlTextField.getText().trim();

                if (name.isEmpty() || url.isEmpty()) {
                    JOptionPane.showMessageDialog(BlockedWebsitesPage.this, "Please enter both name and URL.");
                    return;
                }

                if (!isValidURL(url)) {
                    JOptionPane.showMessageDialog(BlockedWebsitesPage.this, "Invalid URL. Please enter a valid URL.");
                    return;
                }

                DataBase.Websites.put(name, url);
                JOptionPane.showMessageDialog(BlockedWebsitesPage.this, "Website added successfully.");
                nameTextField.setText("");
                urlTextField.setText("");
                System.out.println(DataBase.Websites);
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText().trim();
                String url = urlTextField.getText().trim();

                if (name.isEmpty() || url.isEmpty()) {
                    JOptionPane.showMessageDialog(BlockedWebsitesPage.this, "Please enter both name and URL.");
                    return;
                }

                if (!isValidURL(url)) {
                    JOptionPane.showMessageDialog(BlockedWebsitesPage.this, "Invalid URL. Please enter a valid URL.");
                    return;
                }

                if (!DataBase.Websites.containsKey(name)) {
                    JOptionPane.showMessageDialog(BlockedWebsitesPage.this, "Website not found.");
                    return;
                }

                if (!DataBase.Websites.get(name).equals(url)) {
                    JOptionPane.showMessageDialog(BlockedWebsitesPage.this, "Website not found.");
                    return;
                }

                DataBase.Websites.remove(name);
                JOptionPane.showMessageDialog(BlockedWebsitesPage.this, "Website removed successfully.");
                nameTextField.setText("");
                urlTextField.setText("");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShowAddWebsites();
                dispose();
            }
        });

        // button panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 4, 4));
        buttonPanel.add(new JLabel());
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(backButton);


        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        mainPanel.add(new JLabel("Website Name:"), gbc);

        gbc.gridx = 1;
        mainPanel.add(nameTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Website URL:"), gbc);

        gbc.gridx = 1;
        mainPanel.add(urlTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private boolean isValidURL(String url) {
        String regex = "^(https?|ftp)://[\\w-]+(\\.[\\w-]+)+([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BlockedWebsitesPage blockedWebsitesPage = new BlockedWebsitesPage();
                blockedWebsitesPage.setVisible(true);
            }
        });
    }
}
