//<--4,2,2-->
package engez;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
class Task {

    private String title;
    private String body;
    private Date startDate;
    private Date endDate;
    private String status;  // New status field

    public Task() {

    }

    public Task(String title, String body, Date startDate, Date endDate, String status) {
        this.title = title;
        this.body = body;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

class ToDoAppGUI extends JFrame {
    private ArrayList<Task> tasks = DataBase.tasksList;
    private JList<String> taskList;
    private JTextArea taskTextArea;
    private JTextField titleField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JComboBox<String> statusComboBox;  // Updated to JComboBox
    private JButton addButton;
    private JButton removeButton;
    private JButton modifyButton;
    private JButton backButton;
    private int selectedTaskIndex;
    private SimpleDateFormat dateFormat;

    public ToDoAppGUI(JFrame previousMenu) {


        taskList = new JList<String>();
        taskTextArea = new JTextArea();
        titleField = new JTextField();
        startDateField = new JTextField();
        endDateField = new JTextField();
        statusComboBox = new JComboBox<>();  // Initialize JComboBox
        addButton = new JButton("Add Task");
        removeButton = new JButton("Remove Task");
        modifyButton = new JButton("Modify Task");
        backButton = new JButton("Back to Previous Menu");
        selectedTaskIndex = -1;

        setTitle("To-Do App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 400);
        setLayout(new BorderLayout());

        JPanel taskPanel = new JPanel(new BorderLayout());
        taskPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(taskList);
        taskPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(backButton);
        taskPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(taskPanel, BorderLayout.WEST);

        JPanel detailsPanel = new JPanel(new BorderLayout());
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel fieldsPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        fieldsPanel.add(new JLabel("Title:"));
        fieldsPanel.add(titleField);
        fieldsPanel.add(new JLabel("Start Date:"));
        fieldsPanel.add(startDateField);
        fieldsPanel.add(new JLabel("End Date:"));
        fieldsPanel.add(endDateField);
        fieldsPanel.add(new JLabel("Status:"));
        fieldsPanel.add(statusComboBox);  // Add JComboBox to the panel

        detailsPanel.add(fieldsPanel, BorderLayout.NORTH);
        detailsPanel.add(new JScrollPane(taskTextArea), BorderLayout.CENTER);
        detailsPanel.add(modifyButton, BorderLayout.SOUTH);

        add(detailsPanel, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog(ToDoAppGUI.this, "Enter task title:");
                String body = JOptionPane.showInputDialog(ToDoAppGUI.this, "Enter task body:");
                String startDate = JOptionPane.showInputDialog(ToDoAppGUI.this, "Enter task start date (yyyy-MM-dd):");
                String endDate = JOptionPane.showInputDialog(ToDoAppGUI.this, "Enter task end date (yyyy-MM-dd):");
                addTask(title, body, startDate, endDate);
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTask();
            }
        });

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyTask();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                previousMenu.setVisible(true);
                dispose();
            }
        });

        taskList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedTaskIndex = taskList.getSelectedIndex();
                if (selectedTaskIndex != -1) {
                    Task selectedTask = tasks.get(selectedTaskIndex);
                    displayTaskDetails(selectedTask);
                }
            }
        });

        clearTaskDetails();
        updateTaskList();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Add the status options to the JComboBox
        statusComboBox.addItem("Incomplete");
        statusComboBox.addItem("Inprocessing");
        statusComboBox.addItem("Completed");
        statusComboBox.addItem("Missed");
    }

    public void addTask(String title, String body, String startDate, String endDate) {
        // Parse the dates using the date format
        Date parsedStartDate = null;
        Date parsedEndDate = null;
        try {
            parsedStartDate = dateFormat.parse(startDate);
            parsedEndDate = dateFormat.parse(endDate);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(
                    ToDoAppGUI.this,
                    "Invalid date format. Please use yyyy-MM-dd.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        String status = "Incomplete";
        Date currentDate = new Date();
        if (parsedEndDate.before(currentDate)) {
            status = "Missed";
        }

        Task task = new Task(title, body, parsedStartDate, parsedEndDate, status);
        tasks.add(0, task);
        updateTaskList();
        clearTaskDetails();
    }

    private void removeTask() {
        if (selectedTaskIndex != -1) {
            tasks.remove(selectedTaskIndex);
            selectedTaskIndex = -1;
            updateTaskList();
            clearTaskDetails();
        } else {
            JOptionPane.showMessageDialog(ToDoAppGUI.this, "No task selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modifyTask() {
        Task selectedTask = null;
        if (selectedTaskIndex != -1) {
            selectedTask = tasks.get(selectedTaskIndex);
            selectedTask.setTitle(titleField.getText());
            selectedTask.setBody(taskTextArea.getText());
            selectedTask.setStatus(statusComboBox.getSelectedItem().toString());

            Date parsedStartDate = null;
            Date parsedEndDate = null;
            try {
                parsedStartDate = dateFormat.parse(startDateField.getText());
                parsedEndDate = dateFormat.parse(endDateField.getText());

                Date currentDate = new Date();
                if (parsedEndDate.before(currentDate)) {
                    selectedTask.setStatus("Missed");
                } else if (parsedEndDate.equals(currentDate) || parsedEndDate.after(currentDate)) {
                    selectedTask.setStatus("Incomplete");
                }

            } catch (ParseException e) {
                JOptionPane.showMessageDialog(
                        ToDoAppGUI.this,
                        "Invalid date format. Please use yyyy-MM-dd.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            selectedTask.setStartDate(parsedStartDate);
            selectedTask.setEndDate(parsedEndDate);

            updateTaskList();
        } else {
            JOptionPane.showMessageDialog(ToDoAppGUI.this, "No task selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayTaskDetails(Task task) {
        titleField.setText(task.getTitle());
        taskTextArea.setText(task.getBody());
        startDateField.setText(dateFormat.format(task.getStartDate()));
        endDateField.setText(dateFormat.format(task.getEndDate()));
        statusComboBox.setSelectedItem(task.getStatus());  // Set the selected item in JComboBox
    }

    private void clearTaskDetails() {
        titleField.setText("");
        taskTextArea.setText("");
        startDateField.setText("");
        endDateField.setText("");
        statusComboBox.setSelectedItem("");  // Clear the selected item in JComboBox
    }

    private void updateTaskList() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Task task : tasks) {
            listModel.addElement(task.getTitle());
        }
        taskList.setModel(listModel);
    }
}


