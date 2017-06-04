package fr.esgi.simple_auth_java;

import fr.esgi.simple_auth_java.password_encrypt.PasswordEncrypt;
import lombok.Data;
import lombok.NonNull;

import java.util.HashMap;

/**
 * User bean.
 *
 * @author Tristan, Alexandre
 */
@Data
public class User {
    @NonNull private String email, first_name, last_name;
    @NonNull private String password;
    @NonNull private Boolean is_connected = false;

    public User(HashMap<String, String> args) {
        email      = args.get("email");
        first_name = args.get("first_name");
        last_name  = args.get("last_name");

        PasswordEncrypt encryptor = new PasswordEncrypt(args.get("password"));
        password = encryptor.encrypt();
    }

    public Boolean isAuthenticated() {
        return is_connected;
    }

    public void connect() {
        is_connected = true;
    }

    public void disconnect() {
        is_connected = false;
    }
}
