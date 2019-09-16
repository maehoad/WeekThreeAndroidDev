package com.example.androiddevweekthree;

//package com.example.sweetlogic.inputtextbox;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnKeyListener;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText textInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        addButtonListener();

    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView tvId = (TextView) findViewById(R.id.title_view);
        textInput = (EditText) findViewById(R.id.textInput);

        //Get the date and time
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        String currentDateandTime = sdf.format(new Date());

        //Set view/text
        tvId.setText( "Todo at "+ currentDateandTime.toString());
    }
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        // if the user press space button, display a toast message
        if ((keyCode == KeyEvent.KEYCODE_SPACE)) {

            // display a floating message
            Toast.makeText(MainActivity.this, "Cannot have space in input text!", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }





    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /** Writes textToSave to the file denoted by fileName. **/
    private void save(String fileName, String textToSave) {
        try {
            OutputStreamWriter out =
                    new OutputStreamWriter(openFileOutput(fileName, 0));
            out.write(textToSave);
            out.close();
            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
        } catch (Throwable t) {
            Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }

    /** Checks if the file denoted by fileName exists. **/
    private boolean fileExists(String fileName) {
        File file = getBaseContext().getFileStreamPath(fileName);
        return file.exists();
    }

    /** Opens the file denoted by fileName and returns the contents of the file. **/
    private String open(String fileName) {
        String content = "";
        if (fileExists(fileName)) {
            try {
                InputStream in = openFileInput(fileName);
                if ( in != null) {
                    InputStreamReader tmp = new InputStreamReader( in );
                    BufferedReader reader = new BufferedReader(tmp);
                    String str;
                    StringBuilder buf = new StringBuilder();
                    while ((str = reader.readLine()) != null) {
                        buf.append(str + "\n");
                    } in .close();
                    content = buf.toString();
                }
            } catch (java.io.FileNotFoundException e) {} catch (Throwable t) {
                Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
            }
        }
        return content;
    }
}