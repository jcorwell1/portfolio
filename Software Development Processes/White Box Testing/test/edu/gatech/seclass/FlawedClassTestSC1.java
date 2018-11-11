package edu.gatech.seclass;

import org.junit.Test;
//100% statement coverage with fault
public class FlawedClassTestSC1 {


    @Test
    public void flawedMethod1SC1() {
        int a = 6;
        int b = 6;
        FlawedClass.flawedMethod1(a, b);

    }

    @Test
    public void flawedMethod1SC2(){
        //this one catches the fault
        int a = -3;
        int b = 3;
        FlawedClass.flawedMethod1(a, b);
    }
}
