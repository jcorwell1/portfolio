package edu.gatech.seclass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyCustomStringTest {

    private MyCustomStringInterface mycustomstring;

    @Before
    public void setUp() {
        mycustomstring = new MyCustomString();
    }

    @After
    public void tearDown() {
        mycustomstring = null;
    }

    @Test
    public void testCountNumbers1() { //initial test
        mycustomstring.setString("H3y, l3t'5 put s0me d161ts in this 5tr1n6!11!!");
        assertEquals(9, mycustomstring.countNumbers());
    }
    @Test
    public void testCountNumbers2() { //all numbers and no characters
        mycustomstring.setString("666666666666666666");
        assertEquals(1, mycustomstring.countNumbers());

    }

    @Test
    public void testCountNumbers3() { //nontrivial characters
        mycustomstring.setString("90210/////####??!?!?aa&*&&^*&^#6");
        assertEquals(2, mycustomstring.countNumbers());
    }

    @Test
    public void testCountNumbers4() {   ///string with no numbers
        mycustomstring.setString("no numbers in this string");
        assertEquals(0, mycustomstring.countNumbers());
    }

    @Test
    public void testCountNumbers5() {   ///empty string
        mycustomstring.setString("");
        assertEquals(0, mycustomstring.countNumbers());
    }

    @Test
    public void testCountNumbers6() {  //lots of large numbers

        mycustomstring.setString("6667777 828374374 49872 2984 28243750 2384924 98798 98879668789798798798979879879876876876876876876876");
        assertEquals(8, mycustomstring.countNumbers());


    }

    @Test
    public void testReverseNCharacters1() {//initial given test
        mycustomstring.setString("Peter Piper picked a peck of pickled peppers.");
        assertEquals("etePiP r repkcipa decep fo kcip delkpep srep.", mycustomstring.reverseNCharacters(4, false));
    }

    @Test
    public void testReverseNCharacters2() {//testing with padding
        mycustomstring.setString("Peter Piper picked a peck of pickled peppers.");
        assertEquals("etePiP r repkcipa decep fo kcip delkpep srepXXX.", mycustomstring.reverseNCharacters(4, true));
    }

    @Test
    public void testReverseNCharacters3() {//testing when n= length of string
        mycustomstring.setString("Peter Piper");
        assertEquals("repiP reteP", mycustomstring.reverseNCharacters(11, false));
    }

    @Test    (expected = NullPointerException.class)//nullpointer exception test
    public void testReverseNCharacters4() {
        mycustomstring.setString(null);
        mycustomstring.reverseNCharacters(2, true);

    }

    @Test   (expected = IllegalArgumentException.class)//test Illegal arguments exception
    public void testReverseNCharacters5() {

        mycustomstring.setString("testtest");
        mycustomstring.reverseNCharacters(-1, false);


    }

    @Test   (expected = IllegalArgumentException.class)//test Illegal arguments exception
    public void testReverseNCharacters6() {

        mycustomstring.setString("testtest");
        mycustomstring.reverseNCharacters(0, true);


    }


    @Test
    public void testReverseNCharacters7() { ////n=1 no reversal test

        mycustomstring.setString("hello");
        assertEquals("hello", mycustomstring.reverseNCharacters(1, false));

    }

    @Test   //empty string should return empty
    public void testReverseNCharacters8() {
        mycustomstring.setString("");
        assertEquals("", mycustomstring.reverseNCharacters(5, false));
    }
    @Test   //empty string should return empty.. even if you pad it!
    public void testReverseNCharacters9() {
        mycustomstring.setString("");
        assertEquals("", mycustomstring.reverseNCharacters(5, true));
    }

    @Test //n greater than length reverse entire string
    public void testReverseNCharacters10() {

        mycustomstring.setString("hello");
        assertEquals("olleh", mycustomstring.reverseNCharacters(6, false));

    }

    @Test //padding a string with n greater than string should reverse and pad
    public void testReverseNCharacters11() {

        mycustomstring.setString("hello");
        assertEquals("XXXXXXXXXXolleh", mycustomstring.reverseNCharacters(15, true));

    }



    @Test //initial given test
    public void testConvertDigitsToNamesInSubstring1() {
        mycustomstring.setString("H3y, l3t'5 put 50me d161ts in this 5tr1n6!11!!");
        mycustomstring.convertDigitsToNamesInSubstring(17, 24);
        assertEquals("H3y, l3t'5 put 5Zerome dOnesixonets in this 5tr1n6!11!!", mycustomstring.getString());
    }

    @Test //test entire length of string
    public void testConvertDigitsToNamesInSubstring2() {
        mycustomstring.setString("666");
        mycustomstring.convertDigitsToNamesInSubstring(1, 3);
        assertEquals("Sixsixsix", mycustomstring.getString());

    }

    @Test (expected = MyIndexOutOfBoundsException.class)//test Illegal arguments exception longer than string
    public void testConvertDigitsToNamesInSubstring3() {

        mycustomstring.setString("666");
        mycustomstring.convertDigitsToNamesInSubstring(1, 9);
    }

    @Test (expected = NullPointerException.class)//test null string
    public void testConvertDigitsToNamesInSubstring4() {

        mycustomstring.setString(null);
        mycustomstring.convertDigitsToNamesInSubstring(1, 1);

    }

    @Test (expected = MyIndexOutOfBoundsException.class)//test Illegal arguments exception... startPos less than one
    public void testConvertDigitsToNamesInSubstring5() {


        mycustomstring.setString("lmnophelo5678andi");
        mycustomstring.convertDigitsToNamesInSubstring(0, 9);
    }

    @Test (expected = IllegalArgumentException.class)//test Illegal arguments exception... startPos greater than end
    public void testConvertDigitsToNamesInSubstring6() {
        mycustomstring.setString("lmnophelo5678andi");
        mycustomstring.convertDigitsToNamesInSubstring(3, 1);
    }

    @Test //test difficult string
    public void testConvertDigitsToNamesInSubstring7() {

        mycustomstring.setString("1789andthen there awsf and 71 //asdifhudjinolughbfarhj87878787 and 87878787987");
        mycustomstring.convertDigitsToNamesInSubstring(1, 40);
        assertEquals("Oneseveneightnineandthen there awsf and Sevenone //asdifhudjinolughbfarhj87878787 and 87878787987", mycustomstring.getString());
    }

    @Test (expected = NullPointerException.class) //pass in an empty string
    public void testConvertDigitsToNamesInSubstring8() {

        mycustomstring.setString("");
        mycustomstring.convertDigitsToNamesInSubstring(1, 1);
    }

    @Test //test difficult characters
    public void testConvertDigitsToNamesInSubstring9() {

        mycustomstring.setString("%^7***");
        mycustomstring.convertDigitsToNamesInSubstring(1, 6);
        assertEquals("%^Seven***", mycustomstring.getString());
    }

    @Test //no numbers!
    public void testConvertDigitsToNamesInSubstring10(){
        mycustomstring.setString("helloworld!");
        mycustomstring.convertDigitsToNamesInSubstring(4, 10);
        assertEquals("helloworld!", mycustomstring.getString());

    }

    @Test (expected = MyIndexOutOfBoundsException.class) //start pos less than 0.
    public void testConvertDigitsToNamesInSubstring11(){
    mycustomstring.setString("lmnophelo5678andi");
        mycustomstring.convertDigitsToNamesInSubstring(-1, 9);
    }


}
