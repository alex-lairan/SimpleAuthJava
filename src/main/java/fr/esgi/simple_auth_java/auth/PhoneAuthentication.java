package fr.esgi.simple_auth_java.auth;

import fr.esgi.simple_auth_java.User;

import java.util.HashMap;
import java.util.List;

public class PhoneAuthentication implements Authenticator {
    private List<User>         users;
    private String             email;
    private String             message;
    private PhoneMessageSender sender;

    public PhoneAuthentication(HashMap<String, Object> map, List<User> users) {
        email   = (String) map.get("email");
        message = (String) map.get("message");
        sender  = (PhoneMessageSender) map.get("phone_sender");

        this.users = users;
    }

    @Override
    public User signIn() throws AuthenticationException {
        if(sender == null) { throw new AuthenticationException(); }
        if(!sender.getCode().equals(message)) { throw new AuthenticationException(); }
        return users.stream()
                .filter(u -> u.getEmail().equals(email))
                .findAny().orElseThrow(AuthenticationException::new);
    }
}
