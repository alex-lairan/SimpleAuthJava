package fr.esgi.simple_auth_java;

import fr.esgi.simple_auth_java.password_encrypt.PasswordEncrypt;
import lombok.*;

import java.util.HashMap;

/**
 * User bean.
 *
 * @author Tristan, Alexandre
 */
@Data
@RequiredArgsConstructor
public class User {
    @NonNull private String email, first_name, last_name;
    @NonNull private String password;
    @Setter(AccessLevel.PROTECTED) @Getter(AccessLevel.PROTECTED)
    @NonNull private Boolean isConnected = false;

    public User(HashMap<String, String> args) {
        this(args.get("email"), args.get("first_name"), args.get("last_name"), new PasswordEncrypt(args.get("password")).encrypt());
    }

    public Boolean isAuthenticated() {
        return this.getIsConnected();
    }

    public void connect() {
        this.setIsConnected(true);
    }

    public void disconnect() {
        this.setIsConnected(false);
    }
}
