package fr.esgi.simple_auth_java.auth;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class PhoneMessageSender {
    @NotNull private String phoneNumber;
    @NotNull private String code;

    public PhoneMessageSender(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        send();
    }

    public void send() {
        // Complex stuffs to send SMS to the User
        code = UUID.randomUUID().toString();
    }

    public String getCode() {
        return code;
    }
}
