package fr.esgi.simple_auth_java.register;

/**
 * Created by Olivier on 04/06/2017.
 */

/**
 * Custom exception for user registration
 */
public class SignUpException extends Exception {
    public SignUpException() { super(); }
    public SignUpException(String message) { super(message); }
    public SignUpException(String message, Throwable cause) { super(message, cause); }
    public SignUpException(Throwable cause) { super(cause); }
}
