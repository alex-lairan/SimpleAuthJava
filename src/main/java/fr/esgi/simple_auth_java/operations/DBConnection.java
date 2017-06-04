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
    protected Connection dbConnect(String path) throws SQLException {
        String url = "jdbc:sqlite:".concat(path);
        try {
            // Create a connection to the database
            dbConnection = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            throw new SQLException("Error while attempting connection to the database.", ex);
        }
        return dbConnection;
    }

}
