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

    @Mock
    User testUser;
    Manager testManager = new Manager();

    Forgetor resetPasswordByMail = mock(ForgetorPasswordByMail.class);
    Forgetor resetPaswordWithOldPassword = mock(ForgetorPasswordWithOldPwd.class);
    Forgetor resetPasswordWithTempPwdByMail = mock(ForgetorPasswordWithTempPwdByMail.class);

    @Test(expected = ForgetException.class)
    public void should_fail_because_no_mail()
    {
        resetPasswordByMail.reset(testUser);
    }

    @Test(expected = ForgetException.class)
    public void should_fail_because_old_password_is_incorrect()
    {
        resetPaswordWithOldPassword.reset(testUser);
    }

    @Test
    public void should_success()
    {

    }
}

