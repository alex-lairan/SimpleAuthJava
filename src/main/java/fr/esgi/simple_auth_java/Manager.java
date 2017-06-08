package fr.esgi.simple_auth_java;

import fr.esgi.simple_auth_java.auth.Authentificator;
import fr.esgi.simple_auth_java.register.Registor;
import fr.esgi.simple_auth_java.reset.Resetor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Manage actions on a user.
 *
 * @author Tristan
 * @see Registor
 * @see Authentificator
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
    public User signUp(@NonNull final Registor registor) {
        log.trace("signUp with {}", registor);
        final User result = registor.signUp();
        log.trace("signUp user : {}", result);
        return result;
    }

    /**
     * Sign In a user with a system
     * @param authentificator the system / implementation
     * @return th user identified
     */
    public User signIn(@NonNull final Authentificator authentificator) {
        log.trace("signIn with {}", authentificator);
        final User result = authentificator.signIn();
        log.trace("signUp user : {}", result);
        return result;
    }

    /**
     * Reset user's id with a system
     * @param user the user to reset
     * @param resetor the system / implementation
     * @throws Exception an error occur during reset
     */
    public void reset(@NonNull User user, @NonNull final Resetor resetor) throws Exception { //TODO: create a "ManagerException" or "OperationException"
        log.trace("reset {} with {}", user, resetor);
        resetor.reset(user);
        log.trace("reset end for {}", user);
    }
}
