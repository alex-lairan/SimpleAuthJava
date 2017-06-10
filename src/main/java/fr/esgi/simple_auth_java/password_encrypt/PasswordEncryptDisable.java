package fr.esgi.simple_auth_java.password_encrypt;

/**
 * Encryptor that not encrypt. It's for a {@link fr.esgi.simple_auth_java.User} that not encrypt its password (attention).
 *
 * @author Tristan
 */
public class PasswordEncryptDisable implements PasswordEncrypt {
    /**
     * Not calculate the password's hash
     *
     * @param password the password to not hash
     * @return the password the pointer
     * @throws RuntimeException if error (never)
     */
    @Override
    public String encrypt(final String password) {
        return password;
    }
}
