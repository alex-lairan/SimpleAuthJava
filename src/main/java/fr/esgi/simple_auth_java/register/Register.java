package fr.esgi.simple_auth_java.register;

import fr.esgi.simple_auth_java.User;
import fr.esgi.simple_auth_java.operations.DBConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Olivier on 04/06/2017.
 */
public class Register extends DBConnection implements Registor{

    /**
     * Create a user occurrence in the database by instanciating one with console arguments
     * @return A User object
     * @throws IOException Threw if an error occurs when the console is opened
     */
    public User signUp() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter an email:");
        String email = br.readLine();
        System.out.print("Enter a firstname:");
        String firstname = br.readLine();
        System.out.print("Enter a lastname:");
        String lastname = br.readLine();
        System.out.print("Enter a password:");
        String password = br.readLine();
        return new User(email, firstname, lastname, password);
    }
}
