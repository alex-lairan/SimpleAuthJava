package fr.esgi.simple_auth_java.password_encrypt;

/**
 * Password Encrypt.
 *
 * @author Tristan
 */
public interface PasswordEncrypt {
    /**
     * Calculate the password's hash
     * @param password the password to hash
     * @return hash of string password
     * @throws RuntimeException if error
     */
    String encrypt(final String password);

    //TODO: create exception for replace RuntimeException
}
