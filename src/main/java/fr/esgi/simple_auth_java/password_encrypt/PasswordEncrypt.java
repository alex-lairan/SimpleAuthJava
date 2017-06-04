package fr.esgi.simple_auth_java.password_encrypt;

import lombok.NonNull;

import java.security.MessageDigest;

/**
 * Password Encrypt.
 *
 * @author Alexandre
 */
public class PasswordEncrypt {
    @NonNull private final String password;

    public PasswordEncrypt(@NonNull String password) {
        this.password = password;
    }

    public String encrypt() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (byte aHash : hash) {
                String hex = Integer.toHexString(0xff & aHash);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
