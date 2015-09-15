package com.example.administrator.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    MediaPlayer mPlayer;
    boolean flag = true;
    VideoView vPlayer;
    Intent intent1,intent2,intent3,intent4,intent5;
    private Uri fileUri;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("this is", "oncreat");
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        intent1 = new Intent(this,Main3Activity.class);
        intent2 = new Intent(this,Main4Activity.class);
        intent3 = new Intent(this,Main5Activity.class);
        intent4 = new Intent(this,Main6Activity.class);
        intent5 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        TextView textview2 = (TextView)findViewById(R.id.textview2);
        Button button7 = (Button)findViewById(R.id.button7);
        Button button8 = (Button)findViewById(R.id.button8);
        Button button9 = (Button)findViewById(R.id.button9);
        Button button10 = (Button)findViewById(R.id.button10);
        Button button11 = (Button)findViewById(R.id.button11);
        Button button12 = (Button)findViewById(R.id.button12);
        Button file = (Button)findViewById(R.id.file);
        Button camera = (Button)findViewById(R.id.camera);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button10.setOnClickListener(this);
        button11.setOnClickListener(this);
        button12.setOnClickListener(this);
        file.setOnClickListener(this);
        camera.setOnClickListener(this);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Image captured and saved to fileUri specified in the Intent
                Toast.makeText(this, "Image saved to:\n" +
                        data.getData(), Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
            } else {
                // Image capture failed, advise user
            }
        }

        if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Video captured and saved to fileUri specified in the Intent
                Toast.makeText(this, "Video saved to:\n" +
                        data.getData(), Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the video capture
            } else {
                // Video capture failed, advise user
            }
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
            case R.id.file:{
                startActivity(intent4);
            }break;
            case R.id.camera:{
                fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

                intent5.putExtra(MediaStore.EXTRA_OUTPUT,fileUri);
                startActivityForResult(intent5,CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            }break;
            default:;
        }
    }


    /** Create a file Uri for saving an image or video */
    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
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
