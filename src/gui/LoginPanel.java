package gui;

// Importing classes we need to make the panel work
import authentication.IAuthenticationService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// Creating a class for our login panel. It's a part of the user interface
public class LoginPanel extends JPanel {
    // These are the parts of our form' where you enter your username and password and a label for password rules
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel passwordCriteriaLabel;
    private IAuthenticationService authService;
    private Runnable onSuccessfulLogin;

    // This sets up our login panel
    public LoginPanel(IAuthenticationService authService, Runnable onSuccessfulLogin) {
        this.authService = authService;
        this.onSuccessfulLogin = onSuccessfulLogin;
        initializeUI();
    }

    // This method sets up the user interface
    private void initializeUI() {
        setLayout(new GridBagLayout()); // Using GridBagLayout for arranging components
        GridBagConstraints gbc = new GridBagConstraints();

        // Creating fields for the user to enter their username and password
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login"); // Creating buttons for logging in and registering
        JButton registerButton = new JButton("Register");
        passwordCriteriaLabel = new JLabel(" "); // A label for showing password rules. Starts off empty


        gbc.fill = GridBagConstraints.HORIZONTAL;
        addComponent(new JLabel("Username:"), gbc, 0, 0);
        addComponent(usernameField, gbc, 1, 0);
        addComponent(new JLabel("Password:"), gbc, 0, 1);
        addComponent(passwordField, gbc, 1, 1);
        addComponent(passwordCriteriaLabel, gbc, 1, 2, 2, 1);
        gbc.gridwidth = 1;
        addComponent(loginButton, gbc, 0, 3);
        addComponent(registerButton, gbc, 1, 3);

        // Telling buttons what to do when clicked
        loginButton.addActionListener(this::loginAction);
        registerButton.addActionListener(this::registerAction);
    }

    // A helper method for adding components to the panel
    private void addComponent(Component component, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        add(component, gbc);
    }

    // An overloaded helper method for adding components that span multiple rows or columns
    private void addComponent(Component component, GridBagConstraints gbc, int x, int y, int width, int height) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        add(component, gbc);
    }

    // What happens when you click the login button
    private void loginAction(ActionEvent e) {
        // Retrieving what the user entered
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Trying to log in with those credentials
        if (authService.login(username, password)) {
            // If successful then show a message and do the success action
            JOptionPane.showMessageDialog(this, "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
            onSuccessfulLogin.run();
        } else {
            // If not then show an error message
            JOptionPane.showMessageDialog(this, "Login Failed: Incorrect username or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // What happens when you click the register button
    private void registerAction(ActionEvent e) {
        // retrieving what the user entered
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // First we check if the password is good enough
        if (!isValidPassword(password)) {
            // If not show a message about what's wrong
            passwordCriteriaLabel.setText("<html>Password must be at least 8 characters long, include an uppercase letter, and a number.</html>");
            passwordCriteriaLabel.setForeground(Color.RED);
            return;
        } else {
            // If it is fixed then clear the message
            passwordCriteriaLabel.setText(" ");
        }

        // Try to register with those credentials
        if (authService.register(username, password)) {
            // If successful show a message
            JOptionPane.showMessageDialog(this, "Registration Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // If not show an error message
            JOptionPane.showMessageDialog(this, "Registration Failed: Username already exists", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // A method to check if a password meets our rules
    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[A-Z]).{8,}$"); // It needs a digit, an uppercase letter, and be at least 8 characters long
    }
}