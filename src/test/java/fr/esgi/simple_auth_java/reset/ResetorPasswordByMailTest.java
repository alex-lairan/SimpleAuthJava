package fr.esgi.simple_auth_java.reset;

import fr.esgi.simple_auth_java.Manager;
import fr.esgi.simple_auth_java.User;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;

/**
 * Created by Yohan FAIRFORT on 08/06/2017.
 */
public class ResetorPasswordByMailTest {
    @Mock
    User testUser;
    Manager testManager = new Manager();

    Resetor resetPasswordByMail = mock(ResetorPasswordByMail.class);

    @Test(expected = ResetException.class)
    public void should_fail_because_no_mail()
    {
        resetPasswordByMail.reset(testUser);
    }
}
