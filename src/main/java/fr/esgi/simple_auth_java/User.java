package fr.esgi.simple_auth_java;

import lombok.Data;
import lombok.NonNull;

/**
 * User bean.
 *
 * @author Tristan
 */
@Data
public class User {
    @NonNull String email, first_name, last_name;
    @NonNull String password;
}
