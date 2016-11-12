package ru.dvfu.agregator.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import ru.dvfu.agregator.config.SpringConfiguration;
import ru.dvfu.agregator.service.AuthorizationService;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by Anton Nesudimov on 12.11.2016.
 */
@ContextConfiguration(classes = {SpringConfiguration.class, Authorization.class})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class AuthorizationTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    AuthorizationService mockService = spy(AuthorizationService.class);

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void successfulRegistration() throws Exception {
        when(mockService.register(any(), any())).thenReturn("someToken");

        mockMvc.perform(post("/api/auth")
                .param("login", "Tony")
                .param("password", "pass"))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("token", "someToken"));
    }

    @Test
    public void successfulAuthorization() throws Exception {
        when(mockService.authorize(any(), any())).thenReturn("someToken");

        mockMvc.perform(get("/api/auth")
                .param("login", "Tony")
                .param("password", "pass"))
                .andExpect(status().isOk())
                .andExpect(flash().attribute("token", "someToken"));
    }

    @Test
    public void unsuccessfulAuthorization() throws Exception {
        when(mockService.authorize(any(), any())).thenReturn(null);

        mockMvc.perform(get("/api/auth")
                .param("login", "Tony")
                .param("password", "pass"))
                .andExpect(status().isUnauthorized())
                //не факт, что сработает, если что удали строку. Она проверяет,что в body ничего не передается
                .andExpect(flash().attributeCount(0));
    }

}