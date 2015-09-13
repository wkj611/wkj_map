package com.example.administrator.myapplication;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main5Activity extends AppCompatActivity implements View.OnClickListener{
    SharedPreferences preference;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        Button bt1 = (Button)findViewById(R.id.bt1);
        Button bt2 = (Button)findViewById(R.id.bt2);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        preference = getSharedPreferences("wkj", MODE_PRIVATE);
        editor = preference.edit();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main5, menu);
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
            case R.id.bt1:{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy 年 MM 月 dd 日"+"hh:mm:ss");
                editor.putString("time",sdf.format(new Date()));
                editor.putInt("random", (int) (Math.random() * 100));
                editor.commit();
            }break;
            case R.id.bt2:{
                String time = preference.getString("time",null);
                int randNum = preference.getInt("random", 0);
                String result = time == null?"no data":"write time"+time
                        +"\n rand num is"+randNum;
                Toast.makeText(Main5Activity.this,result,Toast.LENGTH_SHORT).show();

            }break;
            default:;
        }

    }
}
