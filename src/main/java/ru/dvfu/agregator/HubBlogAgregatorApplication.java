package ru.dvfu.agregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//import ru.dvfu.agregator.config.SpringConfiguration;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
//@Import({SpringConfiguration.class})
public class HubBlogAgregatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(HubBlogAgregatorApplication.class, args);
    }
}
