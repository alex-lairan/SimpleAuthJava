package fr.esgi.simple_auth_java;

import fr.esgi.simple_auth_java.auth.AuthenticationException;
import fr.esgi.simple_auth_java.auth.Authenticator;
import fr.esgi.simple_auth_java.register.Registor;
import fr.esgi.simple_auth_java.register.SignUpException;
import fr.esgi.simple_auth_java.reset.Resetor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Manage actions on a user.
 *
 * @author Tristan
 * @see Registor
 * @see Authenticator
 * @see Resetor
 * @see User
 */
@EqualsAndHashCode
@ToString
@Slf4j
public final class Manager {
    /**
     * Sign up a user with a system
     * @param registor the system / implementation
     * @return the new user
     */
    public User signUp(@NonNull final Registor registor) throws SignUpException {
        log.trace("signUp with {}", registor);
        final User result = registor.signUp();
        log.trace("signUp user : {}", result);
        return result;
    }

    /**
     * Sign In a user with a system
     * @param authenticator the system / implementation
     * @return th user identified
     */
    public User signIn(@NonNull final Authenticator authenticator) {
        log.trace("signIn with {}", authenticator);
        User result = null;
        try {
            result = authenticator.signIn(new HashMap<>(), new ArrayList<User>());
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        log.trace("signUp user : {}", result);
        return result;
    }

    /**
     * Reset user's id with a system
     * @param user the user to reset
     * @param resetor the system / implementation
     */
    public void reset(@NonNull User user, @NonNull final Resetor resetor) throws Exception {
        log.trace("reset {} with {}", user, resetor);
        resetor.reset(user);
        log.trace("reset end for {}", user);
    }
}
