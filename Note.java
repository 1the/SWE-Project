//<--4,2,1-->
package engez;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
class Note {
    private String title;
    private String body;

    public Note(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

class NoteAppGUI extends JFrame {
    private ArrayList<Note> notes = DataBase.notesList;
    private JList<String> noteList;
    private JTextArea noteTextArea;
    private JTextField titleField;
    private JButton addButton;
    private JButton removeButton;
    private JButton modifyButton;
    private JButton backButton;
    private int selectedNoteIndex;

    public NoteAppGUI(JFrame previousMenu) {


        noteList = new JList<>();
        noteTextArea = new JTextArea(20, 60);
        titleField = new JTextField();
        addButton = new JButton("Add Note");
        removeButton = new JButton("Remove Note");
        modifyButton = new JButton("Modify Note");
        backButton = new JButton("Back to Previous Menu");
        selectedNoteIndex = -1;

        setTitle("Note App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // Center the window

        JPanel notePanel = new JPanel(new BorderLayout());
        notePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(noteList);
        notePanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(backButton);
        notePanel.add(buttonPanel, BorderLayout.SOUTH);

        add(notePanel, BorderLayout.WEST);

        JPanel detailsPanel = new JPanel(new BorderLayout());
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel fieldsPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        fieldsPanel.add(new JLabel("Title:"));
        fieldsPanel.add(titleField);

        detailsPanel.add(fieldsPanel, BorderLayout.NORTH);
        detailsPanel.add(new JScrollPane(noteTextArea), BorderLayout.CENTER);
        detailsPanel.add(modifyButton, BorderLayout.SOUTH);

        add(detailsPanel, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog(NoteAppGUI.this, "Enter note title:");
                String body = null;
                String inputMessage = "Enter note body:";
                JTextArea textArea = new JTextArea(10, 30); // Set the size of the text area
                JScrollPane scrollPane = new JScrollPane(textArea); // Add scroll bars to the text area

                int option = JOptionPane.showOptionDialog(
                        NoteAppGUI.this,
                        scrollPane,
                        inputMessage,
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null
                );

                if (option == JOptionPane.OK_OPTION) {
                    body = textArea.getText();
                }
                addNote(title, body);
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeNote();
            }
        });

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyNote();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                previousMenu.setVisible(true);
            }
        });

        noteList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedNoteIndex = noteList.getSelectedIndex();
                if (selectedNoteIndex != -1) {
                    Note selectedNote = notes.get(selectedNoteIndex);
                    displayNoteDetails(selectedNote);
                }
            }
        });

        clearNoteDetails();
        updateNoteList();
    }

    public void addNote(String title, String body) {
        Note note = new Note(title, body);
        notes.add(0,note);
        updateNoteList();
        clearNoteDetails();
    }

    private void removeNote() {
        if (selectedNoteIndex != -1) {
            notes.remove(selectedNoteIndex);
            selectedNoteIndex = -1;
            updateNoteList();
            clearNoteDetails();
        } else {
            JOptionPane.showMessageDialog(NoteAppGUI.this, "No note selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modifyNote() {
        if (selectedNoteIndex != -1) {
            Note selectedNote = notes.get(selectedNoteIndex);
            selectedNote.setTitle(titleField.getText());
            selectedNote.setBody(noteTextArea.getText());
            updateNoteList();
        } else {
            JOptionPane.showMessageDialog(NoteAppGUI.this, "No note selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayNoteDetails(Note note) {
        titleField.setText(note.getTitle());
        noteTextArea.setText(note.getBody());
    }

    private void clearNoteDetails() {
        titleField.setText("");
        noteTextArea.setText("");
    }

    private void updateNoteList() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Note note : notes) {
            listModel.addElement(note.getTitle());
        }
        noteList.setModel(listModel);
    }
}
