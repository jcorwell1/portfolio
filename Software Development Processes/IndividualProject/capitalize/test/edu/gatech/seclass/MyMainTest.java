package edu.gatech.seclass;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;


public class MyMainTest {

    private ByteArrayOutputStream outStream;
    private ByteArrayOutputStream errStream;
    private PrintStream outOrig;
    private PrintStream errOrig;
    private Charset charset = StandardCharsets.UTF_8;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        errStream = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(errStream);
        outOrig = System.out;
        errOrig = System.err;
        System.setOut(out);
        System.setErr(err);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(outOrig);
        System.setErr(errOrig);
    }

    // Some utilities

    private File createTmpFile() throws IOException {
        File tmpfile = temporaryFolder.newFile();
        tmpfile.deleteOnExit();
        return tmpfile;
    }


    private File createInputFile1() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Hey guys,\n" +
                "i like biscuits!\n" +
                "They treat me very well in the summertime.\n" +
                "feels like a haiku,\n"
                + "whatever...though \"bacon\" and biscuits!");

        fileWriter.close();
        return file1;

    }
    private File createInputFile2() throws Exception {
        File file2 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file2);

        fileWriter.write("appblees666");

        fileWriter.close();
        return file2;
    }
    //string of nothing but special characters
    private File createInputFile3() throws Exception {
        File file3 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file3);

        fileWriter.write("$^$*&@#((*?!'?!!(!");

        fileWriter.close();
        return file3;
    }
    //string of nothing but spaces
    private File createInputFile4() throws Exception {
        File file4 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file4);

        fileWriter.write("          ");

        fileWriter.close();
        return file4;
    }
    //empty string
    private File createInputFile5() throws Exception {
        File file5 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file5);

        fileWriter.write("");

        fileWriter.close();
        return file5;
    }
    //one word all char types inc. space at end
    //alphanumerics only
    private File createInputFile6() throws Exception {
        File file6 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file6);

        fileWriter.write("appblees666! ");

        fileWriter.close();
        return file6;
    }
    private File createInputFile7() throws Exception {
        File file7 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file7);

        fileWriter.write("Howdy Billy,\n" +
                "This is a test file for the capitalize utility.\n" +
                "let's make sure it has at least a few lines,\n" +
                "so that we can create some \n"
                + "interesting test cases...And let's say \"howdy\" to Bill again!");

        fileWriter.close();
        return file7;
    }
    private File createInputFile8() throws Exception {

        File file8 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file8);

        fileWriter.write("Please can I has 6 or 7 dollars? i want 6 or 7 dollars!");

        fileWriter.close();
        return file8;
    }
    private File createInputFile9() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Bill is,\n" +
                "in my opinion,\n" +
                "an easier name to spell than William.\n" +
                "Bill is shorter,\n" +
                "and Bill is\n" +
                "first alphabetically.");

        fileWriter.close();
        return file1;
    }
    private File createInputFile10() throws Exception {
        File file10 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file10);

        fileWriter.write("Howdy Bill, have you learned your abc and 123?\r\n" +
                "I know My Abc's.\r" +
                "It is important to know your abc's and 123's,\n" +
                "so repeat with me: abc! 123! Abc and 123!");

        fileWriter.close();
        return file10;
    }
    private File createInputFile11() throws Exception {
        File file11 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file11);

        fileWriter.write("AppleBananaCarrot.\r" +
                "DogElephantHorse.\r" +
                "Icecream. Jello");

        fileWriter.close();
        return file11;
    }
    private File createInputFile12() throws Exception {
        File file = createTmpFile();
        FileWriter fileWriter = new FileWriter(file);

        fileWriter.write("");

        fileWriter.close();
        return file;
    }
    private File createInputFile13() throws Exception {
        File file = createTmpFile();
        FileWriter fileWriter = new FileWriter(file);

        fileWriter.write("This is a very simple string" + System.lineSeparator() +
                "with a few lines" + System.lineSeparator() +
                "and not much else");

        fileWriter.close();
        return file;
    }
    private File createInputFile14() throws Exception {
        File file = createTmpFile();
        FileWriter fileWriter = new FileWriter(file);

        fileWriter.write("-- -- -- --");

        fileWriter.close();
        return file;
    }
    private File createInputFile15() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Mary had a csv,csv,csv\n" +
                "Mary had a csv,commas, in a csv.\n" +
                "csv,csv,csv");

        fileWriter.close();
        return file1;
    }
    private File createInputFile16() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("oneword");

        fileWriter.close();
        return file1;
    }
    private File createInputFile17() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("lol.rofl.roflmho");

        fileWriter.close();
        return file1;
    }


    private String getFileContent(String filename) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    // test cases

    //Purpose: Test the -s flag

    @Test
    public void capitalizeTest1() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-s", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Hey guys,\n" +
                "i like biscuits!\n" +
                "They treat me very well in the summertime.\n" +
                "Feels like a haiku,\n"
                + "whatever...Though \"bacon\" and biscuits!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }


    // Purpose: To test the -l flag


    @Test
    public void capitalizeTest2() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-l", inputFile1.getPath()};
        Main.main(args);

        String expected2 = "Hey guys,\n" +
                "I like biscuits!\n" +
                "They treat me very well in the summertime.\n" +
                "Feels like a haiku,\n"
                + "Whatever...though \"bacon\" and biscuits!";

        String actual2 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected2, actual2);
    }

    // Purpose: To test the -e flag

    @Test
    public void capitalizeTest3() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-e", inputFile1.getPath()};
        Main.main(args);

        String expected3 = "HEY GUYS,\n" +
                "I LIKE BISCUITS!\n" +
                "They treat me very well in the summertime.\n" +
                "feels like a haiku,\n"
                + "whatever...THOUGH \"BACON\" AND BISCUITS!";

        String actual3 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected3, actual3);
    }

    // Purpose: test the -x flag

    @Test
    public void capitalizeTest4() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-x", inputFile1.getPath()};
        Main.main(args);

        String expected4 = "Hey Guys,\n" +
                "I Like Biscuits!\n" +
                "They Treat Me Very Well In The Summertime.\n" +
                "Feels Like A Haiku,\n"
                + "Whatever...though \"bacon\" And Biscuits!";

        String actual4 = getFileContent(inputFile1.getPath());


        assertEquals("The files differ!", expected4, actual4);
    }
    // Purpose: test no flag

    @Test
    public void capitalizeTest5() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {inputFile1.getPath()};
        Main.main(args);

        String expected5 = "Hey Guys,\n" +
                "I Like Biscuits!\n" +
                "They Treat Me Very Well In The Summertime.\n" +
                "Feels Like A Haiku,\n"
                + "Whatever...though \"bacon\" And Biscuits!";

        String actual5 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected5, actual5);
    }

    // Purpose: -l test on string with only one word

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

    @Test
    public void capitalizeTest8() throws Exception {
        File inputFile2 = createInputFile2();

        String args[] = {inputFile2.getPath()};
        Main.main(args);

        String expected8 = "Appblees666";

        String actual8 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected8, actual8);
    }
    // Purpose: -e -s test on string with only one word

    @Test
    public void capitalizeTest9() throws Exception {
        File inputFile2 = createInputFile2();

        String args[] = {"-e", "-s",inputFile2.getPath()};
        Main.main(args);

        String expected9 = "Appblees666";

        String actual9 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected9, actual9);
    }
    // Purpose: test a string of empty spaces

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

    @Test
    public void capitalizeTest11() throws Exception {
        File inputFile6 = createInputFile6();

        String args[] = {"-e",inputFile6.getPath()};
        Main.main(args);

        String expected11 = "APPBLEES666!";

        String actual11 = getFileContent(inputFile6.getPath());

        assertEquals("The files differ!", expected11, actual11);
    }
    // Purpose: test -e on a string with one word and punctuation and a space

    @Test
    public void capitalizeTest12() throws Exception {
        File inputFile6 = createInputFile6();

        String args[] = {"-l",inputFile6.getPath()};
        Main.main(args);

        String expected12 = "Appblees666! ";

        String actual12 = getFileContent(inputFile6.getPath());

        assertEquals("The files differ!", expected12, actual12);
    }

    // string test with -s -x

    @Test
    public void capitalizeTest13() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-x", "-s",inputFile1.getPath()};
        Main.main(args);

        String expected13 = "Hey guys,\n" +
                "i like biscuits!\n" +
                "They treat me very well in the summertime.\n" +
                "Feels like a haiku,\n"
                + "whatever...Though \"bacon\" and biscuits!";

        String actual13 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected13, actual13);
    }
    // string test with -e -x

    @Test
    public void capitalizeTest14() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-x", "-e",inputFile1.getPath()};
        Main.main(args);

        String expected14 = "HEY GUYS,\n" +
                "I LIKE BISCUITS!\n" +
                "they treat me very well in the summertime.\n" +
                "feels like a haiku,\n"
                + "whatever...THOUGH \"BACON\" AND BISCUITS!";

        String actual14 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected14, actual14);
    }
    // Purpose: -e -s test on string

    @Test
    public void capitalizeTest15() throws Exception {
        File inputFile15 = createInputFile1();

        String args[] = {"-e","-s",inputFile15.getPath()};
        Main.main(args);

        String expected15 = "HEY GUYS,\n" +
                "I LIKE BISCUITS!\n" +
                "They treat me very well in the summertime.\n" +
                "Feels like a haiku,\n"
                + "whatever...THOUGH \"BACON\" AND BISCUITS!";

        String actual15 = getFileContent(inputFile15.getPath());

        assertEquals("The files differ!", expected15, actual15);
    }

    // Purpose: test a string of only special characters

    @Test
    public void capitalizeTest16() throws Exception {
        File inputFile3 = createInputFile3();

        String args[] = {inputFile3.getPath()};
        Main.main(args);

        String expected16 = "$^$*&@#((*?!'?!!(!";

        String actual16 = getFileContent(inputFile3.getPath());

        assertEquals("The files differ!", expected16, actual16);
    }


    //Test -l -x
    @Test
    public void capitalizeTest17() throws Exception {
        File inputFile8 = createInputFile8();

        String args[] = {"-l","-x", inputFile8.getPath()};
        Main.main(args);

        String expected17 = "Please can i has 6 or 7 dollars? i want 6 or 7 dollars!";

        String actual17 = getFileContent(inputFile8.getPath());

        assertEquals("The files differ!", expected17, actual17);
    }
    //test -x -l
    @Test
    public void capitalizeTest18() throws Exception {
        File inputFile8 = createInputFile8();

        String args[] = {"-x","-l", inputFile8.getPath()};
        Main.main(args);

        String expected17 = "Please can i has 6 or 7 dollars? i want 6 or 7 dollars!";

        String actual17 = getFileContent(inputFile8.getPath());

        assertEquals("The files differ!", expected17, actual17);
    }
    //test -e -x
    @Test
    public void capitalizeTest19() throws Exception {
        File inputFile8 = createInputFile8();

        String args[] = {"-e","-x", inputFile8.getPath()};
        Main.main(args);

        String expected17 = "please can i has 6 or 7 dollars? I WANT 6 OR 7 DOLLARS!";

        String actual17 = getFileContent(inputFile8.getPath());

        assertEquals("The files differ!", expected17, actual17);
    }
    //test -s -x
    @Test
    public void capitalizeTest20() throws Exception {
        File inputFile8 = createInputFile8();

        String args[] = {"-s","-x", inputFile8.getPath()};
        Main.main(args);

        String expected17 = "Please can i has 6 or 7 dollars? I want 6 or 7 dollars!";

        String actual17 = getFileContent(inputFile8.getPath());

        assertEquals("The files differ!", expected17, actual17);
    }
    //test a null argument
    //had to use verbatim from provided test case because the message was coded into the main class. And we were told
    //that our application had to test all instructor test cases as well.
    @Test
    public void capitalizeTest21() throws Exception {
        String args[] = null; //invalid argument
        Main.main(args);
        assertEquals("Usage: Capitalize  [-l] [-e] [-s] [string] [-x] <filename>", errStream.toString().trim());
    //test -s -x -e
    }
    @Test
    public void capitalizeTest22() throws Exception {
        File inputFile8 = createInputFile8();

        String args[] = {"-s","-x","-e", inputFile8.getPath()};
        Main.main(args);

        String expected17 = "Please can i has 6 or 7 dollars? I WANT 6 OR 7 DOLLARS!";

        String actual17 = getFileContent(inputFile8.getPath());

        assertEquals("The files differ!", expected17, actual17);
    }
    // test string of delimeters function
    @Test
    public void capitalizeTest23() throws Exception {
        File inputFile8 = createInputFile8();

        String args[] = {"-s", "?", inputFile8.getPath()};
        Main.main(args);

        String expected17 = "Please can I has 6 or 7 dollars? I want 6 or 7 dollars!";

        String actual17 = getFileContent(inputFile8.getPath());

        assertEquals("The files differ!", expected17, actual17);
    }
    //test -x -l -e
    @Test
    public void capitalizeTest24() throws Exception {
        File inputFile8 = createInputFile8();

        String args[] = {"-x","-l","-e", inputFile8.getPath()};
        Main.main(args);

        String expected17 = "Please can i has 6 or 7 dollars? I WANT 6 OR 7 DOLLARS!";

        String actual17 = getFileContent(inputFile8.getPath());

        assertEquals("The files differ!", expected17, actual17);
    }
    // test -l -e -x
    @Test
    public void capitalizeTest25() throws Exception {
        File inputFile8 = createInputFile8();

        String args[] = {"-l","-e","-x", inputFile8.getPath()};
        Main.main(args);

        String expected17 = "Please can i has 6 or 7 dollars? I WANT 6 OR 7 DOLLARS!";

        String actual17 = getFileContent(inputFile8.getPath());

        assertEquals("The files differ!", expected17, actual17);
    }
    // test -s -e -x
    @Test
    public void capitalizeTest26() throws Exception {
        File inputFile8 = createInputFile8();

        String args[] = {"-s","-e","-x", inputFile8.getPath()};
        Main.main(args);

        String expected17 = "Please can i has 6 or 7 dollars? I WANT 6 OR 7 DOLLARS!";

        String actual17 = getFileContent(inputFile8.getPath());

        assertEquals("The files differ!", expected17, actual17);
    }

    //test -l -s -x -e

    @Test
    public void capitalizeTest27() throws Exception {
        File inputFile8 = createInputFile8();

        String args[] = {"-l","-s","-x", "-e", inputFile8.getPath()};
        Main.main(args);

        String expected17 = "Please can i has 6 or 7 dollars? I WANT 6 OR 7 DOLLARS!";

        String actual17 = getFileContent(inputFile8.getPath());

        assertEquals("The files differ!", expected17, actual17);
    }
    @Test
    public void capitalizeTest28() throws Exception {
        File inputFile8 = createInputFile8();

        String args[] = {"-x","-e","-s","-l", inputFile8.getPath()};
        Main.main(args);

        String expected17 = "Please can i has 6 or 7 dollars? I WANT 6 OR 7 DOLLARS!";

        String actual17 = getFileContent(inputFile8.getPath());

        assertEquals("The files differ!", expected17, actual17);
    }

    //test -s -e -l -x
    @Test
    public void capitalizeTest29() throws Exception {
        File inputFile8 = createInputFile8();

        String args[] = {"-s","-e","-l","-x", inputFile8.getPath()};
        Main.main(args);

        String expected17 = "Please can i has 6 or 7 dollars? I WANT 6 OR 7 DOLLARS!";

        String actual17 = getFileContent(inputFile8.getPath());

        assertEquals("The files differ!", expected17, actual17);
    }

    //test -s -l -x -e

    @Test
    public void capitalizeTest30() throws Exception {
        File inputFile8 = createInputFile8();

        String args[] = {"-s","-l","-x", "-e",inputFile8.getPath()};
        Main.main(args);

        String expected17 = "Please can i has 6 or 7 dollars? I WANT 6 OR 7 DOLLARS!";

        String actual17 = getFileContent(inputFile8.getPath());

        assertEquals("The files differ!", expected17, actual17);
    }

    //test -s -e -x -l

    @Test
    public void capitalizeTest31() throws Exception {
        File inputFile8 = createInputFile8();

        String args[] = {"-s","-e","-x", "-l",inputFile8.getPath()};
        Main.main(args);

        String expected17 = "Please can i has 6 or 7 dollars? I WANT 6 OR 7 DOLLARS!";

        String actual17 = getFileContent(inputFile8.getPath());

        assertEquals("The files differ!", expected17, actual17);
    }




    //assignment test case examples for reference
    // Purpose: To provide an example of a test case format
    // Frame #: Instructor example 1 from assignment directions
    @Test
    public void mainTest1() throws Exception {
        File inputFile7 = createInputFile7();

        String args[] = {inputFile7.getPath()};
        Main.main(args);

        String expected7 = "Howdy Billy,\n" +
                "This Is A Test File For The Capitalize Utility.\n" +
                "Let's Make Sure It Has At Least A Few Lines,\n" +
                "So That We Can Create Some \n"
                + "Interesting Test Cases...And Let's Say \"howdy\" To Bill Again!";

        String actual7 = getFileContent(inputFile7.getPath());

        assertEquals("The files differ!", expected7, actual7);
    }

    // Purpose: To provide an example of a test case format
    // Frame #: Instructor example 2 from assignment directions
    @Test
    public void mainTest2() throws Exception {
        File inputFile7 = createInputFile7();

        String args[] = {"-l", inputFile7.getPath()};
        Main.main(args);

        String expected7 = "Howdy Billy,\n" +
                "This is a test file for the capitalize utility.\n" +
                "Let's make sure it has at least a few lines,\n" +
                "So that we can create some \n"
                + "Interesting test cases...And let's say \"howdy\" to Bill again!";

        String actual7 = getFileContent(inputFile7.getPath());

        assertEquals("The files differ!", expected7, actual7);
    }

    // Purpose: To provide an example of a test case format
    // Frame #: Instructor example 3 from assignment directions
    @Test
    public void mainTest3() throws Exception {
        File inputFile7 = createInputFile7();

        String args[] = {"-e", "-s", inputFile7.getPath()};
        Main.main(args);

        String expected2 = "Howdy Billy,\n" +
                "This is a test file for the capitalize utility.\n" +
                "Let's make sure it has at least a few lines,\n" +
                "so that we can create some \n"
                + "interesting test cases...AND LET'S SAY \"HOWDY\" TO BILL AGAIN!";

        String actual2 = getFileContent(inputFile7.getPath());

        assertEquals("The files differ!", expected2, actual2);
    }

    // Purpose: To provide an example of a test case format
    // Frame #: Instructor example 4 from assignment directions
    @Test
    public void mainTest4() throws Exception {
        File inputFile7 = createInputFile7();

        String args[] = {"-s",",","-x", inputFile7.getPath()};
        Main.main(args);

        String expected3 = "Howdy billy,\n" +
                "This is a test file for the capitalize utility.\n" +
                "let's make sure it has at least a few lines,\n" +
                "So that we can create some \n"
                + "interesting test cases...and let's say \"howdy\" to bill again!";

        String actual3 = getFileContent(inputFile7.getPath());

        assertEquals("The files differ!", expected3, actual3);
    }


    // Purpose: To provide an example of a test case format
    // Frame #: Instructor error example
    @Test
    public void mainTest5() throws Exception{
        String args[] = null; //invalid argument
        Main.main(args);
        assertEquals("Usage: Capitalize  [-l] [-e] [-s] [string] [-x] <filename>", errStream.toString().trim());



    }
    // Additional Test for Deliverable 2
    @Test
    public void mainTest6() throws Exception{
        String args[] = {"nofile.txt"};
        Main.main(args);

        assertEquals("File Not Found", errStream.toString().trim());
    }

    // Additional Test for Deliverable 2
    @Test
    public void mainTest7() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-s", ",", "-x", "-c", inputFile1.getPath()};
        Main.main(args);

        assertEquals("Usage: Capitalize  [-l] [-e] [-s] [string] [-x] [-c] [boolean] <filename>", errStream.toString().trim());
    }

    // Additional Test for Deliverable 2
    @Test
    public void mainTest8() throws Exception {
        File inputFile = createInputFile9();

        String args[] = {"-s", ",.?!", "-x", inputFile.getPath()};
        Main.main(args);

        String expected = "Bill is,\n" +
                "In my opinion,\n" +
                "An easier name to spell than william.\n" +
                "Bill is shorter,\n" +
                "And bill is\n" +
                "first alphabetically.";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Additional Test for Deliverable 2
    //I edited this one because it seemed to be missing a \n spec????????
    @Test
    public void mainTest9() throws Exception {
        File inputFile = createInputFile9();

        String args[] = {"-s", ",.?!", "-e", "-x", "-c", "true", inputFile.getPath()};
        Main.main(args);

        String expected = "BillIs,\n" +
                "InMyOpinion,\n" +
                "AnEasierNameToSpellThanWilliam.\n" +
                "BillIsShorter,\n" +
                "AndBillIs\n"+
                "FirstAlphabetically.";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Additional Test for Deliverable 2
    @Test
    public void mainTest10() throws Exception {
        File inputFile = createInputFile11();

        String args[] = {"-s", "-c", "false", inputFile.getPath()};
        Main.main(args);

        String expected = "Apple banana carrot.\r" +
                "Dog elephant horse.\r" +
                "Icecream. Jello";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Additional Test for Deliverable 2
    @Test
    public void mainTest11() throws Exception {
        File inputFile = createInputFile11();

        String args[] = {"-s", inputFile.getPath()};
        Main.main(args);

        String expected = "AppleBananaCarrot.\r" +
                "DogElephantHorse.\r" +
                "Icecream. Jello";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Additional Test for Deliverable 2
    @Test
    public void mainTest12() throws Exception {
        File inputFile = createInputFile10();

        String args[] = {"-e", inputFile.getPath()};
        Main.main(args);

        String expected = "Howdy Bill, have you learned your abc and 123?\r\n" +
                "I know My Abc's.\r" +
                "IT IS IMPORTANT TO KNOW YOUR ABC'S AND 123'S,\n" +
                "SO REPEAT WITH ME: ABC! 123! ABC AND 123!";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Additional Test for Deliverable 2
    //added -n because it was mising from example!
    @Test
    public void mainTest13() throws Exception {
        File inputFile = createInputFile10();

        String args[] = {"-e", "-c", "true", inputFile.getPath()};
        Main.main(args);

        String expected = "HowdyBill,HaveYouLearnedYourAbcAnd123?\r\n" +
                "IKnowMyAbc's.\r" +
                "ITISIMPORTANTTOKNOWYOURABC'SAND123'S,\n"+
                "SOREPEATWITHME:ABC! 123! ABCAND123!";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Additional Test for Deliverable 2
    @Test
    public void mainTest14() throws Exception {
        File inputFile = createInputFile12();

        String args[] = {"-s", "-x", inputFile.getPath()};
        Main.main(args);

        String expected = "";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Additional Test for Deliverable 2
    @Test
    public void mainTest15() throws Exception {
        File inputFile = createInputFile17();

        String args[] = {"-s", inputFile.getPath()};
        Main.main(args);

        String expected = "Lol.Rofl.Roflmho";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Additional Test for Deliverable 2
    @Test
    public void mainTest16() throws Exception {
        File inputFile = createInputFile13();

        String args[] = {"-l", inputFile.getPath()};
        Main.main(args);

        String expected = "This is a very simple string" + System.lineSeparator() +
                "With a few lines" + System.lineSeparator()+
                "And not much else";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Additional Test for Deliverable 2
    //the first t shouldnt be capitalized?????!?????/
    @Test
    public void mainTest17() throws Exception {
        File inputFile = createInputFile13();

        String args[] = {"-s", "i", inputFile.getPath()};
        Main.main(args);

        String expected = "ThiS iS a very siMple striNg" + System.lineSeparator() +
                "wiTh a few liNes" + System.lineSeparator() +
                "and not much else";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Additional Test for Deliverable 2
    @Test
    public void mainTest18() throws Exception {
        File inputFile = createInputFile14();

        String args[] = {"-l", "-x", inputFile.getPath()};
        Main.main(args);

        String expected = "-- -- -- --";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Additional Test for Deliverable 2
    @Test
    public void mainTest19() throws Exception {
        File inputFile = createInputFile14();

        String args[] = {"-s", "-", inputFile.getPath()};
        Main.main(args);

        String expected = "-- -- -- --";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Additional Test for Deliverable 2
    @Test
    public void mainTest20() throws Exception {
        File inputFile = createInputFile15();

        String args[] = {"-s", ",.", inputFile.getPath()};
        Main.main(args);

        String expected = "Mary had a csv,Csv,Csv\n" +
                "Mary had a csv,Commas, In a csv.\n" +
                "Csv,Csv,Csv";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Additional Test for Deliverable 2
    //also a problem with this one????
    @Test
    public void mainTest21() throws Exception {
        File inputFile = createInputFile15();

        String args[] = {"-s", "c", "-x", inputFile.getPath()};
        Main.main(args);

        String expected = "Mary had a cSv,cSv,cSv\n" +
                "mary had a cSv,cOmmas, in a cSv.\n" +
                "cSv,cSv,cSv";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Additional Test for Deliverable 2
    @Test
    public void mainTest22() throws Exception {
        File inputFile = createInputFile16();

        String args[] = {"-x", inputFile.getPath()};
        Main.main(args);

        String expected = "Oneword";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Additional Test for Deliverable 2
    @Test
    public void mainTest23() throws Exception {
        File inputFile = createInputFile16();

        String args[] = {"-c", "false", inputFile.getPath()};
        Main.main(args);

        String expected = "Oneword";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Additional Test for Deliverable 2
    @Test
    public void mainTest24() throws Exception {
        File inputFile = createInputFile17();

        String args[] = {"-x", "-x", "-x", inputFile.getPath()};
        Main.main(args);

        String expected = "Lol.rofl.roflmho";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Additional Test for Deliverable 2
    @Test
    public void mainTest25() throws Exception {
        File inputFile = createInputFile17();

        String args[] = {"-s", "l", "-c", "true", inputFile.getPath()};
        Main.main(args);

        String expected = "lOl.rofl.roflMho";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Additional Test for Deliverable 2
    @Test
    public void mainTest26() throws Exception {
        File inputFile = createInputFile10();
        String expected = getFileContent(inputFile.getPath());

        String args[] = {"-l", "-k", "true", inputFile.getPath()};
        Main.main(args);

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
        assertEquals("Usage: Capitalize  [-l] [-e] [-s] [string] [-x] [-c] [boolean] <filename>", errStream.toString().trim());
    }

}


