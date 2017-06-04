package fr.esgi.simple_auth_java;

import lombok.Data;
import lombok.NonNull;

/**
 * Created by Blixel on 04/06/2017.
 */
@Data
public class User {
    @NonNull String email, first_name, last_name;
    @NonNull String password;
}
