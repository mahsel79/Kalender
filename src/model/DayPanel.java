package model;

import util.DateUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class DayPanel extends JPanel {
    private JTextArea activityArea;
    private LocalDate date;
    private JLabel dayLabel;
    private JLabel dateLabel;
    private JButton addButton;
    private JPanel eventPanel;

    public DayPanel(int dayOffset) {
        this.date = DateUtils.getMondayOfCurrentWeek().plusDays(dayOffset);
        boolean isToday = date.equals(LocalDate.now());

        setLayout(new BorderLayout());

        // Set a grey border on DayPanel without extra empty padding
        setBorder(new LineBorder(Color.LIGHT_GRAY, 1));

        // Define custom light blue color for highlighting today
        Color lightBlue = new Color(173, 216, 230);

        // Set the background to light blue if it's today, otherwise white
        setBackground(isToday ? lightBlue : Color.WHITE);

        // Display the day name (e.g., Monday, Tuesday) above the date
        String dayName = date.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("sv", "SE"));
        dayLabel = new JLabel(capitalizeFirstLetter(dayName), SwingConstants.CENTER);  // Capitalize only the first letter
        dayLabel.setFont(new Font("Arial", Font.BOLD, 14));  // Set font to bold and size 14
        dateLabel = new JLabel(formatDate(date), SwingConstants.CENTER);

        // Add day and date labels in a panel with reduced padding to show the top border clearly
        JPanel labelPanel = new JPanel(new GridLayout(2, 1));
        labelPanel.setOpaque(false); // Transparent to maintain the panel background
        labelPanel.setBorder(new EmptyBorder(5, 0, 5, 0));  // Adjust top padding
        labelPanel.add(dayLabel);
        labelPanel.add(dateLabel);
        add(labelPanel, BorderLayout.NORTH);

        // Create the Add button with a fixed size and center it
        addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(80, 30));  // Fixed size for consistent appearance

        // Place activity area and button in a panel at the bottom
        activityArea = new JTextArea("Add activity");
        activityArea.setEditable(false);  // Lock the text area to make it non-editable
        activityArea.setLineWrap(true);
        activityArea.setWrapStyleWord(true);

        // Remove extra spacing and set a minimal border for the JTextArea
        activityArea.setBorder(new EmptyBorder(2, 5, 2, 5)); // Tighten around text
        activityArea.setPreferredSize(new Dimension(90, 20)); // More compact size

        JPanel activityPanel = new JPanel(new BorderLayout());
        activityPanel.setOpaque(false); // Transparent background for the bottom section
        activityPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding to all sides

        // Create a panel to hold the events
        eventPanel = new JPanel();
        eventPanel.setLayout(new BoxLayout(eventPanel, BoxLayout.Y_AXIS));  // Arrange events vertically
        eventPanel.setOpaque(false); // Transparent background for event section

        // Wrap JTextArea and add button in the activityPanel
        activityPanel.add(activityArea, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(addButton);

        // Place the buttonPanel below the activity text area
        activityPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the event panel at the top of the DayPanel
        add(eventPanel, BorderLayout.CENTER);
        add(activityPanel, BorderLayout.SOUTH); // Place activityPanel at the bottom of DayPanel

        // Add action listener to the "Add" button
        addButton.addActionListener(e -> openEventDialog(null, null, null, -1));  // Pass null for no initial values
    }

    // Method to format the date as "14 November"
    private String formatDate(LocalDate date) {
        return date.getDayOfMonth() + " " + date.getMonth().getDisplayName(TextStyle.FULL, new Locale("sv", "SE"));
    }

    // Capitalize only the first letter of the day string
    private String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    // Method to open a dialog for adding event details
    private void openEventDialog(String initialTitle, String initialTime, String initialDescription, int indexToEdit) {
        // Create text fields for event details (title)
        JTextField eventTitleField = new JTextField(20);
        eventTitleField.setPreferredSize(new Dimension(100, 25));  // Control size of the text field

        // Create a Spinner for time input (in 24-hour format)
        SpinnerDateModel spinnerModel = new SpinnerDateModel();
        JSpinner timeSpinner = new JSpinner(spinnerModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeSpinner.setEditor(timeEditor);  // Set the time format to 24-hour
        timeSpinner.setPreferredSize(new Dimension(100, 25)); // Adjust width and height for spinner

        // Create a text area for event description
        JTextArea eventDescriptionArea = new JTextArea(3, 20);  // A small text area for short description
        eventDescriptionArea.setLineWrap(true);
        eventDescriptionArea.setWrapStyleWord(true);
        eventDescriptionArea.setPreferredSize(new Dimension(200, 75));  // Control height and width

        // Panel to hold the input fields
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Add padding around components
        gbc.anchor = GridBagConstraints.WEST;  // Align labels to the left

        // Add labels and fields to the panel with GridBagLayout
        addLabelAndField(panel, gbc, "Event Title:", eventTitleField, 0, 0);
        addLabelAndField(panel, gbc, "Event Time (HH:mm):", timeSpinner, 0, 1);
        addLabelAndField(panel, gbc, "Event Description:", new JScrollPane(eventDescriptionArea), 0, 2);

        // If editing an existing event, pre-fill the dialog with current values
        if (initialTitle != null) {
            eventTitleField.setText(initialTitle);
        }
        if (initialTime != null) {
            timeEditor.getTextField().setText(initialTime);  // Set the time in the spinner
        }
        if (initialDescription != null) {
            eventDescriptionArea.setText(initialDescription);
        }

        // Show dialog with "OK", "Cancel", and "Delete" buttons
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        JButton deleteButton = new JButton("Delete");

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(deleteButton);

        // Add action listeners for buttons
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newEventTitle = eventTitleField.getText();
                String eventTime = timeEditor.getTextField().getText(); // Get the time value as a string
                String eventDescription = eventDescriptionArea.getText();

                // Validate the time (it should be in HH:mm format)
                if (eventTime.matches("^([01]?[0-9]|2[0-3]):([0-5]?[0-9])$")) {
                    String eventDetails = "<html><b>Title:</b> " + newEventTitle + "<br>" +
                            "<b>Time:</b> " + eventTime + "<br>" +
                            "<b>Description:</b> " + eventDescription + "</html>";

                    if (indexToEdit != -1) {
                        // Update the existing event label with the new details
                        JLabel eventLabel = (JLabel) eventPanel.getComponent(indexToEdit);
                        eventLabel.setText(eventDetails);
                        eventLabel.setToolTipText("Click to edit");

                    } else {
                        // Create new event label if it's a new event
                        JLabel eventLabel = new JLabel(eventDetails);
                        eventLabel.setOpaque(true);
                        eventLabel.setBackground(new Color(211, 151, 248));
                        eventLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));  // Add border around label
                        eventLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                        // Allow editing when clicked
                        eventLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent e) {
                                openEventDialog(newEventTitle, eventTime, eventDescription, eventPanel.getComponentCount() - 1);
                            }
                        });

                        eventPanel.add(eventLabel);  // Add event label to the eventPanel
                    }

                    eventPanel.revalidate();
                    eventPanel.repaint();
                    ((JDialog) SwingUtilities.getWindowAncestor(panel)).dispose();
                } else {
                    JOptionPane.showMessageDialog(panel, "Please enter a valid time in HH:mm format.", "Invalid Time", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((JDialog) SwingUtilities.getWindowAncestor(panel)).dispose();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (indexToEdit != -1) {
                    // Remove the event label if it's an edit
                    eventPanel.remove(indexToEdit);
                    eventPanel.revalidate();
                    eventPanel.repaint();
                }
                ((JDialog) SwingUtilities.getWindowAncestor(panel)).dispose();
            }
        });

        // Show dialog with buttons
        JPanel dialogPanel = new JPanel(new BorderLayout());
        dialogPanel.add(panel, BorderLayout.CENTER);
        dialogPanel.add(buttonPanel, BorderLayout.SOUTH);

        JDialog eventDialog = new JDialog((Frame) null, "Add/Edit Event", true);
        eventDialog.setContentPane(dialogPanel);
        eventDialog.pack();
        eventDialog.setLocationRelativeTo(this);
        eventDialog.setVisible(true);
    }

    // Helper method to add label and field to the dialog
    private void addLabelAndField(JPanel panel, GridBagConstraints gbc, String labelText, JComponent field, int gridX, int gridY) {
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = gridX + 1;
        panel.add(field, gbc);
    }
}