package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Main6Activity extends AppCompatActivity implements View.OnClickListener{
    Button read,write;
    EditText edread,edwrite;
    String FILE_NAME = "wkj.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        read = (Button)findViewById(R.id.read);
        write = (Button)findViewById(R.id.write);
        edread = (EditText)findViewById(R.id.edread);
        edwrite = (EditText)findViewById(R.id.edwrite);
        read.setOnClickListener(this);
        write.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main6, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.read:{
                edread.setText(myread());
            }break;
            case R.id.write:{
                mywrite(edwrite.getText().toString());
                edwrite.setText("");
            }break;
        }
    }

    private void mywrite(String s) {
        try{
            FileOutputStream fileOutputStream = openFileOutput(FILE_NAME, MODE_APPEND);
            PrintStream printStream = new PrintStream(fileOutputStream);
            printStream.println(s);
            printStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String myread() {
        int readnum;
        try{
            FileInputStream fileInputStream = openFileInput(FILE_NAME);
            byte[] buffer = new  byte[1024];
            StringBuilder stringBuilder = new StringBuilder();
            while ((readnum = fileInputStream.read(buffer))>0){
                stringBuilder.append(new String(buffer,0,readnum));
            }
            fileInputStream.close();
            return stringBuilder.toString();

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

}
