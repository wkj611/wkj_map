package com.example.administrator.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private int count=0;
    private MyBinder binder = new MyBinder();
    private boolean quit;
    public class MyBinder extends Binder{
        int getCount(){
            return count;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("service startcommand");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("service oncreat");
        new Thread(){
            @Override
            public void run() {
                while(!quit){
                    try{
                        Thread.sleep(1000);
                    }catch(Exception e){

                    }
                    //Log.v("count",Integer.toString(count));
                    synchronized (this){
                        count++;
                    }

                }
            }
        }.start();
    }
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.quit = true;
        synchronized (this){
            count = 0;
        }

        System.out.println("service destroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        System.out.println("onbinder");
        return binder;

    }
}
