package edu.gatech.seclass.capitalize;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class MyMainTest {
    //typical all kinds of character string type
    private File createInputFile1() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Hey guys,\n" +
                "i like biscuits!\n" +
                "They treat me very well in the summertime.\n" +
                "feels like a haiku, \n"
                + "whatever...though \"bacon\" and biscuits!");

        fileWriter.close();
        return file1;

    }
    //alphanumerics only
    private File createInputFile2() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("appblees666");

        fileWriter.close();
        return file2;
    }

    //string of nothing but special characters
    private File createInputFile3() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("$^$*&@#((*?!'?!!(!");

        fileWriter.close();
        return file3;
    }
    //string of nothing but spaces
    private File createInputFile4() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("          ");

        fileWriter.close();
        return file4;
    }
    //empty string
    private File createInputFile5() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("");

        fileWriter.close();
        return file5;
    }
    //one word all char types inc. space at end
    //alphanumerics only
    private File createInputFile6() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("appblees666! ");

        fileWriter.close();
        return file6;
    }
	
/*
Place all  of your tests in this class, optionally using MainTest.java as an example.
*/

    //Purpose: Test the -s flag
    //Frame:   53
    @Test
    public void capitalizeTest1() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-s", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Hey guys,\n" +
                "i like biscuits!\n" +
                "They treat me very well in the summertime.\n" +
                "Feels like a haiku, \n"
                + "whatever...Though \"bacon\" and biscuits!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test the -l flage
    // Frame #: 51
    @Test
    public void capitalizeTest2() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-l", inputFile1.getPath()};
        Main.main(args);

        String expected2 = "Hey guys,\n" +
                "I like biscuits!\n" +
                "They treat me very well in the summertime.\n" +
                "Feels like a haiku, \n"
                + "Whatever...though \"bacon\" and biscuits!"

        String actual2 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected2, actual2);
    }

    // Purpose: To test the -e flag
    // Frame #: 52
    @Test
    public void capitalizeTest3() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-e", inputFile1.getPath()};
        Main.main(args);

        String expected3 = "Hey guys,\n" +
                "I LIKE BISCUITS!\n" +
                "They treat me very well in the summertime.\n" +
                "feels like a haiku, \n"
                + "whatever...THOUGH \"BACON\" AND BISCUITS!";

        String actual3 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected3, actual3);
    }

    // Purpose: test the -x flag
    // Frame #: 54
    @Test
    public void capitalizeTest4() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-x", inputFile1.getPath()};
        Main.main(args);

        String expected4 = "hey guys,\n" +
                "i like biscuits!\n" +
                "they treat me very well in the summertime.\n" +
                "feels like a haiku, \n"
                + "whatever...though \"bacon\" and biscuits!";

        String actual4 = getFileContent(inputFile1.getPath());


        assertEquals("The files differ!", expected4, actual4);
    }
    // Purpose: test no flag
    // Frame #: 50
    @Test
    public void capitalizeTest5() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {inputFile1.getPath()};
        Main.main(args);

        String expected5 = "Hey Guys,\n" +
                "I Like Biscuits!\n" +
                "They Treat Me Very Well In The Summertime.\n" +
                "Feels Like A Haiku, \n"
                + "Whatever...though \"bacon\" And Biscuits!";

        String actual5 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected5, actual5);
    }


    // Purpose: test an empty file
    // Frame #: 1
    @Test
    public void capitalizeTest6() {
        String args[] = {inputFile5.getPath()}; //invalid argument
        Main.main(args);
        assertEquals("Your file is EMPTY", errStream.toString().trim());
    }

    // Purpose: -l test on string with only one word
    // Frame #: 6
    @Test
    public void capitalizeTest7() throws Exception {
        File inputFile2 = createInputFile2();

        String args[] = {"-l",inputFile2.getPath()};
        Main.main(args);

        String expected7 = "Appblees666";

        String actual7 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected7, actual7);
    }
    // Purpose: test capitalization of one word
    // Frame #: 5
    @Test
    public void capitalizeTest8() throws Exception {
        File inputFile2 = createInputFile2();

        String args[] = {inputFile2.getPath()};
        Main.main(args);

        String expected8 = "appblees666";

        String actual8 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected8, actual8);
    }
    // Purpose: -e -s test on string with only one word
    // Frame # 17
    @Test
    public void capitalizeTest9() throws Exception {
        File inputFile2 = createInputFile2();

        String args[] = {"-e -s",inputFile2.getPath()};
        Main.main(args);

        String expected9 = "applebees";

        String actual9 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected9, actual9);
    }
    // Purpose: test a string of empty spaces
    // Frame #: 3
    @Test
    public void capitalizeTest10() throws Exception {
        File inputFile4 = createInputFile4();

        String args[] = {inputFile4.getPath()};
        Main.main(args);

        String expected10 = "          ";

        String actual10 = getFileContent(inputFile4.getPath());

        assertEquals("The files differ!", expected10, actual10);
    }

    // Purpose: test -e on a string with one word and punctuation and a space
    // Frame # 37
    @Test
    public void capitalizeTest11() throws Exception {
        File inputFile6 = createInputFile6();

        String args[] = {"-e",inputFile6.getPath()};
        Main.main(args);

        String expected11 = "APPLEBEES666! ";

        String actual11 = getFileContent(inputFile6.getPath());

        assertEquals("The files differ!", expected11, actual11);
    }
    // Purpose: test -e on a string with one word and punctuation and a space
    // Frame # 36
    @Test
    public void capitalizeTest12() throws Exception {
        File inputFile6 = createInputFile6();

        String args[] = {"-l",inputFile6.getPath()};
        Main.main(args);

        String expected12 = "Applebees666! ";

        String actual12 = getFileContent(inputFile6.getPath());

        assertEquals("The files differ!", expected12, actual12);
    }
    // Purpose: test a string of only special characters
    // Frame # 2
    @Test
    public void capitalizeTest12() throws Exception {
        File inputFile3 = createInputFile3();

        String args[] = {inputFile3.getPath()};
        Main.main(args);

        String expected12 = "$^$*&@#((*?!'?!!(!";

        String actual12 = getFileContent(inputFile3.getPath());

        assertEquals("The files differ!", expected12, actual12);
    }
    // string test with -s -x
    // Frame # 64
    @Test
    public void capitalizeTest13() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-s -x",inputFile1.getPath()};
        Main.main(args);

        String expected13 = "Hey guys,\n" +
                "i like biscuits!\n" +
                "They treat me very well in the summertime.\n" +
                "Feels like a haiku, \n"
                + "whatever...though \"bacon\" and biscuits!";

        String actual13 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected13, actual13);
    }
    // string test with -e -x
    // Frame # 63
    @Test
    public void capitalizeTest14() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-e -x",inputFile1.getPath()};
        Main.main(args);

        String expected14 = "HEY GUYS,\n" +
                "I LIKE BISCUITS!\n" +
                "They treat me very well in the summertime.\n" +
                "feels like a haiku, \n"
                + "whatever...THOUGH \"BACON\" AND BISCUITS!";

        String actual3 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected14, actual14);
    }
    // Purpose: -e -s test on string
    // Frame # 62
    @Test
    public void capitalizeTest15() throws Exception {
        File inputFile15 = createInputFile1();

        String args[] = {"-e -s",inputFile1.getPath()};
        Main.main(args);

        String expected3 = "Hey guys,\n" +
                "i like biscuits!\n" +
                "They treat me very well in the summertime.\n" +
                "feels like a haiku, \n"
                + "whatever...though \"bacon\" and biscuits!";

        String actual3 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected15, actual15);
    }

}
