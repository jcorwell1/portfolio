package edu.gatech.seclass;

import org.junit.Test;
//this is statement coverage testing
public class FlawedClassTestBC1 {


    @Test//100% branch no fault
    public void flawedMethod1BC1() { //initial test
        int a = 2;
        int b = 5;
        FlawedClass.flawedMethod1(a, b);


    }
}


