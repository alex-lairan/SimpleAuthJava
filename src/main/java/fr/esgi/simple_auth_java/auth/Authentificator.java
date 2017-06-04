package fr.esgi.simple_auth_java.auth;

import fr.esgi.simple_auth_java.User;

/**
 * Definition of a {@link User}'s authentificator
 *
 * @author Tristan
 * @see User
 */
public interface Authentificator {
    /**
     * Authentified a user
     * @return the {@link User} authentified
     */
    User signIn();
}
