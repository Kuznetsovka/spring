package org.example.spring.lesson1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

    @Configuration
    @ComponentScan("org.example.spring.lesson1")
    public class AppConfig {
        @Bean(name = "phone")
        public Phone phone(Simcard simcard){
            return new PhoneImpl (simcard);
        }
    }
