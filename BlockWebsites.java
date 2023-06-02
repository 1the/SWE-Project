//<--4,1,2-->
package engez;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class BlockWebsites extends JFrame implements ActionListener {
    JPanel ui;
    // checkbox
    private JCheckBox boxes[];
    int size;
    // constructor
    public BlockWebsites() {
        // set the size of the window
        setSize(400, 200);
        size = DataBase.getWebsites().size();

        // display websites from database in 2 parallelized columns
        ui = new JPanel(new GridLayout(0, 2, 4, 4));
        // import websites from database in hashmaps
        ui.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(ui);
        boxes = new JCheckBox[size];

        // create checkbox for each element instead of creating multi checkbox variables
        for (int i = 0; i < size; i++) {
            createrCheckBox(i);

        }
        // create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        // Done button
        Button doneButton = new Button("Done");
        buttonPanel.add(doneButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // event
        doneButton.addActionListener(this);

        setTitle("Block Websites");
        pack();
        // desplay the window in the center of the screen
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    //goback to apps / sites block
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // add the selected websites to a new hashmap
        for (int i = 0; i < size; i++) {
            if (boxes[i].isSelected()) {
                DataBase.BlockedWebsites.put(boxes[i].getText(),
                        DataBase.Websites.entrySet().toArray()[i].toString().split("=")[1]);
            }
        }
        System.out.println(DataBase.BlockedWebsites);
        //  convert the values of the hashmap to a list
        DataBase.BlockedWebsitesList = DataBase.BlockedWebsites.values().toArray(new String[0]);
        //  send the list of urls to the block method
//        URLBlocker.blockURLs(DataBase.BlockedWebsitesList);
        //display the previous page
        new Focus();
        //hide this page
        setVisible(false);
    }

    private void createrCheckBox(int i) {
        // get the name of the website from the database hashmaps
        String name = DataBase.Websites.entrySet().toArray()[i].toString().split("=")[0];
        // create a checkbox with the name of the website
        boxes[i] = new JCheckBox(name);
        // add the checkbox to the panel
        boxes[i].setBorder(new EmptyBorder(0, i % 2 == 0 ? 20 : 0, 0, i % 2 == 0 ? 0 : 20)); // set a border to
        // separate the columns of the websites
        ui.add(boxes[i]);
    }
}

