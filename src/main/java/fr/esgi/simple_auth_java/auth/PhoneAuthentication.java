package fr.esgi.simple_auth_java.auth;

import fr.esgi.simple_auth_java.User;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class PhoneAuthentication implements Authenticator {
    @Override
    public User signIn(HashMap<String, Object> map, List<User> users) throws AuthenticationException {
        String email    = (String) map.get("email");
        String message  = (String) map.get("message");

        PhoneMessageSender sender = (PhoneMessageSender) map.get("phone_sender");

        if(!sender.getCode().equals(message)) { throw new AuthenticationException(); }
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findAny().orElseThrow(AuthenticationException::new);
    }
}
