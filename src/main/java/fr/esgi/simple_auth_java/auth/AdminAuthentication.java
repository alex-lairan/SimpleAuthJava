package fr.esgi.simple_auth_java.auth;

import fr.esgi.simple_auth_java.User;

import java.util.HashMap;
import java.util.List;

public class AdminAuthentication implements Authenticator {
    final private String secretKey = "world king";
    private List<User> users;
    private String     email;
    private String     password;
    private String     secret;

    public AdminAuthentication(HashMap<String, Object> map, List<User> users) {
        email    = (String) map.get("email");
        password = (String) map.get("message");
        secret   = (String) map.get("secret");

        this.users = users;
    }

    @Override
    public User signIn() throws AuthenticationException {
        if(!secretKey.equals(secret)) { throw new AuthenticationException(); }
        return users.stream()
                .filter(u -> u.getEmail().equals(email))
                .filter(u -> u.getPassword().equals(u.getEncryptor().encrypt(password)))
                .findAny().orElseThrow(AuthenticationException::new);
    }
}
