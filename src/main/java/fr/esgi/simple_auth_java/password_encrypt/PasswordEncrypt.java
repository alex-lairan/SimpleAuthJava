package fr.esgi.simple_auth_java.password_encrypt;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Password Encrypt.
 *
 * @author Alexandre, Tristan
 */
@RequiredArgsConstructor
public class PasswordEncrypt {
    @NonNull private final String password;

    /**
     * Calculate the password's SHA-256 hash
     * @return SHA-256 of string password
     * @throws RuntimeException if error
     */
    public String encrypt() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            return new BigInteger(1, hash).toString(16);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new RuntimeException("Error while hashed password", e);
        }
    }
}
