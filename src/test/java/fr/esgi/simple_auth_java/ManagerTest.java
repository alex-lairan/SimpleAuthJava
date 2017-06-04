package fr.esgi.simple_auth_java;

import fr.esgi.simple_auth_java.auth.Authentificator;
import fr.esgi.simple_auth_java.register.Registor;
import fr.esgi.simple_auth_java.reset.Forgetor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by Blixel on 04/06/2017.
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
    public void signUp() throws Exception {
        Registor regist = mock(Registor.class);
        when(regist.signUp()).thenReturn(user);
        assertThat(manager.signUp(regist)).isSameAs(user);
        verifyZeroInteractions(user);
        verify(regist, times(1)).signUp();
    }

    @Test
    public void signIn() throws Exception {
        Authentificator auth = mock(Authentificator.class);
        when(auth.signIn()).thenReturn(user);
        assertThat(manager.signIn(auth)).isSameAs(user);
        verifyZeroInteractions(user);
        verify(auth, times(1)).signIn();
    }

    @Test
    public void reset() throws Exception {
        Forgetor forgetor = mock(Forgetor.class);
        doNothing().when(forgetor).reset(user);
        doNothing().when(forgetor).reset(anyObject()); //doThrow ?
        manager.reset(user, forgetor);
        verify(forgetor, times(1)).reset(user);
        //verify(user, atLeastOnce()).setPassword(any());
        verifyNoMoreInteractions(forgetor, user);
    }
}
