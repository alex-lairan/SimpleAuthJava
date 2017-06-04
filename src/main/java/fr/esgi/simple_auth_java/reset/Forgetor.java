package fr.esgi.simple_auth_java.reset;

import fr.esgi.simple_auth_java.User;

/**
 * Definition of system for Reset the {@link User}'s id (ex: password).
 *
 * @author Tristan
 * @see User
 */
public interface Forgetor {
    /**
     * Reset identification of a user
     * @param user {@link User} to reset
     */
    void reset(User user);
}
