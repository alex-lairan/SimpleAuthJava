package fr.esgi.simple_auth_java;

import fr.esgi.simple_auth_java.auth.Authenticator;
import fr.esgi.simple_auth_java.register.Registor;
import fr.esgi.simple_auth_java.reset.Resetor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Tristan
 */
@RunWith(MockitoJUnitRunner.class)
public class ManagerTest {
    @Mock private User user;
    private Manager manager = new Manager();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Mockito.reset(user);
    }

    @Test
    public void should_only_signUp() throws Exception {
        Registor regist = mock(Registor.class);
        when(regist.signUp()).thenReturn(user);
        assertThat(manager.signUp(regist)).isSameAs(user);
        verifyZeroInteractions(user);
        verify(regist, times(1)).signUp();
    }

    @Test
    public void should_only_signIn() throws Exception {
        Authenticator auth = mock(Authenticator.class);
        when(auth.signIn()).thenReturn(user);
        assertThat(manager.signIn(auth)).isSameAs(user);
        verify(auth, times(1)).signIn();
    }

    @Test
    public void should_only_reset() throws Exception {
        Resetor resetor = mock(Resetor.class);
        doNothing().when(resetor).reset(user);
        doNothing().when(resetor).reset(anyObject()); //doThrow ?
        manager.reset(user, resetor);
        verify(resetor, times(1)).reset(user);
        //verify(user, atLeastOnce()).setPassword(any());
        verifyNoMoreInteractions(resetor, user);
    }
}
