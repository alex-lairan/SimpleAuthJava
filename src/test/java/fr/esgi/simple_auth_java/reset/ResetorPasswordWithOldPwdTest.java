package fr.esgi.simple_auth_java.reset;

import fr.esgi.simple_auth_java.Manager;
import fr.esgi.simple_auth_java.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Yohan FAIRFORT on 08/06/2017.
 */
public class ResetorPasswordWithOldPwdTest {
    @Mock
    User testUser;

    Resetor ResetorPasswordWithOldPwd = new ResetorPasswordWithOldPwd();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Mockito.reset(testUser);
    }

    @Test(expected = WeakPasswordException.class)
    public void should_fail_because_weak_new_password()
    {
        //attend aussi des inputs?
        testUser.setPassword("weak");
        ResetorPasswordWithOldPwd.reset(testUser);
    }

    @Test(expected= BadPasswordMatchException.class)
    public void should_fail_because_bad_password()
    {
        ResetorPasswordWithOldPwd.reset(testUser);
    }

    @Test
    public void should_success()
    {
        User oldUser = testUser;
        ResetorPasswordWithOldPwd.reset(testUser);
        assertThat(testUser).isNotSameAs(oldUser);
    }
}
