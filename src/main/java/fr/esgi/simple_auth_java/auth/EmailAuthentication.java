package fr.esgi.simple_auth_java.auth;

import fr.esgi.simple_auth_java.User;

import java.util.HashMap;
import java.util.List;

public class EmailAuthentication implements Authenticator {
    private List<User> users;
    private String     email;
    private String     password;

    public EmailAuthentication(HashMap<String, Object> map, List<User> users) {
        email    = (String) map.get("email");
        password = (String) map.get("message");

        this.users = users;
    }

    @Override
    public User signIn() throws AuthenticationException {
        return users.stream()
                .filter(u -> u.getEmail().equals(email))
                .filter(u -> u.getPassword().equals(u.getEncryptor().encrypt(password)))
                .findAny().orElseThrow(AuthenticationException::new);
    }
}
