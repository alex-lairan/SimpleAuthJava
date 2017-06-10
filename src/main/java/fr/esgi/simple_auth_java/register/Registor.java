package fr.esgi.simple_auth_java.register;

import fr.esgi.simple_auth_java.User;


/**
 * Definition of a system for register a {@link User}.
 *
 * @author Tristan
 * @see User
 */
public interface Registor {
    /**
     * Sign Up a user
     * @return the new {@link User}
     */
    User signUp() throws SignUpException;
}
