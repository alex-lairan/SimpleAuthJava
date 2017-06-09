package fr.esgi.simple_auth_java;

import fr.esgi.simple_auth_java.auth.Authentificator;
import fr.esgi.simple_auth_java.register.Registor;
import fr.esgi.simple_auth_java.register.SignUpException;
import fr.esgi.simple_auth_java.reset.Forgetor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.io.IOException;

/**
 * Manage actions on a user.
 *
 * @author Tristan
 * @see Registor
 * @see Authentificator
 * @see Forgetor
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
    public User signUp(@NonNull final Registor registor) throws SignUpException {
        return registor.signUp();
    }

    /**
     * Sign In a user with a system
     * @param authentificator the system / implementation
     * @return the user identified
     */
    public User signIn(@NonNull final Authentificator authentificator) {
        return authentificator.signIn();
    }

    /**
     * Reset user's id with a system
     * @param user the user to reset
     * @param forgetor the system / implementation
     */
    public void reset(@NonNull User user, @NonNull final Forgetor forgetor) {
        forgetor.reset(user);
    }
}
