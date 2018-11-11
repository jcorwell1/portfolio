package edu.gatech.seclass.sdpkey;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.jcorwell.sdpkey.R;

import java.lang.*;

public class SDPKeyActivity extends AppCompatActivity {

    public static EditText inputMessage;
    public static EditText keywordEntry;
    public EditText resultMessage;
    private RadioButton encodeOption;
    private RadioButton decodeOption;
    //private Button buttonCalculate;
    //public String message = inputMessage.toString();
    //public String keyword = keywordEntry.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdpkey);

        inputMessage = (EditText) findViewById(R.id.inputMessage);
        keywordEntry = (EditText) findViewById(R.id.keywordEntry);
        resultMessage = (EditText) findViewById(R.id.resultMessage);
        encodeOption = (RadioButton) findViewById(R.id.encodeOption);
        decodeOption = (RadioButton) findViewById(R.id.decodeOption);


    }

    /**
     * Called when the user taps the Send button
     */
    public void buttonCalculate(View view) {
        // Do something in response to button
        String message = inputMessage.getText().toString();
        String key = keywordEntry.getText().toString().toLowerCase();

        //define errors
        if (message.length() == 0) {inputMessage.setError("Message Required");}
        if (key.length() == 0) {keywordEntry.setError("Missing Keyword");}
        if (!key.matches("[a-zA-Z]+")) {keywordEntry.setError("Invalid Keyword");}
        //ENCODE IS CHECKED!!!!


        if (encodeOption.isChecked()) {
            if (message.length() > 0 && key.matches("[a-zA-Z]+")) {
                resultMessage.setText(function.encode(message, key));
            } else {
                Context context = getApplicationContext();
                CharSequence text = "Fix error";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
            ///DECODE IS CHECKED!!!
            if (decodeOption.isChecked()) {
                if (message.length() > 0 && key.matches("[a-zA-Z]+")) {
                    resultMessage.setText(function.decode(message,key));


                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Fix error";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }




    }
       // String message = inputMessage.toString();
       // String keyword = keywordEntry.toString();

     //   String encoded = function.encode(message,keyword);
     //   resultMessage.setText(encoded);
        //if (encodeOption.isChecked()){
        //    resultMessage.setText(encoded);
        }
   /* public static void main(String args[]) {
        String encoded = function.encode(,);

        System.out.println(encoded);
    }*/



