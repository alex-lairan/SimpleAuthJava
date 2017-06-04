package fr.esgi.simple_auth_java;

import fr.esgi.simple_auth_java.auth.Authentificator;
import fr.esgi.simple_auth_java.register.Register;
import fr.esgi.simple_auth_java.reset.Reseter;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * Manage actions on a user.
 *
 * @author Tristan
 * @see Register
 * @see Authentificator
 * @see Reseter
 * @see User
 */
@EqualsAndHashCode
@ToString
public final class Manager {
    /**
     * Sign up a user with a system
     * @param register the system / implementation
     * @return the new user
     */
    public User signUp(@NonNull final Register register) {
        return register.signUp();
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
     * @param reseter the system / implementation
     */
    public void reset(@NonNull User user, @NonNull final Reseter reseter) {
        reseter.reset(user);
    }
}
