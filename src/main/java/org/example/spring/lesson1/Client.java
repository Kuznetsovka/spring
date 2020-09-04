package org.example.spring.lesson1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext ("config.xml");
        Phone phone = context.getBean("phone", Phone.class);
        phone.charging ();
        phone.ring();
        phone.hungUp ();
        ApplicationContext contextApp = new AnnotationConfigApplicationContext (AppConfig.class);
        Phone phone2 = contextApp.getBean("phoneComponent", Phone.class);
        phone2.putSimcard();
        phone2.charging ();
        phone2.ring();
        phone2.hungUp ();
    }
}
