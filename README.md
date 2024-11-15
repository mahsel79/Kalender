# Weekly Calendar Java Application

This project is a **Java Swing-based Weekly Calendar** application, designed for planning weekly activities. The calendar displays the current week (Monday to Sunday), highlights the current day, and allows users to add, edit, and delete events for each day.

## Table of Contents
1. [Project Overview](#project-overview)
2. [Features](#features)
3. [Technologies and Libraries](#technologies-and-libraries)
4. [Code Structure and Modules](#code-structure-and-modules)
5. [User Interface and Design](#user-interface-and-design)
6. [Event Management](#event-management)
7. [How to Run the Project](#how-to-run-the-project)

---

## Project Overview

The goal of this project is to create an intuitive and user-friendly calendar application where users can:
- Track and organize weekly tasks and events.
- Add detailed activities for each day.
- Edit and delete existing events.

By using a `JFrame` interface, the application provides a graphical calendar view, showing each day of the week along with any associated tasks.

---

## Features

- **Current Week View**: Displays a week from Monday to Sunday, highlighting the current day.
- **Activity Management**: Users can add, edit, and delete activities for each day.
- **Persistent Day Panels**: Each day is represented by a `DayPanel` object, holding details about the date, activities, and buttons for managing tasks.
- **Validation**: The application checks for valid time input in `HH:mm` format to ensure consistent scheduling.
- **Add Event Label**: Non-editable text "Add Event" above the add button in each day panel.

---

## Technologies and Libraries

### Core Technologies
- **Java Swing**: Used for creating the graphical user interface. Swing is part of the Java Foundation Classes (JFC) and provides a rich set of lightweight components for developing desktop applications.
- **Java AWT**: Some components from AWT (e.g., `BorderLayout`, `FlowLayout`, `Color`) are used for additional layout and styling options.

### Key Classes and Libraries
- **`javax.swing`**: Includes essential components like `JFrame`, `JPanel`, `JLabel`, `JButton`, and `JTextArea`, which make up the user interface.
- **`java.time`**: Used for handling dates (`LocalDate`) and date formatting. The `java.time` package is modern, flexible, and thread-safe.
- **`java.awt`**: Provides additional utilities for layout (`BorderLayout`, `FlowLayout`), color management, and action event handling.

### Additional Libraries
- **`GridBagLayout`**: Used in the event dialog for arranging components neatly.
- **`SpinnerDateModel` and `JSpinner`**: Used in the event dialog to handle time input in a user-friendly way.

---

## Code Structure and Modules

The project is organized in a modular fashion with clear separation of concerns, making it easy to navigate and extend.

### `DayPanel` Class
This is the core class, representing each day of the week. It holds the following components:
- **Date Information**: Displays the day name and date using `JLabel`.
- **Event Section**: Allows adding and displaying events for each day.
- **Add Event Label**: A non-modifiable text "Add Event" above the add button.
- **Add Button**: Opens a dialog for adding new events.

#### Key Methods
- `addEvent(EventDialog.Event event)`: Adds a new event to the day's panel.
- `updateEvent(int index, EventDialog.Event event)`: Updates an existing event at the specified index.
- `removeEvent(int index)`: Removes an event at the specified index.
- `formatDate(LocalDate date)`: Formats the date for display.

### `DateUtils` Class
A utility class for handling date-related tasks, such as calculating the starting date of the current week.

### `EventDialog` Class
Handles the add/edit event functionality, including fields for title, time, and description, with buttons for saving, canceling, and deleting events.

---

## User Interface and Design

The UI is designed to be clean and straightforward, with minimal distractions, allowing users to focus on tasks.

### Layout
- **Main Layout**: Uses `BorderLayout` and `FlowLayout` for aligning components.
- **Event Dialog Layout**: Uses `GridBagLayout` for organizing fields and labels in a structured way.

### Colors and Styling
- **Day Highlighting**: The current day is highlighted in light blue for quick recognition.
- **Event Labels**: Each event is displayed in a compact `JLabel` formatted with HTML for better readability.

---

## Event Management

The `EventDialog` class provides a pop-up interface for managing events. Key features include:
- **Title Field**: Input for the event title.
- **Time Field**: Input for the event time in `HH:mm` format.
- **Description Field**: Input for the event description.
- **Buttons**:
  - **OK**: Saves the event.
  - **Cancel**: Closes the dialog without saving.
  - **Delete**: Deletes the event if applicable.

---

## How to Run the Project

### Prerequisites
- **Java 21**: This project requires Java 21.
- **IDE (e.g., IntelliJ IDEA)**: Recommended for running the project.

### Steps to Run
1. **Clone the Repository**: Clone or download the project files.
2. **Open in IntelliJ**: Import the project into IntelliJ or another Java-compatible IDE.
3. **Run the Main Class**: Execute the `main` method in the `Main` class to launch the application.
