package fr.esgi.simple_auth_java.register;

import fr.esgi.simple_auth_java.User;
import fr.esgi.simple_auth_java.operations.DBConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Olivier on 04/06/2017.
 */
public class RegisterDB extends DBConnection implements Registor{

    /**
     * Create a user occurrence in the database by instanciating one with console arguments
     * @return A User object
     * @throws IOException Threw if an error occurs when the console is opened
     */
    public User signUp() throws SignUpException{
        String email = null;
        String firstname = null;
        String lastname = null;
        String password = null;

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            System.out.print("Enter an email:");
            email = br.readLine();
            System.out.print("Enter a firstname:");
            firstname = br.readLine();
            System.out.print("Enter a lastname:");
            lastname = br.readLine();
            System.out.print("Enter a password:");
            password = br.readLine();
        } catch(IOException ex){
            throw new SignUpException("Error while reading console input", ex);
        }

        return new User(email, firstname, lastname, password);
    }

    /**
     * Insert a row into the User table
     * @throws SQLException Threw if an error occurs while inserting or connecting
     * @throws IOException Threw if an error occurs when the console is opened
     */
    public void insertDb() throws SignUpException {
        RegisterDB registerDB = new RegisterDB();
        User user = registerDB.signUp();

        Statement statement = null;

        try(Connection connection = dbConnect("..\\SimpleAuthJava\\src\\main\\resources\\database.db")) {
            // Doesn't autocommit changes
            connection.setAutoCommit(false);
            System.out.println("Opened database successfully");

            // Insert a User instance into the database
            String sql = "INSERT INTO User (email, first_name, last_name, password, isConnected) " +
                    "VALUES ('" + user.getEmail() + "', '" + user.getFirst_name() + "', '" +
                    user.getLast_name() + "', '" + user.getPassword() + "', 0);";

            statement = connection.createStatement();
            statement.executeUpdate(sql);

            connection.commit();
        } catch ( SQLException ex ) {
            throw new SignUpException("Error while inserting a user into the database.", ex);
        }
        System.out.println("Records created successfully");
    }
}
