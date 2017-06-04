package fr.esgi.simple_auth_java;

import fr.esgi.simple_auth_java.auth.Authentificator;
import fr.esgi.simple_auth_java.register.Register;
import fr.esgi.simple_auth_java.reset.Reseter;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * Created by Blixel on 04/06/2017.
 */
@EqualsAndHashCode
@ToString
public final class Manager {
    public User signUp(@NonNull final Register register) {
        return register.signUp();
    }

    public User signIn(@NonNull final Authentificator authentificator) {
        return authentificator.signIn();
    }

    public  void reset(@NonNull User user, @NonNull final Reseter reseter) {
        reseter.reset(user);
    }
}
