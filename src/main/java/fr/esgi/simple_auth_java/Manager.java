package fr.esgi.simple_auth_java;

import fr.esgi.simple_auth_java.auth.Authentificator;
import fr.esgi.simple_auth_java.register.Registor;
import fr.esgi.simple_auth_java.reset.Resetor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

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
public final class Manager {
    /**
     * Sign up a user with a system
     * @param registor the system / implementation
     * @return the new user
     */
    public User signUp(@NonNull final Registor registor) {
        return registor.signUp();
    }

    /**
     * Sign In a user with a system
     * @param authentificator the system / implementation
     * @return th user identified
     */
    public User signIn(@NonNull final Authentificator authentificator) {
        return authentificator.signIn();
    }

    /**
     * Reset user's id with a system
     * @param user the user to reset
     * @param resetor the system / implementation
     * @throws Exception an error occur during reset
     */
    public void reset(@NonNull User user, @NonNull final Resetor resetor) throws Exception { //TODO: create a "ManagerException" or "OperationException"
        resetor.reset(user);
    }
}
