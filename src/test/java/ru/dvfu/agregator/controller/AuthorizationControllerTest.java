package ru.dvfu.agregator.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.dvfu.agregator.service.AuthorizationService;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Anton Nesudimov on 12.11.2016.
 */
public class AuthorizationControllerTest {

    @Mock
    private AuthorizationService authorizationService;

    private MockMvc mockMvc;

    @InjectMocks
    private AuthorizationController authorizationController;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(authorizationController).build();
    }

    @Test
    public void successfulRegistration() throws Exception {
        when(authorizationService.register(any(), any())).thenReturn(true);

        mockMvc.perform(post("/api/auth")
                .param("login", "Tony")
                .param("password", "pass"))
                .andExpect(status().isOk());

        verify(authorizationService).register("Tony", "pass");
    }

    @Test
    public void unsuccessfulRegistration() throws Exception {
        when(authorizationService.register(any(), any())).thenReturn(false);

        mockMvc.perform(post("/api/auth")
                .param("login", "Tony")
                .param("password", "pass"))
                .andExpect(status().isConflict());

        verify(authorizationService).register("Tony", "pass");
    }

    @Test
    public void successfulAuthorization() throws Exception {
        when(authorizationService.authorize(any(), any())).thenReturn(true);

        mockMvc.perform(get("/api/auth")
                .param("login", "Tony")
                .param("password", "pass"))
                .andExpect(status().isOk());

        verify(authorizationService).authorize("Tony", "pass");
    }

    @Test
    public void unsuccessfulAuthorization() throws Exception {
        when(authorizationService.authorize(any(), any())).thenReturn(false);

        mockMvc.perform(get("/api/auth")
                .param("login", "Tony")
                .param("password", "pass"))
                .andExpect(status().isUnauthorized());

        verify(authorizationService).authorize("Tony", "pass");
    }

}