package org.example.spring.lesson1;
import org.springframework.stereotype.Component;

@Component("SimMegafon")
public class SimMegafon implements Simcard{
    private String name = "Мегафон";
    @Override
    public void processing() {
        System.out.println ("Вставлена симка " + name);
    }

    @Override
    public String getName() {
        return name;
    }
}
