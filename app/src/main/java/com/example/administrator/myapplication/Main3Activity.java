package com.example.administrator.myapplication;

import android.graphics.PixelFormat;
import android.widget.MediaController;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.VideoView;

import java.io.File;

public class Main3Activity extends AppCompatActivity {
    MediaController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        VideoView videoView = (VideoView)findViewById(R.id.videoview1);
        mController = new MediaController(this);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        File video = new File("/storage/sdcard1/xfplay/HEY-043.640x480.mp4");
        if(video.exists()){
            System.out.println("video exists");
            videoView.setVideoPath(video.getAbsolutePath());
            videoView.setMediaController(mController);
            mController.setMediaPlayer(videoView);
            videoView.requestFocus();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main3, menu);
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
