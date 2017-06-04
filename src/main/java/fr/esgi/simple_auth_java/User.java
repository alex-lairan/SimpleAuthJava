package fr.esgi.simple_auth_java;

import fr.esgi.simple_auth_java.password_encrypt.PasswordEncrypt;
import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;

/**
 * User bean.
 *
 * @author Tristan, Alexandre
 */
@Data
@RequiredArgsConstructor
public class User {
    @NotNull @NonNull @Email private String email;
    @NotNull @NonNull private String first_name, last_name;
    @NotNull @NonNull @Size(min=4) private String password;
    @Setter(AccessLevel.PROTECTED) @Getter(AccessLevel.PROTECTED)
    private boolean isConnected = false;

    public User(HashMap<String, String> args) {
        this(args.get("email"), args.get("first_name"), args.get("last_name"), new PasswordEncrypt(args.get("password")).encrypt());
    }

    public boolean isAuthenticated() {
        return this.isConnected();
    }

    public void connect() {
        this.setConnected(true);
    }

    public void disconnect() {
        this.setConnected(false);
    }
}
