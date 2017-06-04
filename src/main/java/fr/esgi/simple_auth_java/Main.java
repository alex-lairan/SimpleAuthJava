package fr.esgi.simple_auth_java;

import fr.esgi.simple_auth_java.operations.UserInsert;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Olivier on 04/06/2017.
 */

public class Main {
    public static void main(String[] args) throws IOException {
        UserInsert userInsertion = new UserInsert();
        try {
            userInsertion.insert();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}