package fr.esgi.simple_auth_java.password_encrypt;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Password Encrypt with SHA-256.
 *
 * @author Alexandre, Tristan
 */
@RequiredArgsConstructor
public class PasswordEncryptSHA256 implements PasswordEncrypt {
    /**
     * Calculate the password's SHA-256 hash ; simple link to {@link #hash(String)}.
     * @param password the password to encrypt
     * @return SHA-256 of string password
     * @throws RuntimeException if error
     * @see #hash(String)
     */
    @Override
    public String encrypt(@NonNull final String password) {
        return PasswordEncryptSHA256.hash(password);
    }

    /**
     * Calculate the password's SHA-256 hash
     * @param password the password to encrypt
     * @return SHA-256 of string password
     * @throws RuntimeException if error
     */
    public static String hash(@NonNull final String psw) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(psw.getBytes("UTF-8"));
            return new BigInteger(1, hash).toString(16);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new RuntimeException("Error while hashed password", e);
        }
    }
}
