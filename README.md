## Weekly Calendar Java Application

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

By using a JFrame interface, the application provides a graphical calendar view, showing each day of the week along with any associated tasks.

## Features

- **Current Week View**: Displays a week from Monday to Sunday, highlighting the current day.
- **Activity Management**: Users can add, edit, and delete activities for each day.
- **Persistent Day Panels**: Each day is represented by a `DayPanel` object, holding details about the date, activities, and buttons for managing tasks.
- **Validation**: The application checks for valid time input in `HH:mm` format to ensure consistent scheduling.

## Technologies and Libraries

### Core Technologies
- **Java Swing**: Used for creating the graphical user interface. Swing is part of the Java Foundation Classes (JFC) and provides a rich set of lightweight components for developing desktop applications.
- **Java AWT**: Some components from AWT (e.g., `BorderLayout`, `FlowLayout`, `Color`) are used for additional layout and styling options.

### Key Classes and Libraries
- **`javax.swing`**: This is the primary library for building the GUI. It includes essential components like `JFrame`, `JPanel`, `JLabel`, `JButton`, and `JTextArea`, which make up the user interface.
- **`java.time`**: Used for handling dates (`LocalDate`) and date formatting. The `java.time` package is a modern, flexible, and thread-safe way to work with dates and times in Java.
- **`java.awt`**: Provides additional utilities for layout (`BorderLayout`, `FlowLayout`), color management, and action event handling.

### Additional Libraries
- **`GridBagLayout`**: Employed in `openEventDialog` for arranging dialog components neatly.
- **`SpinnerDateModel` and `JSpinner`**: Used in the event dialog to handle time input in a user-friendly way. The spinner is a simple way to ensure time is formatted in a standard `HH:mm` structure.

## Code Structure and Modules

The project is organized in a modular fashion with clear separation of concerns, making it easy to navigate and extend.

### `DayPanel` Class
This is the core class, representing each day of the week. It holds the following components:
- **Date Information**: Displays the day name and date using `JLabel`.
- **Activity Text Area**: Shows a summary of the day’s activities and opens an event dialog when clicked.
- **Add Button**: Opens the event dialog to add new events.

#### Key Methods
- `formatDate(LocalDate date)`: Formats the date for display.
- `openEventDialog`: Opens a dialog for adding or editing an event, which includes fields for the title, time, and description. This dialog also has buttons for saving, canceling, and deleting an event.

### `DateUtils` Class
A utility class for handling date-related tasks, such as calculating the starting date of the current week. This modular approach allows for reusability and cleaner code in `DayPanel`.

### Main Class
The `main` method launches the application by initializing the main frame and loading all `DayPanel` components into it. This provides a centralized entry point, making it easier to start and modify the application.

## User Interface and Design

The UI is designed to be clean and straightforward, with minimal distractions, allowing users to focus on tasks.

### Layout
- **Main Layout**: The application uses `BorderLayout` and `FlowLayout` for aligning components in a simple, intuitive way.
- **Event Dialog Layout**: The `openEventDialog` method uses `GridBagLayout` for organizing fields and labels in a structured way, making the dialog easy to read and interact with.

### Colors and Styling
- **Day Highlighting**: The current day is highlighted in a light blue color to improve user experience and aid quick visual recognition.
- **Event Labels**: Each event displays information in a compact `JLabel` formatted with HTML tags, allowing multi-line text and styled headers for better readability.

## Event Management

The `openEventDialog` method is the primary way users add or edit events. It includes:
- **Title Field**: A `JTextField` for inputting the event title.
- **Time Field**: A `JSpinner` with a date model that restricts input to a `HH:mm` format, ensuring consistency in scheduling.
- **Description Field**: A `JTextArea` for a short description of the event.
- **Dialog Buttons**:
    - **OK**: Saves the event.
    - **Cancel**: Closes the dialog without saving.
    - **Delete**: Deletes the event if it’s being edited.

When editing an existing event, the dialog pre-fills fields with current values, enhancing user experience by allowing quick modifications without re-typing all details.

## How to Run the Project

### Prerequisites
- **Java 21**: This project requires Java 21, as it leverages features from this version for improved performance and code clarity.
- **IDE (e.g., IntelliJ IDEA)**: While the project can run from the command line, using an IDE will improve the experience for navigation and debugging.

### Steps to Run
1. **Clone the Repository**: Clone or download the project files to your local machine.
2. **Open in IntelliJ**: Import the project into IntelliJ or another Java-compatible IDE.
3. **Run the Main Class**: Execute the `main` method in the `Main` class to launch the application.