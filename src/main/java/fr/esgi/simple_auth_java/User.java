package fr.esgi.simple_auth_java;

import fr.esgi.simple_auth_java.password_encrypt.PasswordEncrypt;
import fr.esgi.simple_auth_java.password_encrypt.PasswordEncryptSHA256;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.beans.ConstructorProperties;
import java.util.HashMap;

/**
 * User bean.
 *
 * @author Tristan, Alexandre
 */
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Slf4j
@Builder
public class User {
    @NotNull @NonNull @Email private String email;
    @NotNull @NonNull private String first_name, last_name;
    @NonNull private final PasswordEncrypt encryptor;
    @NotNull @NonNull @Size(min=4) private String password;
    @Setter(AccessLevel.PROTECTED) @Getter(AccessLevel.PROTECTED)
    private boolean isConnected = false;

    public User(HashMap<String, Object> args) {
        this((String) args.get("email"), (String) args.get("first_name"), (String) args.get("last_name"), (PasswordEncrypt) args.get("pswdEncryptor"), (String) args.get("password"));
    }

    @ConstructorProperties({"email", "first_name", "last_name", "encryptor", "password"})
    public User(@NonNull final String email, @NonNull final String first_name, @NonNull final String last_name, @NonNull final PasswordEncrypt encryptor, @NonNull final String password) {
        this(email, first_name, last_name, encryptor, password, false);
    }

    @ConstructorProperties({"email", "first_name", "last_name", "encryptor", "password", "isConnected"})
    protected User(@NonNull final String email, @NonNull final String first_name, @NonNull final String last_name, @NonNull final PasswordEncrypt encryptor, @NonNull final String password, final boolean is_connected) {
        this.setEmail(email);
        this.setFirst_name(first_name);
        this.setLast_name(last_name);
        this.encryptor = encryptor;
        this.setPassword(password);
        this.setConnected(is_connected);
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

    /**
     * Set password with the {@link #encryptor} of user
     * @param password the password to encrypt and set
     */
    public void setPassword(@NonNull final String password) {
        this.password = this.encryptor.encrypt(password);
    }
}
