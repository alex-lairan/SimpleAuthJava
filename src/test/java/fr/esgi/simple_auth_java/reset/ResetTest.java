package fr.esgi.simple_auth_java.reset;

import fr.esgi.simple_auth_java.Manager;
import fr.esgi.simple_auth_java.User;
import fr.esgi.simple_auth_java.reset.*;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;

/**
 * Created by Yohan FAIRFORT on 06/06/2017.
 */
public class ResetTest {

    @Mock User testUser;
    Manager testManager = new Manager();

    Resetor resetPasswordWithTempPwdByMail = mock(ResetorPasswordWithTempPwdByMail.class);

    @Test
    public void should_success()
    {
        testManager.reset(testUser, resetPasswordWithTempPwdByMail);
    }
}

