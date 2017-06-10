package fr.esgi.simple_auth_java.auth;

import fr.esgi.simple_auth_java.User;

import java.util.HashMap;
import java.util.List;


public class EmailAuthentication implements Authenticator {
    @Override
    public User signIn(HashMap<String, Object> map, List<User> users) throws AuthenticationException {
        String email    = (String) map.get("email");
        String password = (String) map.get("password");
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .filter(user -> user.getPassword().equals(user.getEncryptor().encrypt(password)))
                .findAny().orElseThrow(AuthenticationException::new);
    }
}
