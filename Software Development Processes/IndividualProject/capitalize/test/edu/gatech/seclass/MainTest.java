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

import static org.junit.Assert.*;

/*
DO NOT ALTER THIS CLASS.  Use it as an example for MyMainTest.java
 */

public class MainTest {

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

        fileWriter.write("Howdy Billy,\n" +
                "This is a test file for the capitalize utility.\n" +
                "let's make sure it has at least a few lines,\n" +
                "so that we can create some \n"
                + "interesting test cases...And let's say \"howdy\" to Bill again!");

        fileWriter.close();
        return file1;
    }

    private File createInputFile2() throws Exception {
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

    private File createInputFile3() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Howdy Bill, have you learned your abc and 123?\r\n" +
                "I know My Abc's.\r" +
                "It is important to know your abc's and 123's,\n" +
                "so repeat with me: abc! 123! Abc and 123!");

        fileWriter.close();
        return file1;
    }

    private File createInputFile4() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("AppleBananaCarrot.\r" +
                "DogElephantHorse.\r" +
                "Icecream. Jello");

        fileWriter.close();
        return file1;
    }

    private File createInputFile5() throws Exception {
        File file = createTmpFile();
        FileWriter fileWriter = new FileWriter(file);

        fileWriter.write("");

        fileWriter.close();
        return file;
    }

    private File createInputFile6() throws Exception {
        File file = createTmpFile();
        FileWriter fileWriter = new FileWriter(file);

        fileWriter.write("this is a very simple string" + System.lineSeparator() +
                "with a few lines" + System.lineSeparator() +
                "and not much else");

        fileWriter.close();
        return file;
    }

    private File createInputFile7() throws Exception {
        File file = createTmpFile();
        FileWriter fileWriter = new FileWriter(file);

        fileWriter.write("-- -- -- --");

        fileWriter.close();
        return file;
    }

    private File createInputFile8() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Mary had a csv,csv,csv\n" +
                "Mary had a csv,commas, in a csv.\n" +
                "csv,csv,csv");

        fileWriter.close();
        return file1;
    }

    private File createInputFile9() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("oneword");

        fileWriter.close();
        return file1;
    }


    private File createInputFile10() throws Exception {
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

    // Purpose: To provide an example of a test case format
    // Frame #: Instructor example 1 from assignment directions
    @Test
    public void mainTest1() throws Exception {

        File inputFile1 = createInputFile1();

        String args[] = {inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Howdy Billy,\n" +
                "This Is A Test File For The Capitalize Utility.\n" +
                "Let's Make Sure It Has At Least A Few Lines,\n" +
                "So That We Can Create Some \n"
                + "Interesting Test Cases...And Let's Say \"howdy\" To Bill Again!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: To provide an example of a test case format
    // Frame #: Instructor example 2 from assignment directions
    @Test
    public void mainTest2() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-l", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Howdy Billy,\n" +
                "This is a test file for the capitalize utility.\n" +
                "Let's make sure it has at least a few lines,\n" +
                "So that we can create some \n"
                + "Interesting test cases...And let's say \"howdy\" to Bill again!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: To provide an example of a test case format
    // Frame #: Instructor example 3 from assignment directions
    @Test
    public void mainTest3() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-e", "-s", inputFile1.getPath()};
        Main.main(args);

        String expected2 = "Howdy Billy,\n" +
                "This is a test file for the capitalize utility.\n" +
                "Let's make sure it has at least a few lines,\n" +
                "so that we can create some \n"
                + "interesting test cases...AND LET'S SAY \"HOWDY\" TO BILL AGAIN!";

        String actual2 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected2, actual2);
    }

    // Purpose: To provide an example of a test case format
    // Frame #: Instructor example 4 from assignment directions
    @Test
    public void mainTest4() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-s", ",", "-x", inputFile1.getPath()};
        Main.main(args);

        String expected3 = "Howdy billy,\n" +
                "This is a test file for the capitalize utility.\n" +
                "let's make sure it has at least a few lines,\n" +
                "So that we can create some \n"
                + "interesting test cases...and let's say \"howdy\" to bill again!";

        String actual3 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected3, actual3);
    }


    // Purpose: To provide an example of a test case format
    // Frame #: Instructor error example
    @Test
    public void mainTest5() throws Exception{
        String args[] = null; //invalid argument
        Main.main(args);

        assertEquals("Usage: Capitalize  [-l] [-e] [-s] [string] [-x] [-c] [boolean] <filename>", errStream.toString().trim());
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
        File inputFile = createInputFile2();

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
    @Test
    public void mainTest9() throws Exception {
        File inputFile = createInputFile2();

        String args[] = {"-s", ",.?!", "-e", "-x", "-c", "true", inputFile.getPath()};
        Main.main(args);

        String expected = "BillIs,\n" +
                "InMyOpinion,\n" +
                "AnEasierNameToSpellThanWilliam.\n" +
                "BillIsShorter,\n" +
                "AndBillIs\n" +
                "FirstAlphabetically.";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Additional Test for Deliverable 2
    @Test
    public void mainTest10() throws Exception {
        File inputFile = createInputFile4();

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
        File inputFile = createInputFile4();

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
        File inputFile = createInputFile3();

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
    @Test
    public void mainTest13() throws Exception {
        File inputFile = createInputFile3();

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
        File inputFile = createInputFile5();

        String args[] = {"-s", "-x", inputFile.getPath()};
        Main.main(args);

        String expected = "";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Additional Test for Deliverable 2
    @Test
    public void mainTest15() throws Exception {
        File inputFile = createInputFile10();

        String args[] = {"-s", inputFile.getPath()};
        Main.main(args);

        String expected = "Lol.Rofl.Roflmho";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Additional Test for Deliverable 2
    @Test
    public void mainTest16() throws Exception {
        File inputFile = createInputFile6();

        String args[] = {"-l", inputFile.getPath()};
        Main.main(args);

        String expected = "This is a very simple string" + System.lineSeparator() +
                "With a few lines" + System.lineSeparator()+
        "And not much else";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Additional Test for Deliverable 2
    @Test
    public void mainTest17() throws Exception {
        File inputFile = createInputFile6();

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
        File inputFile = createInputFile7();

        String args[] = {"-l", "-x", inputFile.getPath()};
        Main.main(args);

        String expected = "-- -- -- --";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Additional Test for Deliverable 2
    @Test
    public void mainTest19() throws Exception {
        File inputFile = createInputFile7();

        String args[] = {"-s", "-", inputFile.getPath()};
        Main.main(args);

        String expected = "-- -- -- --";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Additional Test for Deliverable 2
    @Test
    public void mainTest20() throws Exception {
        File inputFile = createInputFile8();

        String args[] = {"-s", ",.", inputFile.getPath()};
        Main.main(args);

        String expected = "Mary had a csv,Csv,Csv\n" +
                "Mary had a csv,Commas, In a csv.\n" +
                "Csv,Csv,Csv";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Additional Test for Deliverable 2
    @Test
    public void mainTest21() throws Exception {
        File inputFile = createInputFile8();

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
        File inputFile = createInputFile9();

        String args[] = {"-x", inputFile.getPath()};
        Main.main(args);

        String expected = "Oneword";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Additional Test for Deliverable 2
    @Test
    public void mainTest23() throws Exception {
        File inputFile = createInputFile9();

        String args[] = {"-c", "false", inputFile.getPath()};
        Main.main(args);

        String expected = "Oneword";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Additional Test for Deliverable 2
    @Test
    public void mainTest24() throws Exception {
        File inputFile = createInputFile10();

        String args[] = {"-x", "-x", "-x", inputFile.getPath()};
        Main.main(args);

        String expected = "Lol.rofl.roflmho";

        String actual = getFileContent(inputFile.getPath());

        assertEquals("The files differ!", expected, actual);
    }

    // Additional Test for Deliverable 2
    @Test
    public void mainTest25() throws Exception {
        File inputFile = createInputFile10();

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