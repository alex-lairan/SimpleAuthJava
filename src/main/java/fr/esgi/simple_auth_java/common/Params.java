package fr.esgi.simple_auth_java.common;

import lombok.NonNull;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

/**
 * General parameters for application.<br/>
 * Thread-safe and read-only. Sinlgeton.
 *
 * @author Tristan
 */
public final class Params {
    final private Properties properties = new Properties();

    private Params() throws IOException {
        this.properties.load(this.getClass().getResourceAsStream("params.properties"));
    }

    public Optional<String> getParam(@NonNull final String name) {
        return Optional.ofNullable(this.properties.getProperty(name));
    }

    public String getParam(@NonNull final String name, @NonNull final String defaultValue) {
        return this.properties.getProperty(name, defaultValue);
    }


    private static Params instance = null;

    public static synchronized void initInstance() throws IOException {
        if(Objects.isNull(instance))
            instance = new Params();
    }

    public static Params getInstance() throws IOException {
        initInstance();
        return instance;
    }
}
