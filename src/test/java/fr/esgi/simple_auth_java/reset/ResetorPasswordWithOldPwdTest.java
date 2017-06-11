package fr.esgi.simple_auth_java.reset;

import fr.esgi.simple_auth_java.Manager;
import fr.esgi.simple_auth_java.User;
import fr.esgi.simple_auth_java.common.Mailer;
import fr.esgi.simple_auth_java.password_encrypt.PasswordEncryptDisable;
import fr.esgi.simple_auth_java.password_encrypt.PasswordEncryptSHA256;
import fr.esgi.simple_auth_java.register.Registor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Yohan FAIRFORT on 08/06/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ResetorPasswordWithOldPwdTest {
    @Mock User testUser;

    Resetor ResetorPasswordWithOldPwd = new ResetorPasswordWithOldPwd();

    @Before
    public void setUp() throws Exception {
        testUser = mock(User.class);

        when(testUser.getPassword()).thenReturn("oldPassword");
        when(testUser.getEncryptor()).thenReturn(new PasswordEncryptDisable());

        Mockito.reset(testUser);
    }

    @Test(expected = ResetException.class)
    public void should_fail_because_weak_new_password()
    {
        //attend aussi des inputs?
        testUser.setPassword("weak");
        ResetorPasswordWithOldPwd.reset(testUser);
    }

    @Test
    public void should_succeed()
    {
        String simulatedUserInput =
                "oldPassword" + System.getProperty("line.separator")
                + "newPasswd" + System.getProperty("line.separator")
                + "newPasswd" + System.getProperty("line.separator");

        ByteArrayInputStream setIn = new ByteArrayInputStream(simulatedUserInput.getBytes());

        ResetorPasswordWithOldPwd.reset(testUser);
        System.setIn(setIn);
        verify(testUser).setPassword("newPasswd");
    }
}
