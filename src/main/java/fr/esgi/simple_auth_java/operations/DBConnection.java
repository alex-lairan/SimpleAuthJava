package fr.esgi.simple_auth_java.operations;

import lombok.NonNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Olivier on 04/06/2017.
 */
public abstract class DBConnection {

    @NonNull
    protected Connection dbConnection = null;

    public Connection getDbConnection() {
        return dbConnection;
    }

    /**
     * Set a connection to a database
     * @return The Connection object
     */
    protected Connection dbConnect() {
        String url = "jdbc:sqlite:C:\\Users\\Olivier\\IdeaProjects\\SimpleAuthJava\\src\\main\\resources\\database.db";
        try {
            // Create a connection to the database
            dbConnection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

}
