package ru.dvfu.agregator.config;

import org.springframework.context.annotation.Configuration;
import ru.dvfu.agregator.service.AuthorizationService;

/**
 * Created by Anton Nesudimov on 12.11.2016.
 */
@Configuration
public class SpringConfiguration {

    public AuthorizationService getAuthorizationService() {
        return new AuthorizationService();
    }
}
