package fr.esgi.simple_auth_java.auth;

/**
 * Created by Olivier on 04/06/2017.
 */

/**
 * Custom exception for user registration
 */
public class AuthenticationException extends Exception {
    public AuthenticationException() { super(); }
    public AuthenticationException(String message) { super(message); }
    public AuthenticationException(String message, Throwable cause) { super(message, cause); }
    public AuthenticationException(Throwable cause) { super(cause); }
}
