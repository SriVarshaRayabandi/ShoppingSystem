package authentication; // Package declaration of this class belongs to  Authentication Package

public class User { // Declaration of a public class 'User'
    private String username; // Declaration of a private instance variable "username", type-string
    private String password; // Declaration of a private instance variable "password", type-string

    public User(String username, String password) { // Constructor declaration
        this.username = username; // Assigning the parameter 'username' to the instance variable 'username'
        this.password = password; // Assigning the  parameter 'passward' to the insstance variable 'password'
    }

    public String getUsername() { // Getter method for retrieveing the value of the 'username' instance variable
        return username; // Returning the valllue of the 'username'  variable
    }

    public void setUsername(String username) { // Setter method for updating the value of the 'username'
        this.username = username; // Assigning the value of the parameter 'username' to the instannce variable 'username'
    }

    public String getPassword() { // Getter method to retrive the value of the 'password' instance variable
        return password; // Returns the the value of the 'password' instance variable
    }

    public void setPassword(String password) { // Setter method is used to update the value of the 'password' instance variable
        this.password = password; // Assigning the value of parameter 'password' to the instance variable 'password'
    }
}
