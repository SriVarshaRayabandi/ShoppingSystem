package gui;

import authentication.IAuthenticationService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel passwordCriteriaLabel;
    private IAuthenticationService authService;
    private Runnable onSuccessfulLogin;

    public LoginPanel(IAuthenticationService authService, Runnable onSuccessfulLogin) {
        this.authService = authService;
        this.onSuccessfulLogin = onSuccessfulLogin; // Store the callback
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        passwordCriteriaLabel = new JLabel(" ");

        gbc.fill = GridBagConstraints.HORIZONTAL;
        addComponent(new JLabel("Username:"), gbc, 0, 0);
        addComponent(usernameField, gbc, 1, 0);
        addComponent(new JLabel("Password:"), gbc, 0, 1);
        addComponent(passwordField, gbc, 1, 1);
        addComponent(passwordCriteriaLabel, gbc, 1, 2, 2, 1);

        gbc.gridwidth = 1;
        addComponent(loginButton, gbc, 0, 3);
        addComponent(registerButton, gbc, 1, 3);

        loginButton.addActionListener(this::loginAction);
        registerButton.addActionListener(this::registerAction);
    }

    private void addComponent(Component component, GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        add(component, gbc);
    }

    private void addComponent(Component component, GridBagConstraints gbc, int x, int y, int width, int height) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        add(component, gbc);
    }

    private void loginAction(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (authService.login(username, password)) {
            JOptionPane.showMessageDialog(this, "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
            onSuccessfulLogin.run(); // Trigger the callback on successful login
        } else {
            JOptionPane.showMessageDialog(this, "Login Failed: Incorrect username or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registerAction(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (!isValidPassword(password)) {
            passwordCriteriaLabel.setText("<html>Password must be at least 8 characters long, include an uppercase letter, and a number.</html>");
            passwordCriteriaLabel.setForeground(Color.RED);
            return;
        } else {
            passwordCriteriaLabel.setText(" "); // Reset password criteria label if previously set
        }

        if (authService.register(username, password)) {
            JOptionPane.showMessageDialog(this, "Registration Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Registration Failed: Username already exists", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[A-Z]).{8,}$");
    }


}
