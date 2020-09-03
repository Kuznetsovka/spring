package org.example.spring.lesson1;

public interface Phone {
    void ring();
    void charging();
    void pickUp();
    void hungUp();
    void breaking();
    Simcard getSimcard();
    void setSimcard(Simcard simcard);
    boolean isCharged();
    boolean isBroken();
}
