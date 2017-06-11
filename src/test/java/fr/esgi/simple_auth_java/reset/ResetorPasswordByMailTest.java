package fr.esgi.simple_auth_java.reset;

import fr.esgi.simple_auth_java.Manager;
import fr.esgi.simple_auth_java.User;
import fr.esgi.simple_auth_java.common.Mailer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Yohan FAIRFORT on 08/06/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ResetorPasswordByMailTest {
    @Mock
    User testUser;
    @Mock
    Mailer mailer;
    Resetor resetPasswordByMail = new ResetorPasswordByMail();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        testUser= mock(User.class);
    }

    @Test(expected = ResetException.class)
    public void should_fail_because_wrong_mail()
    {
        resetPasswordByMail.reset(testUser);
        when(testUser.getEmail());
    }

    @Test
    public void should_success()
    {
        String oldPass = testUser.getPassword();
        resetPasswordByMail.reset(testUser);
        assertThat(testUser.getPassword()).isNotEqualTo(oldPass);
    }
}
