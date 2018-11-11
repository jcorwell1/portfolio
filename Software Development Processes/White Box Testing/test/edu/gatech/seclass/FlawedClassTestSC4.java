package edu.gatech.seclass;

import org.junit.Test;

public class FlawedClassTestSC4 {


    @Test
    public void flawedMethodSC3one() {

        FlawedClass.flawedMethod4(true, false);


    }
    @Test
    public void flawedMethodSC3two() {

        FlawedClass.flawedMethod4(false, true);


    }

}
