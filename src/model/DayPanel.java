package model;

import util.DateUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class DayPanel extends JPanel {
    private LocalDate date;
    private JPanel eventPanel;

    public DayPanel(int dayOffset) {
        this.date = DateUtils.getMondayOfCurrentWeek().plusDays(dayOffset);
        boolean isToday = date.equals(LocalDate.now());

        setLayout(new BorderLayout());
        setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        setBackground(isToday ? new Color(173, 216, 230) : Color.WHITE);

        // Top panel: Day name and date
        JLabel dayLabel = new JLabel(
                capitalizeFirstLetter(date.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("sv", "SE"))),
                SwingConstants.CENTER
        );
        dayLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel dateLabel = new JLabel(formatDate(date), SwingConstants.CENTER);

        JPanel labelPanel = new JPanel(new GridLayout(2, 1));
        labelPanel.setOpaque(false);
        labelPanel.setBorder(new EmptyBorder(5, 0, 5, 0));
        labelPanel.add(dayLabel);
        labelPanel.add(dateLabel);
        add(labelPanel, BorderLayout.NORTH);

        // Center panel: Events
        eventPanel = new JPanel();
        eventPanel.setLayout(new BoxLayout(eventPanel, BoxLayout.Y_AXIS));
        eventPanel.setOpaque(false);
        add(eventPanel, BorderLayout.CENTER);

        // Bottom panel: "Add Event" label and Add button
        JLabel addEventLabel = new JLabel("Add Event", SwingConstants.CENTER);
        addEventLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        addEventLabel.setBorder(new EmptyBorder(5, 0, 5, 0));

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> new EventDialog(
                (JFrame) SwingUtilities.getWindowAncestor(this),
                null,
                -1,
                event -> addEvent(event),
                this::removeEvent
        ));

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1)); // Stack the label and button vertically
        buttonPanel.setOpaque(false);
        buttonPanel.add(addEventLabel);
        buttonPanel.add(addButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addEvent(EventDialog.Event event) {
        JLabel eventLabel = new JLabel(formatEvent(event));
        eventLabel.setOpaque(true);
        eventLabel.setBackground(new Color(211, 151, 248));
        eventLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        eventLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        eventLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int index = eventPanel.getComponentZOrder(eventLabel);
                new EventDialog(
                        (JFrame) SwingUtilities.getWindowAncestor(DayPanel.this),
                        event,
                        index,
                        updatedEvent -> updateEvent(index, updatedEvent),
                        DayPanel.this::removeEvent
                );
            }
        });

        eventPanel.add(eventLabel);
        eventPanel.revalidate();
        eventPanel.repaint();
    }

    private void updateEvent(int index, EventDialog.Event event) {
        JLabel eventLabel = (JLabel) eventPanel.getComponent(index);
        eventLabel.setText(formatEvent(event));
    }

    private void removeEvent(int index) {
        eventPanel.remove(index);
        eventPanel.revalidate();
        eventPanel.repaint();
    }

    private String formatEvent(EventDialog.Event event) {
        return String.format("<html><b>Title:</b> %s<br><b>Time:</b> %s<br><b>Description:</b> %s</html>",
                event.getTitle(), event.getTime(), event.getDescription());
    }

    private String formatDate(LocalDate date) {
        return date.getDayOfMonth() + " " + date.getMonth().getDisplayName(TextStyle.FULL, new Locale("sv", "SE"));
    }

    private String capitalizeFirstLetter(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }
}
