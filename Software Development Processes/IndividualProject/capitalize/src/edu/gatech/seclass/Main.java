package edu.gatech.seclass;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {

private static String getFileContent(String filename) {
    Charset charset = StandardCharsets.UTF_8;
    String content = null;
    try {
        content = new String(Files.readAllBytes(Paths.get(filename)), charset);
    } catch (IOException e) {
        e.printStackTrace();
    }
    return content;
}

    public static void main(String[] args) throws IOException {

try{

        String file = getFileContent(args[args.length - 1]);
        FileWriter fileWriter = new FileWriter(args[args.length - 1]);


        Boolean bool = true;



            if(Arrays.asList(args).contains("-k")){
                bool = false;
                usage2();
                fileWriter.write((file));
                fileWriter.close();
            }



        if(bool){







            for (int i = 0; i < args.length; i++) {
                if(args[i] == "-x"){
                    file = x(file);

                    if (args.length == 2 || args[0] == "-x" && args[1] == "-x" && args[2] == "-x" || args[0] == "-x" && args[1] == "-x")
                        file = capitalize(file);
                }
            }


            if (args.length > 1) {
                for (int i = 0; i < args.length; i++) {

                    if (args[i] == "-l")
                        file = l(file);
                    if (args[i] == "-e")
                        file = e(file);

                    if (args[i] == "-s") {




                        if (args[i + 1].length()>1)
                        {
                            file = s(file, null);
                        }
                       if (args[i + 1] != "-x" || args[i + 1] != "-l" || args[i + 1] != "-e")

                        {

                            //this for loop should make it iterable over every element specified by
                            //-s, however im having issus being able to properly delegate
                            //via the if statemetn
                            //if(args[i+1].length()>1){
                            //    for(int j = 0; j < args[i+1].length();j++)
                            //    file = s(file, Character.toString(args[i+1].charAt(j)));




                            file = s(file, args[i + 1]);
                        }
                    }
                    if (args[i]=="-c"){
                        if (args[i + 1] != "-x" || args[i + 1] != "-l" || args[i + 1] != "-e")

                        {
                            file = c(file, Boolean.parseBoolean(args[i + 1]));
                        }
                        if (args[i+1]!= "true"||args[i+1]!="false")
                           usage2();


                    }


                }
                fileWriter.write(file);
                fileWriter.close();
            }


            if (args.length == 1) {

                fileWriter.write(capitalize(file));
                fileWriter.close();

            }


        } }catch (NullPointerException e) {
            usage2();
        }
        catch (StringIndexOutOfBoundsException r) {
            System.err.println("File Not Found");
    }}
    public static String l(String file) {

        String file2 = "";



        for (int i = 0; i < file.length(); i++) {
        if(i==0)

            file2+=Character.toUpperCase(file.charAt(i));
        if(i>0)

            if (file.charAt(i - 1) == '\n') {

                file2 += Character.toUpperCase(file.charAt(i));


            } else {
                file2 += file.charAt(i);
            }
            //file = file.charAt(0) + file2;


        }
        return (file2);
    }
    public static String capitalize(String file) {

        //gratzi alla http://www.penguincoders.net/2015/06/program-to-capitalize-first-letter-of-each-word-in-java.html

        file = file.replace(" \n","%\n");
        String cap = "";
        for (int i = 0; i < file.length(); i++) {
            char x = file.charAt(i);
            // char z = file.charAt(i);
            if (x == ' ') {
                cap = cap + " ";
                char y = file.charAt(i+1);
                cap = cap + Character.toUpperCase(y);
                i++;
            } else if (x == '\n') {
                cap = cap + "\n";
                char y = file.charAt(i+1);
                //if (!Character.isWhitespace(file.charAt(i)))
                //    y = file.charAt(i);
                cap = cap + Character.toUpperCase(y);
                i++;


            } else {
                cap = cap + x;
            }
        }
        cap = cap.replace("%\n"," \n");
        cap = cap.substring(0, 1).toUpperCase() + cap.substring(1);
        return cap;




    }
    public static String e(String file) {


        //String sentences0 = file;

        file = file.replace(".", ". ");

        //String[]sentences = file.split("\\.|\\?|\\!");


        //String[] sentences = sentences0.trim().split(("[^.!?\\s][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)"));
        String[] sentences = file.trim().split("(?<=\\.\\s)|(?<=[?!]\\s)");
        String[] sentences2 = new String[sentences.length];

        String phrase = "";

        for(int i = 0; i<sentences.length;i++) {

            if (sentences[i].endsWith("! ")||sentences[i].endsWith("!")||sentences[i].endsWith("!\n"))
                sentences2[i] = sentences[i].toUpperCase();
             else
                sentences2[i] = sentences[i];


            phrase += sentences2[i];
        }

        phrase = phrase.replace(". ",".");
        return phrase;





    }
    public static String x(String file) {

        String file2 = file.toLowerCase();

        return file2;

    }
    public static String s(String file,String delimeter) {

        //https://stackoverflow.com/questions/16078479/capitalize-first-word-of-a-sentence-in-a-string-with-multiple-sentences
        StringBuilder sb = new StringBuilder(file);
        if (delimeter == null) {

            int pos = 0;
            boolean capitalize = false;
            if(file.contains("?")||file.contains("!")||file.contains(".")){
            capitalize = true;}

            while (pos < sb.length()) {
                if (sb.charAt(pos) == '?' || sb.charAt(pos) == '.' || sb.charAt(pos) == '!') {
                    capitalize = true;
                } else if (capitalize && !Character.isWhitespace(sb.charAt(pos))) {
                    sb.setCharAt(pos, Character.toUpperCase(sb.charAt(pos)));
                    capitalize = false;
                }
                pos++;
            }
        }
        if (delimeter != null) {

            int pos = 0;
            boolean capitalize = true;

                while (pos < sb.length()) {

                    if (sb.charAt(pos) == delimeter.charAt(0)) {
                        capitalize = true;
                    } else if (capitalize && !Character.isWhitespace(sb.charAt(pos))) {
                        sb.setCharAt(pos, Character.toUpperCase(sb.charAt(pos)));
                        capitalize = false;
                    }
                    if (sb.charAt(pos) == delimeter.charAt(0)) {
                        capitalize = true;
                    } else if (capitalize && !Character.isWhitespace(sb.charAt(pos))) {
                        sb.setCharAt(pos, Character.toUpperCase(sb.charAt(pos)));
                        capitalize = false;
                    }


                    pos++;
                }

            //delimeter = delimeter.substring(1,delimeter.length());
        //    if(delimeter.length()>0) {
        //        s(sb.toString(), delimeter.substring(1,delimeter.length()));
        //    }
        }



        return sb.toString();
    }
    public static String c(String file,boolean bool){

        //String file2 = "";
        if(bool) {

            file = capitalize(file);
            if (!file.contains(" "))
                file = file.replace(file.charAt(0), Character.toLowerCase(file.charAt(0)));

            file = file.replaceAll(" ", "");

           // a little sketch this following method, needs generalization

            if (file.contains("!")) {
                file = file.replace("!", "! ");

                if(file.endsWith("! ")){
                    file =  file.substring(0,file.length()-1);
                }

            }

        }








        else if(!bool){

            for(int i = 1;i<file.length();i++)
            {
            if (Character.isUpperCase(file.charAt(i))){

                if(Character.isLowerCase(file.charAt(i-1))){

                    file = file.replace(file.charAt(i),Character.toLowerCase(file.charAt(i)));

                    file = file.substring(0,i) + " " +file.substring(i,file.length());


                }}


            }
            if(!file.contains(" ")){

                file = file.substring(0,1).toUpperCase() + file.substring(1,file.length());

            }
        }



        return file;


    }



    private static void usage() {
        System.err.println("Usage: Capitalize  [-l] [-e] [-s] [string] [-x] <filename>");
    }



    private static void usage2() {
        System.err.println("Usage: Capitalize  [-l] [-e] [-s] [string] [-x] [-c] [boolean] <filename>");
    }

    private static void illegalargument(String args){





    }




        }






