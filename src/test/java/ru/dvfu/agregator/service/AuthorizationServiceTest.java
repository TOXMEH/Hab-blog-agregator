package ru.dvfu.agregator.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.dvfu.agregator.model.User;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by nesud on 27.11.2016.
 */
public class AuthorizationServiceTest {

    @Mock
    private UserService userService = mock(UserService.class);

    @InjectMocks
    private AuthorizationService authorizationService = spy(AuthorizationService.class);

    private User mockUser = new User("login", "pass");

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void successfulRegistration() throws Exception {
        when(userService.getUserByName(any())).thenReturn(null);

        assertTrue(authorizationService.register("login", "pass"));

        verify(authorizationService).register(any(), any());

        verify(userService).getUserByName("login");

        verify(userService).save(any());
    }

    @Test
    public void unsuccessfulRegistration() throws Exception {
        when(userService.getUserByName(any())).thenReturn(mockUser);

        assertFalse(authorizationService.register("login", "pass"));

        verify(authorizationService).register(any(), any());

        verify(userService).getUserByName("login");

        verify(userService, times(0)).save(any());
    }

    @Test
    public void successfulAuthorization() throws Exception {
        when(userService.getUserByName(any())).thenReturn(mockUser);

        assertTrue(authorizationService.authorize("login", "pass"));
    }

    @Test
    public void unsuccessfulAuthorization() throws Exception {
        when(userService.getUserByName(any())).thenReturn(null);

        assertFalse(authorizationService.authorize("login", "pass"));
    }

}