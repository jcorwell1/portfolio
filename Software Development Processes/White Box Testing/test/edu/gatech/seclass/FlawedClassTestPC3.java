package edu.gatech.seclass;

import org.junit.Test;
//100% statement coverage with fault
public class FlawedClassTestPC3 {


    @Test
    public void flawedMethod3() { //initial test


        FlawedClass.flawedMethod5(true, true);


    }
    @Test
    public void flawedMethod3two() { //initial test


        FlawedClass.flawedMethod5(false, false);


    }

    @Test
    public void flawedMethod3three() { //initial test

        FlawedClass.flawedMethod5(true, false);


    }
    @Test
    public void flawedMethod4four() { //initial test

        FlawedClass.flawedMethod5(false, true);


    }

}
