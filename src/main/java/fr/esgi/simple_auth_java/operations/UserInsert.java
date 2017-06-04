package fr.esgi.simple_auth_java.operations;

import fr.esgi.simple_auth_java.User;
import fr.esgi.simple_auth_java.register.Register;
import lombok.NonNull;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Olivier on 04/06/2017.
 */
public class UserInsert extends DBConnection {
    /**
     * Insert a row into the User table
     * @throws SQLException Threw if an error occurs while inserting or connecting
     * @throws IOException Threw if an error occurs when the console is opened
     */
    public void insert() throws SQLException, IOException {
        Register register = new Register();
        User user = register.signUp();

        Connection connection = dbConnect();
        Statement statement = null;

        try {
            // Doesn't autocommit changes
            connection.setAutoCommit(false);
            System.out.println("Opened database successfully");

            // Insert a User instance into the database
            String sql = "INSERT INTO User (email, first_name, last_name, password, isConnected) " +
                    "VALUES ('" + user.getEmail() + "', '" + user.getFirst_name() + "', '" +
                    user.getLast_name() + "', '" + user.getPassword() + "', 0);";

            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch ( Exception e ) {
            System.err.println( e.getMessage() );
        } finally {
            statement.close();
            connection.commit();
            connection.close();
        }
        System.out.println("Records created successfully");
    }
}
