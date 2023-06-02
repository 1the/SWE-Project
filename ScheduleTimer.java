//<--4,1,3-->
package engez;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author oed51
 */
public class ScheduleTimer extends JFrame implements ActionListener {

    private int initialTime;
    private int remainingTime;
    private Timer timer;
    private JLabel timeLabel;
    private JButton startButton;
    private JButton pauseButton;
    private JButton stopButton;
    NewPage page;

    public ScheduleTimer() {
        page = new NewPage();
        // visibility
        page.setVisible(true);

        setTitle("Countdown Timer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        timeLabel = new JLabel(formatTime(remainingTime));
        timeLabel.setFont(new Font("Arial", Font.BOLD, 48));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // timepanel
        JPanel timePanel = new JPanel(new BorderLayout());
        timePanel.add(timeLabel, BorderLayout.CENTER);
        add(timePanel, BorderLayout.CENTER);

        startButton = new JButton("Start");
        pauseButton = new JButton("Pause / Resume");
        stopButton = new JButton("Stop");

        //events
        startButton.addActionListener(this);
        pauseButton.addActionListener(this);
        stopButton.addActionListener(this);

        // create a panel to hold the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 4, 4));
        buttonPanel.add(startButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(stopButton);
        add(buttonPanel, BorderLayout.LINE_END);

        // main lapel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);

        // center the screen
        setLocationRelativeTo(null);
        // add time label to the top of the NewPage frame
        page.getContentPane().add(timeLabel, BorderLayout.PAGE_START);
        //add button panel to the center of the NewPage frame
        page.getContentPane().add(buttonPanel, BorderLayout.CENTER);
        setTitle("Countdown Timer");
        //center the screen
        page.setLocationRelativeTo(null);
        //elements size is not related to window size
        page.setLayout(new FlowLayout());
        //maximize not allowed
        page.setResizable(false);
        //window size
        page.setSize(500, 150);  //set size of the frame
        //make the frame visible
        page.setVisible(true);
    }

    //goback to apps / sites block
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            String inputTime = JOptionPane.showInputDialog(null, "Enter time in seconds:");
            try {
                initialTime = Integer.parseInt(inputTime);
                remainingTime = initialTime;
                timeLabel.setText(formatTime(remainingTime));
                startTimer();
                URLBlocker.blockURLs(DataBase.BlockedWebsitesList);
                ApplicationBlocker.blockApplications(DataBase.BlockedAppsList);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer value.");
            }
        } else if (e.getSource() == pauseButton) {
            if (timer.isRunning()) {
                timer.stop();
                URLBlocker.unBlockURLs();
                ApplicationBlocker.unblockApplications(DataBase.BlockedAppsList);
            } else {
                timer.start();
                URLBlocker.blockURLs(DataBase.BlockedWebsitesList);
                ApplicationBlocker.blockApplications(DataBase.BlockedAppsList);

            }
        } else if (e.getSource() == stopButton) {
            stopTimer();
            URLBlocker.unBlockURLs();
            ApplicationBlocker.unblockApplications(DataBase.BlockedAppsList);


        }
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remainingTime--;
                timeLabel.setText(formatTime(remainingTime));
                if (remainingTime == 0) {
                    stopTimer();
                    returnToFocusWindow();
                }
            }
        });
        timer.start();
    }

    private void stopTimer() {
        if (timer != null) {
            timer.stop();
            remainingTime = initialTime;
            timeLabel.setText(formatTime(remainingTime));
            returnToFocusWindow();
        }
    }

    private void returnToFocusWindow() {
        //  return to focus window
        new Focus();
        //visibility
        page.setVisible(false);
        page.dispose();

    }

    private String formatTime(int timeInSeconds) {
        int hours = timeInSeconds / 3600;
        int minutes = (timeInSeconds % 3600) / 60;
        int seconds = timeInSeconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
