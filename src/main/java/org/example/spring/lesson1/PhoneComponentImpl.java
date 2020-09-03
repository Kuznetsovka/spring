package org.example.spring.lesson1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("phoneComponent")
@Scope("prototype")
public class PhoneComponentImpl implements Phone {
        private Simcard simcard;
        @Value("false")
        private boolean broken;
        @Value("false")
        private boolean charge;

    @Override
        public void ring() {
            if (broken) {
                System.out.println("Phone is BROKEN!!!");
                return;
            }
            if (!isCharged ()) {
                System.out.println("Charge is low!!!");
                return;
            }
        System.out.println ("Звонил телефон на СИМ " + simcard.getName ());
        }

    @Override
    public void charging() {
        System.out.println ("Телефон заряжен!");
        charge = true;
    }

    @Override
    public void pickUp() {
        System.out.println ("Звонок принят!");
    }

    @Override
    public void hungUp() {
        System.out.println ("Звонок завершен!");
    }

    @Autowired(required=false)
        @Qualifier("simMTS")
        public void setSimcard(Simcard simcard) {
            this.simcard = simcard;
        }

    @Override
    public boolean isCharged() {
        return charge;
    }

    @Override
        public void breaking() {
            this.broken = true;
        }

    @Override
    public Simcard getSimcard() {
        return simcard;
    }

    @Override
    public boolean isBroken() {
        return broken;
    }
}
