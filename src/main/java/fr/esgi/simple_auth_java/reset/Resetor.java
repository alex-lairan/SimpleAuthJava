package fr.esgi.simple_auth_java.reset;

import fr.esgi.simple_auth_java.User;

/**
 * Definition of system for Reset the {@link User}'s id (ex: password).
 *
 * @author Tristan
 * @see User
 */
public interface Resetor {
    /**
     * Reset identification of a user
     * @param user {@link User} to reset
     * @throws IllegalResetException If reset are not permit or the procedure isn't follow correctly (by user)
     * @throws ResetException an error occur during reset
     */
    void reset(User user) throws IllegalResetException, ResetException;
}
