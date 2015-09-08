package com.example.administrator.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.IOException;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    MediaPlayer mPlayer;
    boolean flag = true;
    VideoView vPlayer;
    Intent intent1,intent2,intent3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("this is", "oncreat");
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        intent1 = new Intent(this,Main3Activity.class);
        intent2 = new Intent(this,Main4Activity.class);
        intent3 = new Intent(this,Main5Activity.class);
        TextView textview2 = (TextView)findViewById(R.id.textview2);
        Button button7 = (Button)findViewById(R.id.button7);
        Button button8 = (Button)findViewById(R.id.button8);
        Button button9 = (Button)findViewById(R.id.button9);
        Button button10 = (Button)findViewById(R.id.button10);
        Button button11 = (Button)findViewById(R.id.button11);
        Button button12 = (Button)findViewById(R.id.button12);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button10.setOnClickListener(this);
        button11.setOnClickListener(this);
        button12.setOnClickListener(this);
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource("/storage/sdcard1/pnty.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(intent != null) {
            Bundle bundle = intent.getBundleExtra("message");
            boolean flag = bundle.getBoolean("boolean_key");
            String value = bundle.getString("string_key");
            textview2.setText(value);
            Log.v("Main2", value + " " + flag);
        }

    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button7: {
                //MediaPlayer mPlayer = MediaPlayer.create(this,R.raw.pnty);
                //mPlayer.start();
                if(flag==true) {
                    try {

                        mPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mPlayer.seekTo(0);
                }
                mPlayer.start();

            }break;
            case R.id.button8:{
                mPlayer.stop();
                flag = true;
            }break;
            case R.id.button9:{
                mPlayer.pause();
                flag = false;
            }break;
            case R.id.button10:{
                startActivity(intent1);
            }break;
            case R.id.button11:{
                startActivity(intent2);
            }break;
            case R.id.button12:{
                startActivity(intent3);
            }break;
            default:;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
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
}
