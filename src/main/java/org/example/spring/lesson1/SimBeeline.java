package org.example.spring.lesson1;

import org.springframework.stereotype.Component;

@Component("SimBeeline")
public class SimBeeline implements Simcard{
    String name = "Билайн";
    @Override
    public void processing() {
        System.out.println ("Вставлена симка " + name);
    }

    @Override
    public String getName() {
        return name;
    }
}
