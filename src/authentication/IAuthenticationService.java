package authentication;

public interface IAuthenticationService {
    boolean register(String username, String password);
    boolean login(String username, String password);
}
