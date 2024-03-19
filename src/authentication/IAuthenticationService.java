package authentication; // Package declaration stating the interface belongs to the Authentication package

public interface IAuthenticationService { // Declaraing of a public interface named as IAuthenticationService
    boolean register(String username, String password); // Declaring an abstract method 'register' with parameters username and password
    boolean login(String username, String password); // Declaring an  abstract method 'login' with parametes username and password
}