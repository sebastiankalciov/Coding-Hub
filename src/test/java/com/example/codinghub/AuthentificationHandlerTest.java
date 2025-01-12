package com.example.codinghub;

import com.example.codinghub.handlers.AuthentificationHandler;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

public class AuthentificationHandlerTest {

    @Test
    public void testValidCredentials() throws SQLException {
        AuthentificationHandler handler = new AuthentificationHandler();
        String isAuthentificated = handler.login("test@gmail.com", "test1234");
        boolean boolIsAuthentificated = false;

        if (isAuthentificated.equals("logged in successfully")) {
            boolIsAuthentificated = true;
        }
        assertTrue(boolIsAuthentificated);
    }

    @Test
    public void testInvalidCredentials() throws SQLException {
        AuthentificationHandler handler = new AuthentificationHandler();
        String isAuthentificated = handler.login("tes2232334t@gmail.com", "test1234");
        boolean boolIsAuthentificated = false;

        if (isAuthentificated.equals("user not found")) {
            boolIsAuthentificated = true;
        }
        assertFalse(boolIsAuthentificated);
    }

    @Test
    public void testAlreadyHaveAccount() throws SQLException {
        AuthentificationHandler handler = new AuthentificationHandler();
        String isAuthentificated = handler.register("test@gmail.com", "test1234");
        boolean boolIsAuthentificated = false;

        if (isAuthentificated.equals("already registered")) {
            boolIsAuthentificated = true;
        }
        assertTrue(boolIsAuthentificated);
    }
}
