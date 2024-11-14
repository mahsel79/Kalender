package model;

import javax.swing.*;
import java.awt.*;

public class CalendarFrame extends JFrame {
    public CalendarFrame() {
        setTitle("Weekly Calendar");
        setSize(900, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 7));  // 1 row, 7 columns to represent each day

        // Add seven DayPanels, one for each day of the week
        for (int i = 0; i < 7; i++) {
            add(new DayPanel(i));
        }

        setVisible(true);
    }
}
