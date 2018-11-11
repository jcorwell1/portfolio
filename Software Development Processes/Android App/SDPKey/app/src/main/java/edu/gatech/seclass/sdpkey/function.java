package edu.gatech.seclass.sdpkey;

/**
 * Created by jcorwell on 9/14/17.
 */

public class function {


    public static String encode(String message, String key){
        //following lines of code will be adapted for android input

        String message2 = message.replaceAll("[^a-zA-Z ]", "").toLowerCase();
        key = new StringBuffer(key).reverse().toString().toLowerCase();
        String[] keyArr = key.split("");
        String control = "abcdefghijklmnopqrstuvwxyz ";

        String SecretAlphabet = alphabet(control, keyArr);
        StringBuilder m = shellMessage(message2, message, SecretAlphabet, control);
        StringBuilder SecretMessage = FilledShell(message, m, control);
        return SecretMessage.toString();

    }
    //decodes a message

    public static String decode(String message, String key)
    {   String message2 = message.replaceAll("[^a-zA-Z ]", "").toLowerCase();

        key = new StringBuffer(key).reverse().toString().toLowerCase();
        String[] keyArr = key.split("");
        String control = "abcdefghijklmnopqrstuvwxyz ";

        String SecretAlphabet = alphabet(control, keyArr);
        control = SecretAlphabet;
        SecretAlphabet = "abcdefghijklmnopqrstuvwxyz ";
        StringBuilder m = shellMessage(message2, message, SecretAlphabet, control);
        StringBuilder SecretMessage = FilledShell(message, m, control);

        return SecretMessage.toString();
    }


    //generates our secret alphabet

    public static String alphabet(String alphabet, String[] keyArr) {
        for (int i = 0; i < keyArr.length; i++) {
            alphabet = alphabet.replace(keyArr[i], "");
            alphabet = keyArr[i] + alphabet;
        }
        return alphabet;
    }

    //Switches alphabets and capitalizes

    public static StringBuilder shellMessage(String message2, String message, String Secretalphabet, String control) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < message2.length(); i++) {
            char ch = message2.charAt(i);
            int index = control.indexOf(ch);
            char secCh = Secretalphabet.charAt(index);
            if (Character.isUpperCase(message.charAt(i)))
                secCh = Character.toUpperCase(Secretalphabet.charAt(index));
            s = s.append(secCh);
        }
        return s;
    }

    //encorporates punctuation as well as numbers

    public static StringBuilder FilledShell(String message, StringBuilder shellMessage, String alphabet) {
        StringBuilder Filled = new StringBuilder(shellMessage);
        //System.out.println(Filled);
        for (int i = 0; i < message.length(); i++) {
            if (Character.isDigit(message.charAt(i))||//matches("[^[0-9]*$]") ||
                    Character.toString(message.charAt(i)).matches("[.!?\\-]"))
                Filled.insert(message.indexOf(message.charAt(i)), message.charAt(i));
        }
        return Filled;
    }
}
