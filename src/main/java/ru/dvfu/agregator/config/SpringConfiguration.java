package ru.dvfu.agregator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dvfu.agregator.service.AuthorizationService;
import ru.dvfu.agregator.service.UserService;

/**
 * Created by Anton Nesudimov on 12.11.2016.
 */
@Configuration
public class SpringConfiguration {

    @Bean
    public AuthorizationService getAuthorizationService() {
        return new AuthorizationService();
    }

    @Bean
    public UserService getUserService() {
        return new UserService();
    }
}
