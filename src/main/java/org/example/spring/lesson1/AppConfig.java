package org.example.spring.lesson1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

    @Configuration
    @ComponentScan("org.example.spring.lesson1")
    public class AppConfig {
        public AppConfig() {
            Phone phone = new PhoneComponentImpl();
        }
        public Phone phone(Simcard simcard){
            return new PhoneImpl (simcard);
        }

    }
