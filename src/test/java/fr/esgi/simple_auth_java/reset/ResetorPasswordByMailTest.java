package fr.esgi.simple_auth_java.reset;

import fr.esgi.simple_auth_java.Manager;
import fr.esgi.simple_auth_java.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Created by Yohan FAIRFORT on 08/06/2017.
 */
public class ResetorPasswordByMailTest {
    @Mock
    User testUser;

    Resetor resetPasswordByMail = mock(ResetorPasswordByMail.class);

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Mockito.reset(testUser);
    }

    @Test(expected = ResetException.class)
    public void should_fail_because_wrong_mail()
    {
        testUser.setEmail(null);
        resetPasswordByMail.reset(testUser);
    }
    @Test
    public void should_success()
    {
        String oldPass = testUser.getPassword();
        resetPasswordByMail.reset(testUser);
        assertThat(testUser.getPassword()).isNotEqualTo(oldPass);
    }
}
