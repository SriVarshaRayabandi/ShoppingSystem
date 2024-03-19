package authentication; // Package declaring stating that this class belongs to the Authentication package

import java.util.HashMap; // Import the statememnt to use HashMap from the java.util package
import java.util.Map; // Import the statement to use Map from the java.util package

public class AuthenticationService implements IAuthenticationService { // Declaring a public class named AuthenticationService which  implements the 'IAuthenticationService' interface

    private Map<String, User> users = new HashMap<>(); // Declaring of a private instance variable users of type Map<String User> initialized with a new HashMap

    @Override
    public boolean register(String username, String password) { // Implementing   the 'register' method which is declared in the 'IAuthenticationService' interface
        if (users.containsKey(username)) { // Check if the users map already containns the provided username
            return false; // Retur false indicating registration failure if the username already exists
        }
        users.put(username, new User(username, password)); // Add a new User object to the users map with the provided username and password
        return true; // Return true indicating successful registrationn
    }

    @Override
    public boolean login(String username, String password) { // Implementing of the login method declared in  IAuthenticationService interface
        User user = users.get(username); // Retrieve the User object associated with the provided username from the users map
        return user != null && user.getPassword().equals(password); // Returning true if a the username and passwrod matches otherwise return false
    }
}
