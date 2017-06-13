package fr.esgi.simple_auth_java.reset;

import fr.esgi.simple_auth_java.User;
import fr.esgi.simple_auth_java.password_encrypt.PasswordEncryptDisable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Yohan FAIRFORT on 08/06/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ResetorPasswordWithOldPwdTest {
    @Mock private User testUser;
    private ResetorPasswordWithOldPwd resetor = new ResetorPasswordWithOldPwd();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Mockito.reset(testUser);
        when(testUser.getPassword()).thenReturn("oldPassword");
        when(testUser.getEncryptor()).thenReturn(new PasswordEncryptDisable());
    }

    @Test(expected = IllegalResetException.class)
    public void should_fail_because_bad_old_password() throws IOException {
        String simulatedUserInput =
                "badPassword" + System.getProperty("line.separator")
                        + "badPassword" + System.getProperty("line.separator")
                        + "badPassword" + System.getProperty("line.separator");
        try(InputStream in = new ByteArrayInputStream(simulatedUserInput.getBytes())) {
            System.setIn(in);
            resetor.reset(testUser);
        }
    }

    @Test
    public void should_succeed() throws IOException {
        String simulatedUserInput =
                "oldPassword" + System.getProperty("line.separator")
                + "newPasswd" + System.getProperty("line.separator")
                + "newPasswd" + System.getProperty("line.separator");
        try(InputStream in = new ByteArrayInputStream(simulatedUserInput.getBytes())) {
            System.setIn(in);
            resetor.reset(testUser);
            verify(testUser).setPassword("newPasswd");
        }
    }
}
