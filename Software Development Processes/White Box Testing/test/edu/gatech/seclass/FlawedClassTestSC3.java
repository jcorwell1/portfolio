package edu.gatech.seclass;

import org.junit.Test;

public class FlawedClassTestSC3 {


    @Test
    public void flawedMethodSC3one() { //initial test

        FlawedClass.flawedMethod5(true, false);


    }
    @Test
    public void flawedMethodSC3two() { //initial test

        FlawedClass.flawedMethod5(false, true);


    }

}
