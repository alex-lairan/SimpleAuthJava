package fr.esgi.simple_auth_java.reset;

import fr.esgi.simple_auth_java.User;
import lombok.NonNull;

/**
 * Created by Blixel on 04/06/2017.
 */
public class ResetorPasswordByMail implements Resetor {
    /**
     * Reset identification of a user
     *
     * @param user {@link User} to reset
     */
    @Override
    public void reset(@NonNull final User user) throws IllegalResetException, ResetException {
    }
}
