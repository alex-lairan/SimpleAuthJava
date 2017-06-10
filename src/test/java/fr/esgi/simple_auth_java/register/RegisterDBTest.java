package fr.esgi.simple_auth_java.register;

import fr.esgi.simple_auth_java.User;
import fr.esgi.simple_auth_java.password_encrypt.PasswordEncryptSHA256;
import org.junit.Test;
import org.mockito.Mock;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Created by Nicol on 10/06/2017.
 */
public class RegisterDBTest {
    @Test
    public void signUp() throws Exception {
        //Init variables.
        String email = "testsignup@esgitu.com";
        String firstName = "Sign";
        String lastName = "Up";
        String password = "mysuperpasswd";

        //Change them into 1 line configuration.
        String simulatedUserInput = email + System.getProperty("line.separator") +
                                    firstName + System.getProperty("line.separator") +
                                    lastName + System.getProperty("line.separator") +
                                    password + System.getProperty("line.separator");

        //Convert it to a ByteArray of the System.In
        ByteArrayInputStream setIn = new ByteArrayInputStream(simulatedUserInput.getBytes());

        //Set it into System.In and called when System.In.next() fire.
        System.setIn(setIn);

        //Call function
        User testUser = new RegisterDB().signUp();

        //Assertation
        //  - Testing if same class
        assertEquals(User.class, testUser.getClass());
        //  - Testing his vars
        assertEquals(email, testUser.getEmail());
        assertEquals(firstName, testUser.getFirst_name());
        assertEquals(lastName, testUser.getLast_name());
        assertEquals(new PasswordEncryptSHA256().encrypt(password), testUser.getPassword());
    }

    // Maybe finish this test if we find a solution to Mock Scanner/System.In jobs
    /*@Mock Scanner mockedScanner;
    @Test
    public void mockSignUp() throws Exception {
        String email = "testsignup@esgitu.com";
        String firstName = "Sign";
        String lastName = "Up";
        String password = "mysuperpasswd";

        when(mockedScanner.next()).thenReturn(email).thenReturn(firstName).thenReturn(lastName).thenReturn(password);

        User testUser = new RegisterDB().signUp();

        //Assertation
        //  - Testing if same class
        assertEquals(User.class, testUser.getClass());
        //  - Testing his vars
        assertEquals(email, testUser.getEmail());
        assertEquals(firstName, testUser.getFirst_name());
        assertEquals(lastName, testUser.getLast_name());
        assertEquals(new PasswordEncryptSHA256().encrypt(password), testUser.getPassword());
    }*/

    @Test
    public void insertDb() throws Exception {
        //This will be the Integ. Test
    }

}