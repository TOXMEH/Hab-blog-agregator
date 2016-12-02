package ru.dvfu.agregator.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.dvfu.agregator.model.User;
import ru.dvfu.agregator.service.HubService;
import ru.dvfu.agregator.service.UserService;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by nesud on 02.12.2016.
 */
public class UserControllerTest {
    @Mock
    private UserService userService;

    @Mock
    private HubService hubService;

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    private User user = new User("login", "pass");

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getUser() throws Exception {
        when(userService.getUserByName(any())).thenReturn(user);

        mockMvc.perform(get("/api/user/login"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("login")))
                .andExpect(jsonPath("$.password", is("pass")));

        verify(userService).getUserByName("login");
    }

    @Test
    public void getNoUser() throws Exception {
        when(userService.getUserByName(any())).thenReturn(null);

        mockMvc.perform(get("/api/user/login"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(userService).getUserByName("login");
    }

    @Test
    public void setHubs() throws Exception {
        when(userService.getUserByName(any())).thenReturn(user);

        mockMvc.perform(put("/api/user/login/hubs")
                .param("hubs", "Java, Kotlin, C"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(userService).getUserByName("login");
        verify(userService).save(user);
    }

}