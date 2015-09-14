package com.example.administrator.myapplication;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

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
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                File sdCardDir = new File(getSDCardPath());
                File targetFile = new File(sdCardDir, FILE_NAME);
                if(!targetFile.exists()){
                    targetFile.createNewFile();
                    targetFile.setWritable(true);
                }
                FileOutputStream randomAccessFile = new FileOutputStream(targetFile);
                randomAccessFile.write(s.getBytes());
                randomAccessFile.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String myread() {
        int readnum;
        try{
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                File sdCardDir = new File(getSDCardPath());
                File targetFile = new File(sdCardDir, FILE_NAME);
                FileInputStream fileInputStream = new FileInputStream(targetFile);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                StringBuilder stringBuilder = new StringBuilder("");
                String line = null;
                while ((line = bufferedReader.readLine())!=null){
                    stringBuilder.append(line);
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
    public static String getSDCardPath() {
        String cmd = "cat /proc/mounts";
        Runtime run = Runtime.getRuntime();// 返回与当前 Java 应用程序相关的运行时对象
        BufferedInputStream in=null;
        BufferedReader inBr=null;
        try {
            Process p = run.exec(cmd);// 启动另一个进程来执行命令
            in = new BufferedInputStream(p.getInputStream());
            inBr = new BufferedReader(new InputStreamReader(in));


            String lineStr;
            while ((lineStr = inBr.readLine()) != null) {
                // 获得命令执行后在控制台的输出信息
                Log.v("getSDCardPath", lineStr);
                if (lineStr.contains("sdcard")
                        && lineStr.contains(".android_secure")) {
                    String[] strArray = lineStr.split(" ");
                    if (strArray != null && strArray.length >= 5) {
                        String result = strArray[1].replace("/.android_secure",
                                "");
                        return result;
                    }
                }
                // 检查命令是否执行失败。
                if (p.waitFor() != 0 && p.exitValue() == 1) {
                    // p.exitValue()==0表示正常结束，1：非正常结束
                    Log.e("getSDCardPath", "命令执行失败!");
                }
            }
        } catch (Exception e) {
            Log.e("getSDCardPath", e.toString());
            //return Environment.getExternalStorageDirectory().getPath();
        }finally{
            try {
                if(in!=null){
                    in.close();
                }
            } catch (IOException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                if(inBr!=null){
                    inBr.close();
                }
            } catch (IOException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return Environment.getExternalStorageDirectory().getPath();
    }
    public static String getNormalSDCardPath(){
        return Environment.getExternalStorageDirectory().getPath();
    }

}
