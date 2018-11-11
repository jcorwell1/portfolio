package edu.gatech.seclass;

import org.junit.Test;

public class FlawedClassTestXC5 {


    @Test
    public void flawedMethod5XC5() { //initial test


        FlawedClass.flawedMethod5(true, true);


    }
    @Test
    public void flawedMethod5XC4() { //initial test


        FlawedClass.flawedMethod5(false, false);


    }

    @Test
    public void flawedMethod5XC3() { //initial test

        FlawedClass.flawedMethod5(true, false);


    }
    @Test
    public void flawedMethod5XC2() { //initial test

        FlawedClass.flawedMethod5(false, true);


    }

}
