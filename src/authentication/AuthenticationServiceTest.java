package authentication;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AuthenticationServiceTest {

    private AuthenticationService authService;

    @Before
    public void setUp() {
        authService = new AuthenticationService();
    }

    @Test
    public void testRegisterAndLogin() {
        assertTrue(authService.register("user", "password"));
        assertFalse(authService.register("user", "password")); // Try to register the same user
        assertTrue(authService.login("user", "password"));
        assertFalse(authService.login("user", "wrongpassword"));
        assertFalse(authService.login("nonexistent", "password"));
    }
}