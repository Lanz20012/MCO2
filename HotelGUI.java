import javax.swing.*;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.ActionListener;



/**
 * HotelGUI is the graphical user interface (GUI) for the Hotel Reservation System application.
 * It provides a main menu with options for creating a hotel, viewing hotel information,
 * managing the hotel, simulating bookings, and changing rates. 
 */
public class HotelGUI extends JFrame {

    private JButton createHotelButton;
    private JButton viewHotelButton;
    private JButton manageHotelButton;
    private JButton simulateBookingButton;
    private JButton changeRatesButton;
    private JButton exitButton;
    private JTextField hotelNameField;
    private JTextField roomsInputField;
    private HotelController controller;
    private JPanel mainPanel;

    
    /**
     * Constructs the HotelGUI instance, initializes the frame settings, and sets up the main panel.
     */
    public HotelGUI() {
        super("Hotel Reservation System");
        setLayout(new BorderLayout());
        setSize(500, 400);
        setLocationRelativeTo(null);  // Center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        initMainPanel();

        setVisible(true);
    }

    
    /**
     * Initializes the main panel with buttons and layout.
     */
    private void initMainPanel() {
        // Initialize buttons
        createHotelButton = new JButton("Create Hotel");
        viewHotelButton = new JButton("View Hotel");
        manageHotelButton = new JButton("Manage Hotel");
        changeRatesButton = new JButton("Change Rates");
        simulateBookingButton = new JButton("Simulate Booking");
        exitButton = new JButton("Exit");

        // Set preferred size for buttons
        Dimension buttonSize = new Dimension(200, 40);
        createHotelButton.setPreferredSize(buttonSize);
        viewHotelButton.setPreferredSize(buttonSize);
        manageHotelButton.setPreferredSize(buttonSize);
        changeRatesButton.setPreferredSize(buttonSize);
        simulateBookingButton.setPreferredSize(buttonSize);
        exitButton.setPreferredSize(buttonSize);

        // Create and set up the north panel with a welcome message
        JPanel northPanel = new JPanel();
        northPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        JLabel welcomeLabel = new JLabel("<html><div style='text-align: center;'><b>Welcome to the Hotel Reservation System</b></div></html>");
        northPanel.add(welcomeLabel);
        mainPanel.add(northPanel, BorderLayout.NORTH);

        // Create and set up the center panel with buttons
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 10));
        buttonPanel.add(createHotelButton);
        buttonPanel.add(viewHotelButton);
        buttonPanel.add(manageHotelButton);
        buttonPanel.add(changeRatesButton);
        buttonPanel.add(simulateBookingButton);
        buttonPanel.add(exitButton);

        centerPanel.add(buttonPanel, gbc);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Initialize text fields
        hotelNameField = new JTextField(20);
        roomsInputField = new JTextField(20);
    }

    /**
     * Sets the action listener for all buttons in the main panel.
     * 
     * @param listener The ActionListener to be set for button actions.
     */
    public void setActionListener(ActionListener listener) {
        createHotelButton.addActionListener(listener);
        viewHotelButton.addActionListener(listener);
        manageHotelButton.addActionListener(listener);
        changeRatesButton.addActionListener(listener);
        simulateBookingButton.addActionListener(listener);
        exitButton.addActionListener(listener);
    }

    
    /**
     * Creates and displays the dialog for creating a new hotel.
     */
    public void displayCreateHotelDialog() {
        // Create a new panel with GridBagLayout for proportional arrangement
        JPanel createHotelPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make components expand horizontally

        // Label and text field for hotel name
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0; // No extra space for the label
        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        createHotelPanel.add(new JLabel("Hotel Name:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0; // Extra space for the text field
        JTextField hotelNameField = new JTextField(20);
        createHotelPanel.add(hotelNameField, gbc);

        // Label and text field for number of rooms
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0; // No extra space for the label
        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        createHotelPanel.add(new JLabel("Number of Rooms:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0; // Extra space for the text field
        JTextField roomsInputField = new JTextField(20);
        createHotelPanel.add(roomsInputField, gbc);

        // Create a button panel with OK and Cancel buttons
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // Add action listeners for the buttons
        okButton.addActionListener(e -> {
            String hotelName = hotelNameField.getText();
            String roomsInput = roomsInputField.getText();
            try {
                int numberOfRooms = Integer.parseInt(roomsInput);
                controller.handleCreateHotel(hotelName, numberOfRooms);
                switchToMainPanel(); // Switch back to the main panel after handling
            } catch (NumberFormatException ex) {
                displayMessage("Please enter a valid number for rooms.");
            }
        });

        cancelButton.addActionListener(e -> switchToMainPanel()); // Cancel button action

        // Create a new panel to hold the create hotel form and buttons
        JPanel fullPanel = new JPanel(new BorderLayout());
        fullPanel.add(createHotelPanel, BorderLayout.CENTER);
        fullPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Replace the current panel with the new panel
        getContentPane().removeAll();
        getContentPane().add(fullPanel);
        revalidate();
        repaint();
    }



    /**
     * Creates and displays the dialog for viewing hotel information.
     */
    public void displayViewHotelDialog() {
        // Create a new panel with GridBagLayout for proportional arrangement
        JPanel viewHotelPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make components expand horizontally

        // Label for the dialog instruction
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        gbc.weightx = 1.0; // Extra space for the label
        gbc.anchor = GridBagConstraints.CENTER; // Center the label
        JLabel instructionLabel = new JLabel("Select the type of information to view:");
        instructionLabel.setFont(instructionLabel.getFont().deriveFont(18f)); // Set a larger font size
        viewHotelPanel.add(instructionLabel, gbc);

        // Define options for viewing hotel information
        String[] options = {"Low Level Info", "High Level Info"};

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.insets = new Insets(10, 10, 10, 10); // Padding around buttons
        buttonGbc.fill = GridBagConstraints.HORIZONTAL; // Make buttons expand horizontally
        buttonGbc.weightx = 1.0; // Extra space for buttons

        // Create buttons for each option
        for (int i = 0; i < options.length; i++) {
            final String option = options[i]; 
            JButton button = new JButton(option);
            button.setPreferredSize(new Dimension(400, 50)); // Set a larger size for the buttons
            button.addActionListener(e -> {
                if (option.equals("Low Level Info")) {
                    displayLowLevelInfoDialog();
                } else if (option.equals("High Level Info")) {
                    displayHighLevelInfoDialog();
                }
            }); // Attach action listener

            buttonGbc.gridx = 0;
            buttonGbc.gridy = i;
            buttonPanel.add(button, buttonGbc);
        }

        // Add button panel to the view hotel panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Span across two columns
        gbc.weighty = 1.0; // Extra space for the button panel
        gbc.fill = GridBagConstraints.BOTH; // Make button panel expand both horizontally and vertically
        viewHotelPanel.add(buttonPanel, gbc);

        // Create a panel for the cancel button
        JPanel cancelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(120, 30)); // Set a smaller size for the cancel button
        cancelButton.addActionListener(e -> switchToMainPanel()); // Cancel button action
        cancelPanel.add(cancelButton);

        // Add the cancel button panel to the view hotel panel
        gbc.gridx = 0;
        gbc.gridy = options.length + 1; // Position below the button panel
        gbc.gridwidth = 2; // Span across two columns
        gbc.weighty = 0.0; // No extra space for the cancel button panel
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make cancel button panel expand horizontally
        viewHotelPanel.add(cancelPanel, gbc);

        // Create a new panel to hold the view hotel panel and add padding
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(viewHotelPanel, BorderLayout.CENTER);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Replace the current panel with the new panel
        getContentPane().removeAll();
        getContentPane().add(contentPanel);
        revalidate();
        repaint();
    }





    /**
     * Creates and displays the dialog for viewing low-level hotel information.
     */
    public void displayLowLevelInfoDialog() {
        // Create a new panel with GridBagLayout for proportional arrangement
        JPanel lowLevelInfoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make components expand horizontally

        // Label for the dialog instruction
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        gbc.weightx = 1.0; // Extra space for the label
        gbc.anchor = GridBagConstraints.CENTER; // Center the label
        JLabel instructionLabel = new JLabel("Select the low-level information to view:");
        instructionLabel.setFont(instructionLabel.getFont().deriveFont(18f)); // Set a larger font size
        lowLevelInfoPanel.add(instructionLabel, gbc);

        // Define options for low-level information
        String[] options = {
            "View Total Number of Available and Booked Rooms",
            "View Information on a Specific Room",
            "View Reservation Information"
        };

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.insets = new Insets(10, 10, 10, 10); // Padding around buttons
        buttonGbc.fill = GridBagConstraints.HORIZONTAL; // Make buttons expand horizontally
        buttonGbc.weightx = 1.0; // Extra space for buttons

        // Create buttons for each option
        for (int i = 0; i < options.length; i++) {
            final String option = options[i]; 
            JButton button = new JButton(option);
            button.setPreferredSize(new Dimension(400, 50)); // Set a larger size for the buttons
            button.addActionListener(e -> {
                if (option.equals("View Total Number of Available and Booked Rooms")) {
                    displayTotalRoomsDialog();
                } else if (option.equals("View Information on a Specific Room")) {
                    displaySpecificRoomInfoDialog();
                } else if (option.equals("View Reservation Information")) {
                    displayReservationInfoDialog();
                }
            }); // Attach action listener

            buttonGbc.gridx = 0;
            buttonGbc.gridy = i;
            buttonPanel.add(button, buttonGbc);
        }

        // Add button panel to the low-level info panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Span across two columns
        gbc.weighty = 1.0; // Extra space for the button panel
        gbc.fill = GridBagConstraints.BOTH; // Make button panel expand both horizontally and vertically
        lowLevelInfoPanel.add(buttonPanel, gbc);

        // Create a panel for the cancel button
        JPanel cancelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(120, 30)); // Set a smaller size for the cancel button
        cancelButton.addActionListener(e -> switchToMainPanel()); // Cancel button action
        cancelPanel.add(cancelButton);

        // Add the cancel button panel to the low-level info panel
        gbc.gridx = 0;
        gbc.gridy = options.length + 1; // Position below the button panel
        gbc.gridwidth = 2; // Span across two columns
        gbc.weighty = 0.0; // No extra space for the cancel button panel
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make cancel button panel expand horizontally
        lowLevelInfoPanel.add(cancelPanel, gbc);

        // Create a new panel to hold the low-level info panel and add padding
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(lowLevelInfoPanel, BorderLayout.CENTER);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Replace the current panel with the new panel
        getContentPane().removeAll();
        getContentPane().add(contentPanel);
        revalidate();
        repaint();
    }





    /**
     * Creates and displays the dialog for viewing high-level hotel information.
     */
    public void displayHighLevelInfoDialog() {
        // Create a new panel with GridBagLayout for proportional arrangement
        JPanel highLevelInfoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make components expand horizontally

        // Label for the dialog instruction
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        gbc.weightx = 1.0; // Extra space for the label
        gbc.anchor = GridBagConstraints.CENTER; // Center the label
        JLabel instructionLabel = new JLabel("Enter the hotel name:");
        instructionLabel.setFont(instructionLabel.getFont().deriveFont(18f)); // Set a larger font size
        highLevelInfoPanel.add(instructionLabel, gbc);

        // Create a JTextField for user input
        JTextField hotelNameField = new JTextField(20);

        // Add input field to the panel
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Span across two columns
        gbc.weightx = 1.0; // Extra space for the input field
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make input field expand horizontally
        highLevelInfoPanel.add(hotelNameField, gbc);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.insets = new Insets(10, 10, 10, 10); // Padding around buttons
        buttonGbc.fill = GridBagConstraints.HORIZONTAL; // Make buttons expand horizontally
        buttonGbc.weightx = 1.0; // Extra space for buttons

        // Create OK button
        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(120, 30)); // Set a suitable size for the button
        okButton.addActionListener(e -> {
            String hotelName = hotelNameField.getText().trim();
            if (!hotelName.isEmpty()) {
                controller.handleHighLevelInfo(hotelName);
                switchToMainPanel();
            } else {
                displayMessage("Hotel name cannot be empty.");
            }
        });

        // Add OK button to the button panel
        buttonGbc.gridx = 0;
        buttonGbc.gridy = 0;
        buttonPanel.add(okButton, buttonGbc);

        // Create Cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(120, 30)); // Set a suitable size for the button
        cancelButton.addActionListener(e -> switchToMainPanel()); // Cancel button action

        // Add Cancel button to the button panel
        buttonGbc.gridx = 1;
        buttonGbc.gridy = 0;
        buttonPanel.add(cancelButton, buttonGbc);

        // Add button panel to the high-level info panel
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Span across two columns
        gbc.weighty = 1.0; // Extra space for the button panel
        gbc.fill = GridBagConstraints.BOTH; // Make button panel expand both horizontally and vertically
        highLevelInfoPanel.add(buttonPanel, gbc);

        // Create a new panel to hold the high-level info panel and add padding
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(highLevelInfoPanel, BorderLayout.CENTER);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Replace the current panel with the new panel
        getContentPane().removeAll();
        getContentPane().add(contentPanel);
        revalidate();
        repaint();
    }



    /**
     * Displays a dialog for entering hotel name and a specific date to view total rooms.
     */
    public void displayTotalRoomsDialog() {
        // Create a new panel with GridBagLayout for proportional arrangement
        JPanel totalRoomsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make components expand horizontally

        // Label and text field for hotel name
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1; // Single column
        JLabel hotelNameLabel = new JLabel("Enter Hotel Name:");
        totalRoomsPanel.add(hotelNameLabel, gbc);

        JTextField hotelNameField = new JTextField();
        hotelNameField.setPreferredSize(new Dimension(400, 30)); // Set a larger size for the text field
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1; // Single column
        gbc.weightx = 1.0; // Allow the text field to expand
        totalRoomsPanel.add(hotelNameField, gbc);

        // Label and text field for specific date
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Single column
        JLabel dateLabel = new JLabel("Enter a Specific Date (1-31):");
        totalRoomsPanel.add(dateLabel, gbc);

        JTextField dateField = new JTextField();
        dateField.setPreferredSize(new Dimension(400, 30)); // Set a larger size for the text field
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Single column
        gbc.weightx = 1.0; // Allow the text field to expand
        totalRoomsPanel.add(dateField, gbc);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.insets = new Insets(10, 10, 10, 10); // Padding around buttons
        buttonGbc.fill = GridBagConstraints.HORIZONTAL; // Make buttons expand horizontally
        buttonGbc.weightx = 1.0; // Extra space for buttons

        // Create OK button
        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(120, 30)); // Set a suitable size for the button
        okButton.addActionListener(e -> {
            String hotelName = hotelNameField.getText().trim();
            try {
                int specificDate = Integer.parseInt(dateField.getText().trim());
                if (specificDate >= 1 && specificDate <= 31) {
                    controller.handleTotalRooms(hotelName, specificDate);
                    switchToMainPanel();
                } else {
                    displayMessage("Please enter a date between 1 and 31.");
                }
            } catch (NumberFormatException ex) {
                displayMessage("Please enter a valid integer for the specific date.");
            }
        });

        // Create Cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(120, 30)); // Set a suitable size for the button
        cancelButton.addActionListener(e -> switchToMainPanel()); // Cancel button action

        // Add buttons to the button panel
        buttonGbc.gridx = 0;
        buttonGbc.gridy = 0;
        buttonPanel.add(okButton, buttonGbc);

        buttonGbc.gridx = 1;
        buttonGbc.gridy = 0;
        buttonPanel.add(cancelButton, buttonGbc);

        // Add button panel to the total rooms panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Span across two columns
        gbc.weighty = 1.0; // Extra space for the button panel
        gbc.fill = GridBagConstraints.BOTH; // Make button panel expand both horizontally and vertically
        totalRoomsPanel.add(buttonPanel, gbc);

        // Create a new panel to hold the total rooms panel and add padding
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(totalRoomsPanel, BorderLayout.CENTER);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Replace the current panel with the new panel
        getContentPane().removeAll();
        getContentPane().add(contentPanel);
        revalidate();
        repaint();
    }







    /**
     * Displays a dialog for entering hotel name and room number to view specific room information.
     */
    public void displaySpecificRoomInfoDialog() {
        // Create a new panel with GridBagLayout for proportional arrangement
        JPanel specificRoomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make components expand horizontally

        // Label and text field for hotel name
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1; // Single column
        JLabel hotelNameLabel = new JLabel("Enter Hotel Name:");
        specificRoomPanel.add(hotelNameLabel, gbc);

        JTextField hotelNameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1; // Single column
        specificRoomPanel.add(hotelNameField, gbc);

        // Label and text field for room number
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Single column
        JLabel roomNumberLabel = new JLabel("Enter a Room Number:");
        specificRoomPanel.add(roomNumberLabel, gbc);

        JTextField roomNumberField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Single column
        specificRoomPanel.add(roomNumberField, gbc);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.insets = new Insets(10, 10, 10, 10); // Padding around buttons
        buttonGbc.fill = GridBagConstraints.HORIZONTAL; // Make buttons expand horizontally
        buttonGbc.weightx = 1.0; // Extra space for buttons

        // Create OK button
        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(120, 30)); // Set a suitable size for the button
        okButton.addActionListener(e -> {
            String hotelName = hotelNameField.getText().trim();
            try {
                int roomNumber = Integer.parseInt(roomNumberField.getText().trim());
                if (!hotelName.isEmpty()) {
                    controller.handleSpecificRoomInfo(hotelName, roomNumber);
                    switchToMainPanel();
                } else {
                    displayMessage("Hotel name cannot be empty.");
                }
            } catch (NumberFormatException ex) {
                displayMessage("Please enter a valid integer for the room number.");
            }
        });

        // Create Cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(120, 30)); // Set a suitable size for the button
        cancelButton.addActionListener(e -> switchToMainPanel()); // Cancel button action

        // Add buttons to the button panel
        buttonGbc.gridx = 0;
        buttonGbc.gridy = 0;
        buttonPanel.add(okButton, buttonGbc);

        buttonGbc.gridx = 1;
        buttonGbc.gridy = 0;
        buttonPanel.add(cancelButton, buttonGbc);

        // Add button panel to the specific room panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Span across two columns
        gbc.weighty = 1.0; // Extra space for the button panel
        gbc.fill = GridBagConstraints.BOTH; // Make button panel expand both horizontally and vertically
        specificRoomPanel.add(buttonPanel, gbc);

        // Create a new panel to hold the specific room panel and add padding
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(specificRoomPanel, BorderLayout.CENTER);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Replace the current panel with the new panel
        getContentPane().removeAll();
        getContentPane().add(contentPanel);
        revalidate();
        repaint();
    }

    /**
     * Displays a dialog for viewing reservation information.
     * This dialog prompts the user to enter the hotel name and guest name. 
     * 
     */
    public void displayReservationInfoDialog() {
        // Create a new panel with GridBagLayout for proportional arrangement
        JPanel reservationInfoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make components expand horizontally

        // Label and text field for hotel name
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1; // Single column
        JLabel hotelNameLabel = new JLabel("Enter Hotel Name:");
        reservationInfoPanel.add(hotelNameLabel, gbc);

        JTextField hotelNameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1; // Single column
        reservationInfoPanel.add(hotelNameField, gbc);

        // Label and text field for guest name
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Single column
        JLabel guestNameLabel = new JLabel("Enter Guest Name:");
        reservationInfoPanel.add(guestNameLabel, gbc);

        JTextField guestNameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Single column
        reservationInfoPanel.add(guestNameField, gbc);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.insets = new Insets(10, 10, 10, 10); // Padding around buttons
        buttonGbc.fill = GridBagConstraints.HORIZONTAL; // Make buttons expand horizontally
        buttonGbc.weightx = 1.0; // Extra space for buttons

        // Create OK button
        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(120, 30)); // Set a suitable size for the button
        okButton.addActionListener(e -> {
            String hotelName = hotelNameField.getText().trim();
            String guestName = guestNameField.getText().trim();
            if (!hotelName.isEmpty() && !guestName.isEmpty()) {
                controller.handleReservationInfo(hotelName, guestName);
                switchToMainPanel();
            } else {
                displayMessage("Hotel name and guest name cannot be empty.");
            }
        });

        // Create Cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(120, 30)); // Set a suitable size for the button
        cancelButton.addActionListener(e -> switchToMainPanel()); // Cancel button action

        // Add buttons to the button panel
        buttonGbc.gridx = 0;
        buttonGbc.gridy = 0;
        buttonPanel.add(okButton, buttonGbc);

        buttonGbc.gridx = 1;
        buttonGbc.gridy = 0;
        buttonPanel.add(cancelButton, buttonGbc);

        // Add button panel to the reservation info panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Span across two columns
        gbc.weighty = 1.0; // Extra space for the button panel
        gbc.fill = GridBagConstraints.BOTH; // Make button panel expand both horizontally and vertically
        reservationInfoPanel.add(buttonPanel, gbc);

        // Create a new panel to hold the reservation info panel and add padding
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(reservationInfoPanel, BorderLayout.CENTER);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Replace the current panel with the new panel
        getContentPane().removeAll();
        getContentPane().add(contentPanel);
        revalidate();
        repaint();
    }

    
    /**
     * This dialog prompts the user to enter details for simulating a booking, 
     * including hotel name, guest name, check-in and check-out dates, room type, and discount code.
     */
    public void displaySimulateBookingDialog() {
        // Create a new panel with GridBagLayout for proportional arrangement
        JPanel simulateBookingPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make components expand horizontally

        // Label and text field for hotel name
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0; // No extra space for the label
        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        simulateBookingPanel.add(new JLabel("Hotel Name:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0; // Extra space for the text field
        JTextField hotelNameField = new JTextField(20);
        simulateBookingPanel.add(hotelNameField, gbc);

        // Label and text field for guest name
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0; // No extra space for the label
        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        simulateBookingPanel.add(new JLabel("Guest Name:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0; // Extra space for the text field
        JTextField guestNameField = new JTextField(20);
        simulateBookingPanel.add(guestNameField, gbc);

        // Label and text field for check-in date
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0; // No extra space for the label
        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        simulateBookingPanel.add(new JLabel("Check-In Date (1-31):"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0; // Extra space for the text field
        JTextField checkInDateField = new JTextField(20);
        simulateBookingPanel.add(checkInDateField, gbc);

        // Label and text field for check-out date
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.0; // No extra space for the label
        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        simulateBookingPanel.add(new JLabel("Check-Out Date (1-31):"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0; // Extra space for the text field
        JTextField checkOutDateField = new JTextField(20);
        simulateBookingPanel.add(checkOutDateField, gbc);

        // Label and text field for room type
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.0; // No extra space for the label
        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        simulateBookingPanel.add(new JLabel("Select Room Type: (1 - Standard, 2 - Deluxe, 3 - Executive)"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0; // Extra space for the text field
        JTextField roomTypeField = new JTextField(20);
        simulateBookingPanel.add(roomTypeField, gbc);

        // Label and text field for discount code
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0.0; // No extra space for the label
        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        simulateBookingPanel.add(new JLabel("Discount Code (if any):"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0; // Extra space for the text field
        JTextField discountCodeField = new JTextField(20);
        simulateBookingPanel.add(discountCodeField, gbc);

        // Create a button panel with OK and Cancel buttons
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // Add action listeners for the buttons
        okButton.addActionListener(e -> {
            String hotelName = hotelNameField.getText();
            String guestName = guestNameField.getText();
            try {
                int checkInDate = Integer.parseInt(checkInDateField.getText());
                int checkOutDate = Integer.parseInt(checkOutDateField.getText());
                int roomType = Integer.parseInt(roomTypeField.getText());
                String discountCode = discountCodeField.getText();
                controller.handleSimulateBooking(hotelName, guestName, checkInDate, checkOutDate, roomType, discountCode);
                switchToMainPanel(); // Switch back to the main panel after handling
            } catch (NumberFormatException ex) {
                displayMessage("Please enter valid numbers for check-in date, check-out date, and room type.");
            }
        });

        cancelButton.addActionListener(e -> switchToMainPanel()); // Cancel button action

        // Create a new panel to hold the form and buttons
        JPanel fullPanel = new JPanel(new BorderLayout());
        fullPanel.add(simulateBookingPanel, BorderLayout.CENTER);
        fullPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Replace the current panel with the new panel
        getContentPane().removeAll();
        getContentPane().add(fullPanel);
        revalidate();
        repaint();
    }


    

    /**
     * This dialog provides options to manage various aspects of the hotel, such as 
     * changing the hotel name, adding/removing rooms, updating room prices, 
     * removing reservations, and removing the hotel.
     */
    public void displayManageHotelDialog() {
        // Create a new panel with GridBagLayout for proportional arrangement
        JPanel manageHotelPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make components expand horizontally

        // Label for the dialog instruction
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        gbc.weightx = 1.0; // Extra space for the label
        gbc.anchor = GridBagConstraints.CENTER; // Center the label
        JLabel instructionLabel = new JLabel("Select an action to manage the hotel:");
        instructionLabel.setFont(instructionLabel.getFont().deriveFont(18f)); // Set a larger font size
        manageHotelPanel.add(instructionLabel, gbc);

        // Define options for the management tasks
        String[] options = {
            "Change Hotel Name",
            "Add Rooms",
            "Remove Rooms",
            "Update Room Price",
            "Remove Reservation",
            "Remove Hotel"
        };

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(options.length, 1, 10, 10)); // Use GridLayout for buttons with spacing

        for (String option : options) {
            JButton button = new JButton(option);
            button.setPreferredSize(new Dimension(400, 50)); // Set a larger size for the buttons
            button.addActionListener(e -> handleButtonClick(option)); // Attach action listener
            buttonPanel.add(button);
        }

        // Add button panel to the manage hotel panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Span across two columns
        gbc.weighty = 1.0; // Extra space for the button panel
        gbc.fill = GridBagConstraints.BOTH; // Make button panel expand both horizontally and vertically
        manageHotelPanel.add(buttonPanel, gbc);

        // Create a panel for the cancel button
        JPanel cancelPanel = new JPanel();
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(120, 30)); // Set a smaller size for the cancel button
        cancelButton.addActionListener(e -> switchToMainPanel()); // Cancel button action
        cancelPanel.add(cancelButton);

        // Add the cancel button panel to the manage hotel panel
        gbc.gridx = 0;
        gbc.gridy = options.length + 1; // Position below the button panel
        gbc.gridwidth = 2; // Span across two columns
        gbc.weighty = 0.0; // No extra space for the cancel button panel
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make cancel button panel expand horizontally
        manageHotelPanel.add(cancelPanel, gbc);

        // Create a new panel to hold the manage hotel panel and add padding
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(manageHotelPanel, BorderLayout.CENTER);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Replace the current panel with the new panel
        getContentPane().removeAll();
        getContentPane().add(contentPanel);
        revalidate();
        repaint();
    }






    /**
     * Handles button clicks in the manage hotel dialog based on the selected option.
     */
    private void handleButtonClick(String option) {
        switch (option) {
            case "Change Hotel Name":
                displayChangeHotelName();
                break;
            case "Add Rooms":
                displayAddRooms();
                break;
            case "Remove Rooms":
                displayRemoveRoom();
                break;
            case "Update Room Price":
                displayUpdateRoomPrice();
                break;
            case "Remove Reservation":
                displayRemoveReservation();
                break;
            case "Remove Hotel":
                displayRemoveHotel();
                break;
            default:
                break; // No action
        }
    }




    /**
     * This dialog prompts the user to enter the hotel name and the room number to be removed.
     */
    public void displayRemoveRoom() {
        // Create a new panel with GridBagLayout for proportional arrangement
        JPanel removeRoomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make components expand horizontally

        // Label and text field for hotel name
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0; // No extra space for the label
        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        removeRoomPanel.add(new JLabel("Enter Hotel Name:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0; // Extra space for the text field
        JTextField hotelNameField = new JTextField(20);
        removeRoomPanel.add(hotelNameField, gbc);

        // Label and text field for room number
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0; // No extra space for the label
        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        removeRoomPanel.add(new JLabel("Enter Room Number to Remove:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0; // Extra space for the text field
        JTextField roomNumberField = new JTextField(20);
        removeRoomPanel.add(roomNumberField, gbc);

        // Create a button panel with OK and Cancel buttons
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // Add action listeners for the buttons
        okButton.addActionListener(e -> {
            String hotelName = hotelNameField.getText().trim();
            try {
                int roomNumber = Integer.parseInt(roomNumberField.getText().trim());
                controller.handleRemoveRoom(hotelName, roomNumber);
                switchToMainPanel(); // Switch back to the main panel after handling
            } catch (NumberFormatException ex) {
                displayMessage("Please enter a valid number for the room number.");
            }
        });

        cancelButton.addActionListener(e -> switchToMainPanel()); // Cancel button action

        // Create a new panel to hold the remove room form and buttons
        JPanel fullPanel = new JPanel(new BorderLayout());
        fullPanel.add(removeRoomPanel, BorderLayout.CENTER);
        fullPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Replace the current panel with the new panel
        getContentPane().removeAll();
        getContentPane().add(fullPanel);
        revalidate();
        repaint();
    }



    

    /**
     * This dialog prompts the user to enter the hotel name and guest name for the reservation to be removed.
     */
    public void displayRemoveReservation() {
        // Create a new panel with GridBagLayout for proportional arrangement
        JPanel removeReservationPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make components expand horizontally

        // Label and text field for hotel name
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0; // No extra space for the label
        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        removeReservationPanel.add(new JLabel("Enter Hotel Name:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0; // Extra space for the text field
        JTextField hotelNameField = new JTextField(20);
        removeReservationPanel.add(hotelNameField, gbc);

        // Label and text field for guest name
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0; // No extra space for the label
        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        removeReservationPanel.add(new JLabel("Enter Guest Name:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0; // Extra space for the text field
        JTextField guestNameField = new JTextField(20);
        removeReservationPanel.add(guestNameField, gbc);

        // Create a button panel with OK and Cancel buttons
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // Add action listeners for the buttons
        okButton.addActionListener(e -> {
            String hotelName = hotelNameField.getText().trim();
            String guestName = guestNameField.getText().trim();
            // Call the controller to handle the reservation removal
            controller.handleRemoveReservation(hotelName, guestName);
            switchToMainPanel(); // Switch back to the main panel after handling
        });

        cancelButton.addActionListener(e -> switchToMainPanel()); // Cancel button action

        // Create a new panel to hold the remove reservation form and buttons
        JPanel fullPanel = new JPanel(new BorderLayout());
        fullPanel.add(removeReservationPanel, BorderLayout.CENTER);
        fullPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Replace the current panel with the new panel
        getContentPane().removeAll();
        getContentPane().add(fullPanel);
        revalidate();
        repaint();
    }



    /**
     * Allows the user to enter the hotel name and the new price for the room.
     */
    public void displayUpdateRoomPrice() {
        // Create a new panel with GridBagLayout for proportional arrangement
        JPanel updateRoomPricePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make components expand horizontally

        // Label and text field for hotel name
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0; // No extra space for the label
        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        updateRoomPricePanel.add(new JLabel("Enter Hotel Name:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0; // Extra space for the text field
        JTextField hotelNameField = new JTextField(20);
        updateRoomPricePanel.add(hotelNameField, gbc);

        // Label and text field for new price
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0; // No extra space for the label
        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        updateRoomPricePanel.add(new JLabel("Enter New Price:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0; // Extra space for the text field
        JTextField newPriceField = new JTextField(20);
        updateRoomPricePanel.add(newPriceField, gbc);

        // Create a button panel with OK and Cancel buttons
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // Add action listeners for the buttons
        okButton.addActionListener(e -> {
            String hotelName = hotelNameField.getText().trim();
            String priceText = newPriceField.getText().trim();
            try {
                double newPrice = Double.parseDouble(priceText);
                // Call the controller to handle the room price update
                controller.handleUpdateRoomPrice(hotelName, newPrice);
                switchToMainPanel(); // Switch back to the main panel after handling
            } catch (NumberFormatException ex) {
                // Handle invalid price input
                displayMessage("Please enter a valid number for the price.");
            }
        });

        cancelButton.addActionListener(e -> switchToMainPanel()); // Cancel button action

        // Create a new panel to hold the update room price form and buttons
        JPanel fullPanel = new JPanel(new BorderLayout());
        fullPanel.add(updateRoomPricePanel, BorderLayout.CENTER);
        fullPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Replace the current panel with the new panel
        getContentPane().removeAll();
        getContentPane().add(fullPanel);
        revalidate();
        repaint();
    }



    /**
     * Allows the user to enter the name of the hotel to be removed.
     */
    public void displayRemoveHotel() {
        // Create a new panel with GridBagLayout for proportional arrangement
        JPanel removeHotelPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make components expand horizontally

        // Label and text field for hotel name
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0; // No extra space for the label
        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        removeHotelPanel.add(new JLabel("Enter Hotel Name:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0; // Extra space for the text field
        JTextField hotelNameField = new JTextField(20);
        removeHotelPanel.add(hotelNameField, gbc);

        // Create a button panel with OK and Cancel buttons
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // Add action listeners for the buttons
        okButton.addActionListener(e -> {
            String hotelName = hotelNameField.getText().trim();
            if (!hotelName.isEmpty()) {
                // Call the controller to handle the hotel removal
                controller.handleRemoveHotel(hotelName);
                switchToMainPanel(); // Switch back to the main panel after handling
            } else {
                // Handle empty hotel name input
                displayMessage("Hotel name cannot be empty.");
            }
        });

        cancelButton.addActionListener(e -> switchToMainPanel()); // Cancel button action

        // Create a new panel to hold the remove hotel form and buttons
        JPanel fullPanel = new JPanel(new BorderLayout());
        fullPanel.add(removeHotelPanel, BorderLayout.CENTER);
        fullPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Replace the current panel with the new panel
        getContentPane().removeAll();
        getContentPane().add(fullPanel);
        revalidate();
        repaint();
    }



    /**
     * Allows the user to enter the hotel name and the number of rooms to be added.
     */
    public void displayAddRooms() {
        // Create a new panel with GridBagLayout for proportional arrangement
        JPanel addRoomsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make components expand horizontally

        // Label and text field for hotel name
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0; // No extra space for the label
        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        addRoomsPanel.add(new JLabel("Enter Hotel Name:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0; // Extra space for the text field
        JTextField hotelNameField = new JTextField(20);
        addRoomsPanel.add(hotelNameField, gbc);

        // Label and text field for number of rooms
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0; // No extra space for the label
        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        addRoomsPanel.add(new JLabel("Enter Number of Rooms to Add:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0; // Extra space for the text field
        JTextField numRoomsField = new JTextField(20);
        addRoomsPanel.add(numRoomsField, gbc);

        // Create a button panel with OK and Cancel buttons
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // Add action listeners for the buttons
        okButton.addActionListener(e -> {
            String hotelName = hotelNameField.getText().trim();
            String numRoomsText = numRoomsField.getText().trim();
            try {
                int numRooms = Integer.parseInt(numRoomsText);
                if (!hotelName.isEmpty() && numRooms > 0) {
                    // Call the controller to handle adding rooms
                    controller.handleAddRooms(hotelName, numRooms);
                    switchToMainPanel(); // Switch back to the main panel after handling
                } else {
                    // Handle invalid input
                    displayMessage("Please enter a valid hotel name and number of rooms.");
                }
            } catch (NumberFormatException ex) {
                // Handle non-integer input for number of rooms
                displayMessage("Please enter a valid number for the number of rooms.");
            }
        });

        cancelButton.addActionListener(e -> switchToMainPanel()); // Cancel button action

        // Create a new panel to hold the add rooms form and buttons
        JPanel fullPanel = new JPanel(new BorderLayout());
        fullPanel.add(addRoomsPanel, BorderLayout.CENTER);
        fullPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Replace the current panel with the new panel
        getContentPane().removeAll();
        getContentPane().add(fullPanel);
        revalidate();
        repaint();
    }



    /**
     * Allows the user to enter the old name and the new name for the hotel.
     */
    public void displayChangeHotelName() {
        // Create a new panel with GridBagLayout for proportional arrangement
        JPanel changeHotelNamePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make components expand horizontally

        // Label and text field for current hotel name
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0; // No extra space for the label
        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        changeHotelNamePanel.add(new JLabel("Enter Current Hotel Name:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0; // Extra space for the text field
        JTextField currentHotelNameField = new JTextField(20);
        changeHotelNamePanel.add(currentHotelNameField, gbc);

        // Label and text field for new hotel name
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0; // No extra space for the label
        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        changeHotelNamePanel.add(new JLabel("Enter New Hotel Name:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0; // Extra space for the text field
        JTextField newHotelNameField = new JTextField(20);
        changeHotelNamePanel.add(newHotelNameField, gbc);

        // Create a button panel with OK and Cancel buttons
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // Add action listeners for the buttons
        okButton.addActionListener(e -> {
            String currentHotelName = currentHotelNameField.getText().trim();
            String newHotelName = newHotelNameField.getText().trim();
            if (!currentHotelName.isEmpty() && !newHotelName.isEmpty()) {
                // Call the controller to handle changing the hotel name
                controller.handleChangeHotelName(currentHotelName, newHotelName);
                switchToMainPanel(); // Switch back to the main panel after handling
            } else {
                // Handle invalid input
                displayMessage("Please enter both the current and new hotel names.");
            }
        });

        cancelButton.addActionListener(e -> switchToMainPanel()); // Cancel button action

        // Create a new panel to hold the change hotel name form and buttons
        JPanel fullPanel = new JPanel(new BorderLayout());
        fullPanel.add(changeHotelNamePanel, BorderLayout.CENTER);
        fullPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Replace the current panel with the new panel
        getContentPane().removeAll();
        getContentPane().add(fullPanel);
        revalidate();
        repaint();
    }


    /**
     * Allows the user to enter the hotel name and the new rate.
     */
    public void displayChangeRates() {
        // Create a new panel with GridBagLayout for proportional arrangement
        JPanel changeRatesPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make components expand horizontally

        // Label and text field for current hotel name
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0; // No extra space for the label
        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        changeRatesPanel.add(new JLabel("Enter Current Hotel Name:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0; // Extra space for the text field
        JTextField hotelNameField = new JTextField(20);
        changeRatesPanel.add(hotelNameField, gbc);

        // Label and text field for day
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0; // No extra space for the label
        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        changeRatesPanel.add(new JLabel("Enter Day of the Month:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0; // Extra space for the text field
        JTextField dayField = new JTextField(20);
        changeRatesPanel.add(dayField, gbc);

        // Label and text field for rate change
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0; // No extra space for the label
        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        changeRatesPanel.add(new JLabel("Enter Percentage for New Rate:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0; // Extra space for the text field
        JTextField rateField = new JTextField(20);
        changeRatesPanel.add(rateField, gbc);

        // Create a button panel with OK and Cancel buttons
        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // Add action listeners for the buttons
        okButton.addActionListener(e -> {
            String hotelNameText = hotelNameField.getText().trim();
            String dayText = dayField.getText().trim();
            String rateText = rateField.getText().trim();

            try {
                int day = Integer.parseInt(dayText);
                int rate = Integer.parseInt(rateText); // Changed to int
                if (!hotelNameText.isEmpty() && day > 0 && day <= 31 && rate > 0) {
                    controller.handleChangeRates(hotelNameText, day, rate);
                    switchToMainPanel();
                } else {
                    displayMessage("Please enter a valid hotel name, day, and rate.");
                }
            } catch (NumberFormatException ex) {
                displayMessage("Please enter a valid day and rate.");
            }
        });

        cancelButton.addActionListener(e -> switchToMainPanel()); // Cancel button action

        // Create a new panel to hold the change rates form and buttons
        JPanel fullPanel = new JPanel(new BorderLayout());
        fullPanel.add(changeRatesPanel, BorderLayout.CENTER);
        fullPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Replace the current panel with the new panel
        getContentPane().removeAll();
        getContentPane().add(fullPanel);
        revalidate();
        repaint();
    }



    /**
     * Displays a message dialog with the given message.
     * 
     * @param message The message to be displayed in the dialog.
     */
	public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

	
	/**
     * Sets the controller for this GUI.
     * 
     * @param controller The HotelController instance to be set.
     */
    public void setController(HotelController controller) {
        this.controller = controller;
    }

    
    /**
     * Returns the current controller associated with this GUI.
     * 
     * @return The current HotelController instance.
     */
    public HotelController getController() {
        return controller;
    }

    /**
     * Clears the text fields used.
     */
    public void clearInputFields() {
        hotelNameField.setText("");
        roomsInputField.setText("");
    }
    
    /**
     * Sets a DocumentListener on the text fields for hotel name and room input.
     * 
     * @param listener The DocumentListener to be added to the text fields.
     */
    public void setDocumentListener(DocumentListener listener) {
        hotelNameField.getDocument().addDocumentListener(listener);
        roomsInputField.getDocument().addDocumentListener(listener);
    }
    
    /**
     * Switches the current view to the main panel.
     * Removes all components from the content pane and adds the main panel.
     */
    public void switchToMainPanel() {
        getContentPane().removeAll();
        getContentPane().add(mainPanel);
        revalidate();
        repaint();
    }
    
    
    /**
     * Returns the button used to create a new hotel.
     * 
     * @return The JButton used to create a new hotel.
     */
    public JButton getCreateHotelButton() {
        return createHotelButton;
    }

    /**
     * Returns the button used to view hotel details.
     * 
     * @return The JButton used to view hotel details.
     */
    public JButton getViewHotelButton() {
        return viewHotelButton;
    }

    /**
     * Returns the button used to manage existing hotels.
     * 
     * @return The JButton used to manage existing hotels.
     */
    public JButton getManageHotelButton() {
        return manageHotelButton;
    }
    
    /**
     * Returns the button used to change hotel rates.
     * 
     * @return The JButton used to change hotel rates.
     */
    public JButton getChangeRatesButton() {
        return changeRatesButton;
    }


    /**
     * Returns the button used to simulate a booking.
     * 
     * @return The JButton used to simulate a booking.
     */
    public JButton getSimulateBookingButton() {
        return simulateBookingButton;
    }

    
    /**
     * Returns the button used to exit the application.
     * 
     * @return The JButton used to exit the application.
     */
    public JButton getExitButton() {
        return exitButton;
    }
}
