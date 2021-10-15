package com.example.a18097511_nguyendinhhoangmy;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyServiceClass extends Service {
    MediaPlayer mp;
    private int i=-1;
    private final MyBinder myBinder =new MyBinder();
    public class MyBinder extends Binder {
        public MyServiceClass getServiceMyClass(){
            return MyServiceClass.this;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("Service","unbinder");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Service","create");
    }

    public void start(){

        if(mp!=null)
            mp.start();
    }
    public void pause(){
        mp.pause();
        i = mp.getCurrentPosition();

    }
    public boolean isPlaying(){
        return  mp.isPlaying();
    }

    public void setMusic(int x){

        mp= MediaPlayer.create(getApplicationContext(), x);

        Log.e("Service","setMusic");

    }
    public void play(){
        if(i==-1){
            if(mp!=null){
                mp.start();
            }
        }else{
            mp.seekTo(i);
            mp.start();
        }
        Log.e("Service","start");
    }

    public void stop(){

        mp.stop();
    }


    @Override
    public void onDestroy() {
        Log.e("Service","huy");
        if(mp!=null){
            mp.release();
            mp = null;
        }
    }
    public MediaPlayer getMp(){
        return mp;
    }
}
