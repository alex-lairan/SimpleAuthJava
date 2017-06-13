package fr.esgi.simple_auth_java.reset;

import fr.esgi.simple_auth_java.User;
import fr.esgi.simple_auth_java.common.Mailer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Yohan FAIRFORT on 08/06/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ResetorPasswordByMailTest {
    @Mock private User testUser;
    @Mock private Mailer mailer;
    private ResetorPasswordByMail resetor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Mockito.reset(testUser);
        when(testUser.getEmail()).thenReturn("my@email.com");
        when(testUser.getFirst_name()).thenReturn("first");
        when(testUser.getLast_name()).thenReturn("last");
        //when(testUser.getPassword()).thenReturn("oldPassword");
        //when(testUser.getEncryptor()).thenReturn(new PasswordEncryptDisable());
        Mockito.reset(mailer);
        //when(mailer.validate(any())).thenCallRealMethod();
        resetor = new ResetorPasswordByMail(this.mailer); //problem init of mockito
    }

    @Test
    public void should_success() {
        resetor.reset(testUser);
        verify(testUser).setPassword(any());
        verify(mailer).sendMail(any());
        //TODO: assert pwd mail == password
    }
}
