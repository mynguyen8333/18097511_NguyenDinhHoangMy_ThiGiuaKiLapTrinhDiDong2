package com.example.a18097511_nguyendinhhoangmy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity {
    ImageButton btnplay;

    int count = 0;
    int totalTime = 0;
    TextView txtbd,txtkt;
    ProgressBar progressBar;

    boolean i =false;
    private  MyServiceClass mMyServiceClass;
    private boolean isServiceConnected=false;
    int x;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyServiceClass.MyBinder myBinder = (MyServiceClass.MyBinder) service;
            mMyServiceClass = myBinder.getServiceMyClass();
            isServiceConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isServiceConnected = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        start();
        setContentView(R.layout.activity_main4);
        btnplay = findViewById(R.id.btn_playms);
        progressBar = findViewById(R.id.progressBar2);
        txtbd = findViewById(R.id.txtbd);

        x = R.raw.shapeofyou;
        totalTime = MediaPlayer.create(getBaseContext(),x).getDuration();

        btnplay.setOnClickListener(view->{
            if(i){
                if(isServiceConnected){
                    mMyServiceClass.pause();
                    txtbd.post(mUpdateTime);
                    i=false;
                }

            }
            else {
                if(isServiceConnected){
                    mMyServiceClass.setMusic(x);
                    mMyServiceClass.play();
                    if(mMyServiceClass.getMp().isPlaying()){

                        progressBar.post(mUpdateProgress);
                        txtbd.post(mUpdateTime);
                    }
                    i=true;
                }
            }
        });
    }

    private Runnable mUpdateProgress = new Runnable() {
        public void run() {
            int currentDuration;
            if(isServiceConnected){
                if (mMyServiceClass.getMp().isPlaying()) {
                    currentDuration = mMyServiceClass.getMp().getCurrentPosition();
                    double time = currentDuration * 1.0 / totalTime * 100;
                    progressBar.setProgress((int) time);
                    progressBar.postDelayed(this, 1000);
                }else {
                    progressBar.removeCallbacks(this);
                }
            }
        }
    };

    private Runnable mUpdateTime = new Runnable() {
        public void run() {
            int currentDuration;
            if(isServiceConnected){
                if (mMyServiceClass.getMp().isPlaying()) {
                    currentDuration = mMyServiceClass.getMp().getCurrentPosition();
                    updatePlayer(currentDuration);
                    txtbd.postDelayed(this, 1000);
                }else {
                    txtbd.removeCallbacks(this);
                }
            }

        }
    };

    private void updatePlayer(int currentDuration){
        txtbd.setText("" + milliSecondsToTimer((long) currentDuration));
    }

    public  String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        // Add hours if there
        if (hours > 0) finalTimerString += (hours > 9 ? hours : "0" + hours) + ":";
        finalTimerString += (minutes > 9 ? minutes : "0" + minutes) + ":";
        finalTimerString += (seconds > 9 ? seconds : "0" + seconds);

        // return timer string
        return finalTimerString;
    }

    public void start(){
        Intent intent = new Intent(this, MyServiceClass.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }
    public void stop(){
        if(isServiceConnected){
            unbindService(mServiceConnection);
            isServiceConnected =false;
        }

    }
}