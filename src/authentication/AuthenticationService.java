package authentication;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationService implements IAuthenticationService {
    private Map<String, User> users = new HashMap<>();

    @Override
    public boolean register(String username, String password) {
        if (users.containsKey(username)) {
            return false; // If User already exists
        }
        users.put(username, new User(username, password));
        return true;
    }

    @Override
    public boolean login(String username, String password) {
        User user = users.get(username);
        return user != null && user.getPassword().equals(password);
    }
}