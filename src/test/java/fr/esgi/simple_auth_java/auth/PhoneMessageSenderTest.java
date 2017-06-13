package fr.esgi.simple_auth_java.auth;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PhoneMessageSenderTest {
    @Test
    public void should_send_new_uuid_as_code() {
        PhoneMessageSender phone = new PhoneMessageSender("0621387412");
        String oldCode = phone.getCode();
        phone.send();
        assertThat(phone.getCode()).isNotEqualTo(oldCode);
    }
}
