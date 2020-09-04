package org.example.spring.lesson1;

public class PhoneImpl implements Phone{
    private Simcard simcard;
    private boolean broken;
    private boolean charge;

    public PhoneImpl() {
    }

    public PhoneImpl(Simcard simcard) {
        this.simcard = simcard;
        simcard.processing();
    }

    @Override
    public void breaking() {

    }

    @Override
    public Simcard getSimcard() {
        return simcard;
    }

    @Override
    public void setSimcard(Simcard simcard) {
        this.simcard = simcard;
    }

    @Override
    public boolean isBroken() {
        return false;
    }

    @Override
    public void putSimcard() {
        simcard.processing ();
    }

    @Override
    public void ring() {
        if (broken) {
            System.out.println("Phone is BROKEN!!!");
            return;
        }
        if (charge) {
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

    @Override
    public boolean isCharged() {
        return charge;
    }
}