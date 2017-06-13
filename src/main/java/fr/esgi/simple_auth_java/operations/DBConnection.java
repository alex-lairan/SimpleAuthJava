package fr.esgi.simple_auth_java.operations;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Olivier on 04/06/2017.
 */
@ToString
@EqualsAndHashCode
@Slf4j
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
    protected Connection dbConnect(String path) throws SQLException, IOException {
        log.debug("open {}", path);
        final Path pth = Paths.get(".", path);
        log.debug("full current path : {}", Paths.get(".").toAbsolutePath());
        if(Files.notExists(pth)) {
            log.info("first use of {}", pth);
            Files.copy(DBConnection.class.getClassLoader().getResourceAsStream(path), pth, new StandardCopyOption[0]);
        }
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
