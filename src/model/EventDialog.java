package model;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class EventDialog {
    private Consumer<Event> eventCallback; // Callback to handle dialog results
    private Consumer<Integer> deleteCallback; // Callback to handle event deletion

    public EventDialog(JFrame parent, Event initialEvent, int indexToEdit,
                       Consumer<Event> eventCallback, Consumer<Integer> deleteCallback) {
        this.eventCallback = eventCallback;
        this.deleteCallback = deleteCallback;

        // Create components
        JTextField eventTitleField = new JTextField(20);
        JSpinner timeSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        timeSpinner.setEditor(timeEditor);

        JTextArea eventDescriptionArea = new JTextArea(3, 20);
        eventDescriptionArea.setLineWrap(true);
        eventDescriptionArea.setWrapStyleWord(true);

        // Pre-fill fields if editing
        if (initialEvent != null) {
            eventTitleField.setText(initialEvent.getTitle());
            timeEditor.getTextField().setText(initialEvent.getTime());
            eventDescriptionArea.setText(initialEvent.getDescription());
        }

        // Dialog layout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        addLabelAndField(panel, gbc, "Event Title:", eventTitleField, 0, 0);
        addLabelAndField(panel, gbc, "Event Time (HH:mm):", timeSpinner, 0, 1);
        addLabelAndField(panel, gbc, "Description:", new JScrollPane(eventDescriptionArea), 0, 2);

        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        JButton deleteButton = new JButton("Delete");

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        if (indexToEdit != -1) buttonPanel.add(deleteButton);

        okButton.addActionListener(e -> {
            String title = eventTitleField.getText();
            String time = timeEditor.getTextField().getText();
            String description = eventDescriptionArea.getText();

            if (time.matches("^([01]?[0-9]|2[0-3]):([0-5]?[0-9])$")) {
                Event event = new Event(title, time, description);
                eventCallback.accept(event);
                ((JDialog) SwingUtilities.getWindowAncestor(panel)).dispose();
            } else {
                JOptionPane.showMessageDialog(panel, "Invalid time format. Use HH:mm.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> ((JDialog) SwingUtilities.getWindowAncestor(panel)).dispose());

        deleteButton.addActionListener(e -> {
            deleteCallback.accept(indexToEdit);
            ((JDialog) SwingUtilities.getWindowAncestor(panel)).dispose();
        });

        JDialog dialog = new JDialog(parent, "Add/Edit Event", true);
        dialog.setLayout(new BorderLayout());
        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

    private void addLabelAndField(JPanel panel, GridBagConstraints gbc, String labelText, JComponent field, int gridX, int gridY) {
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = gridX + 1;
        panel.add(field, gbc);
    }

    // Event class for encapsulating event details
    public static class Event {
        private String title;
        private String time;
        private String description;

        public Event(String title, String time, String description) {
            this.title = title;
            this.time = time;
            this.description = description;
        }

        public String getTitle() { return title; }
        public String getTime() { return time; }
        public String getDescription() { return description; }
    }
}
