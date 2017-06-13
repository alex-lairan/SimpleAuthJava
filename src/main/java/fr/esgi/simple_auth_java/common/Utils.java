package fr.esgi.simple_auth_java.common;

import lombok.NonNull;
import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.io.Console;
import java.util.Optional;

/**
 * classe utilitaire
 *
 * @author Tristan
 */
public final class Utils {
    private Utils() { ; }

    /**
     * Return user response or "empty" if Cancel (Ctrl+C)
     * @param console console for input
     * @return user's response
     */
    private static Optional<String> getOrEscape(@NonNull final Console console) {
        Signal.handle(new Signal("INT"), new SignalHandler() {
            @Override
            public void handle(final Signal signal) {
                System.out.println("Got signal " + signal);
            }
        });
        return Optional.empty();
    }

}
