package com.example.administrator.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    MyService.MyBinder binder = null;
    String gstring;
    TextView textview3;
    EditText edittext;
    Intent intent1;
    boolean flag = true;

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MyService.MyBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("service disconnected");

        }
    };

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            textview3.setText(Integer.toString(msg.what));
        }
    }
    private class ShowThread extends Thread{
        private Handler shandler;
        public ShowThread(Handler handler){
            shandler = handler;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    shandler.sendEmptyMessage(binder.getCount());
                    Thread.sleep(1000);
                } catch (Exception ex) {

                }
            }

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //MyView mv = new MyView(this);
        //setContentView(mv);


        setContentView(R.layout.activity_main);
        Button button1 = (Button)findViewById(R.id.button1);
        Button button2 = (Button)findViewById(R.id.button2);
        Button button3 = (Button)findViewById(R.id.button3);
        Button button4 = (Button)findViewById(R.id.button4);
        Button button5 = (Button)findViewById(R.id.button5);
        Button button6 = (Button)findViewById(R.id.button6);
        edittext = (EditText)findViewById(R.id.edittext1);
        textview3 = (TextView)findViewById(R.id.textview3);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        intent1 = new Intent(MainActivity.this,MyService.class);
        

    }
    protected void onStart(){
        super.onStart();
        startService(intent1);

    }

   public void onClick(View v) {
       //Toast.makeText(MainActivity.this, "button is pressed", Toast.LENGTH_SHORT).show();
       switch (v.getId()){
           case R.id.button1:{
               String edittext_string;
               Intent intent = new Intent(MainActivity.this, Main2Activity.class);
               Bundle bundle = new Bundle();
               edittext_string = edittext.getText().toString();
               bundle.putBoolean("boolean_key", true);
               bundle.putString("string_key", edittext_string);
               intent.putExtra("message", bundle);
               startActivity(intent);
           }break;
           case R.id.button2:{
               flag = true;

               bindService(intent1, connection,0);
           }break;
           case R.id.button3:{
               if(flag){
                   unbindService(connection);
                   stopService(intent1);
                   flag = false;
               }

           }break;
           case R.id.button4:{
               new Toast(this).makeText(this,"count is"+ binder.getCount(),Toast.LENGTH_SHORT).show();
           }break;
           case R.id.button5:{
               MyHandler myHandler = new MyHandler();
               ShowThread showThread = new ShowThread(myHandler);
               showThread.start();
           }break;
           case R.id.button6:{
               gstring = edittext.getText().toString();
               showNotification();
           }break;
           default:;
       }

   }

    private void showNotification() {
        Intent intent = new Intent(MainActivity.this,MyNotification.class);
        intent.putExtra("message1",gstring);
        PendingIntent pintent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new Notification.Builder(this)
                .setAutoCancel(true)
                .setContentTitle("new message")
                .setContentText("wkj's message")
                .setContentIntent(pintent)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .build();
        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        nm.notify(1,notification);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
