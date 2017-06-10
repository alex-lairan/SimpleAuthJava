package fr.esgi.simple_auth_java.auth;

import fr.esgi.simple_auth_java.User;

import java.util.HashMap;
import java.util.List;

public class AdminAuthentication implements Authenticator {
    @Override
    public User signIn(HashMap<String, Object> map, List<User> users) throws AuthenticationException {
        String secret   = (String) map.get("secret");
        String email    = (String) map.get("email");
        String password = (String) map.get("password");

        if(!"world king".equals(secret)) { throw new AuthenticationException(); }
        User user = users.stream()
                .filter(u -> u.getEmail().equals(email))
                .findAny().orElseThrow(AuthenticationException::new);

        if(user.getPassword().equals(user.getEncryptor().encrypt(password))) {
            user.connect();
            return user;
        }
        throw new AuthenticationException();
    }
}
