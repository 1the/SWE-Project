//<--4,1,1-->
package engez;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class BlockApps extends JFrame implements ActionListener {

    Button doneButton;
    //list of apps
    // private String apps[] = {
    //         "Facebook", "Whatsapp", "Telegram", "Chrome",
    //         "Firefox", "Atom", "VLC player",
    //         "VS Code", "Code Blocks", "Intellij", "Anaconda", "Spider",
    //         "Jupyter", "Microsoft store", "MS project", "Trello", "Spotify","Groove Music"
    // };

    //display apps in 2 parallelized columns
    JPanel ui = new JPanel(new GridLayout(0, 2, 4, 4));

    //checkbox
    private JCheckBox boxes[];

    //constructor
    public BlockApps() {

        ui.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(ui);
        boxes = new JCheckBox[DataBase.Apps.size()];

        //create checkbox for each element instead of creating multi checkbox variables
        for (int i = 0; i < DataBase.Apps.size(); i++) {
            createrCheckBox(i);
        }

        //create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        //Done button
        doneButton = new Button("Done");
        buttonPanel.add(doneButton);
        add(buttonPanel,BorderLayout.SOUTH);

        //event
        doneButton.addActionListener(this);

        setTitle("Block Apps");
        //center the screen
        setLocationRelativeTo(null);
        //maximize not allowed
        setResizable(false);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    //goback to apps / sites block
    public void actionPerformed(ActionEvent e)
    {
        // add selected apps to blocked apps list
        for (int i = 0; i < DataBase.Apps.size(); i++) {
            if (boxes[i].isSelected()) {
                DataBase.BlockedApps.put(DataBase.Apps.keySet().toArray()[i].toString(), DataBase.Apps.get(DataBase.Apps.keySet().toArray()[i].toString()));
            }
        }
        // show blocked apps and paths
        for (int i = 0; i < DataBase.BlockedApps.size(); i++) {
            System.out.println(DataBase.BlockedApps.keySet().toArray()[i].toString() + " " + DataBase.BlockedApps.get(DataBase.BlockedApps.keySet().toArray()[i].toString()));
        }
        // convert values of blocked apps to list
        DataBase.BlockedAppsList = DataBase.BlockedApps.values().toArray(new String[DataBase.BlockedApps.size()]);
        //go back to focus page
        new Focus();
        //hide this page
        setVisible(false);
    }

    public void createrCheckBox(int i) {
        boxes[i] = new JCheckBox(DataBase.Apps.keySet().toArray()[i].toString());
        boxes[i].setBorder(new EmptyBorder(0, i%2 == 0 ? 20 : 0, 0, i%2 == 0 ? 0 : 20)); // set a border to create...i*30,0,0));
        ui.add(boxes[i]);
    }
}