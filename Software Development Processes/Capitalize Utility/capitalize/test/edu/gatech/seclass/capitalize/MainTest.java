package edu.gatech.seclass.capitalize;

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
    public void mainTest5() {
        String args[] = null; //invalid argument
        Main.main(args);
        assertEquals("Usage: Capitalize  [-l] [-e] [-s] [string] [-x] <filename>", errStream.toString().trim());
    }

}