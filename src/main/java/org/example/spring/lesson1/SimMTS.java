package org.example.spring.lesson1;

import org.springframework.stereotype.Component;

@Component("SimMTS")
public class SimMTS implements Simcard {
    String name = "МТС";
    @Override
    public void processing() {
        System.out.println ("Вставлена симка " + name);
    }

    @Override
    public String getName() {
        return name;
    }
}
