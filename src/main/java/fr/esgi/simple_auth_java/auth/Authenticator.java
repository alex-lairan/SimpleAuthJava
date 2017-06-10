package fr.esgi.simple_auth_java.auth;

import fr.esgi.simple_auth_java.User;

import java.util.HashMap;
import java.util.List;

/**
 * Definition of a {@link User}'s authentificator
 *
 * @author Tristan
 * @see User
 */
public interface Authenticator {
    /**
     * Authentified a user
     * @return the {@link User} authentified
     */
    User signIn() throws AuthenticationException;
}
