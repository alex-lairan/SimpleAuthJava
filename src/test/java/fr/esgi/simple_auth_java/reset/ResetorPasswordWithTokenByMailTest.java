package fr.esgi.simple_auth_java.reset;

import fr.esgi.simple_auth_java.User;
import fr.esgi.simple_auth_java.common.Mailer;
import fr.esgi.simple_auth_java.password_encrypt.PasswordEncryptDisable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.simplejavamail.email.Email;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Yohan FAIRFORT on 08/06/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ResetorPasswordWithTokenByMailTest {
    @Mock private User testUser;
    @Mock private Mailer mailer;
    private ResetorPasswordWithTokenByMail resetor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Mockito.reset(testUser);
        when(testUser.getEmail()).thenReturn("my@email.com");
        when(testUser.getFirst_name()).thenReturn("first");
        when(testUser.getLast_name()).thenReturn("last");
        when(testUser.getPassword()).thenReturn("oldPassword");
        when(testUser.getEncryptor()).thenReturn(new PasswordEncryptDisable());
        Mockito.reset(mailer);
        when(mailer.validate(any())).thenCallRealMethod();
        resetor = new ResetorPasswordWithTokenByMail(this.mailer); //problem init of mockito
    }

    @Test(expected = IllegalResetException.class)
    public void should_fail_because_wrong_token() {
        String simulatedUserInput = "badToken" + System.getProperty("line.separator")
                        + "badToken" + System.getProperty("line.separator")
                        + "badToken" + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        resetor.reset(testUser);
    }

    @Test
    public void should_succeed() throws IOException {
        String simulatedUserInput = System.getProperty("line.separator")
                        + "newPassword" + System.getProperty("line.separator")
                        + "newPassword" + System.getProperty("line.separator");
        try(PipedOutputStream out = new PipedOutputStream();
            PipedInputStream in = new PipedInputStream(out)) {
            doAnswer((InvocationOnMock invocation) -> {
                out.write(((Email) (invocation.getArguments()[0])).getText().split(":")[1].trim().getBytes());
                out.write(simulatedUserInput.getBytes());
                out.flush();
                return null;
            }).when(mailer).sendMail(any());
            System.setIn(in);
            resetor.reset(testUser);
            verify(testUser).setPassword("newPassword");
            verify(mailer, atLeastOnce()).sendMail(any());
            verifyNoMoreInteractions(mailer);
        }
    }
}
