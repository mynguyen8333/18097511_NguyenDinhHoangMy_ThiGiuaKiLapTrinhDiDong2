package com.example.a18097511_nguyendinhhoangmy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity implements Onclick{
    RecyclerView rsv_song;
    CustomAdapter adt;
    ArrayList<Song> mSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mSong = new ArrayList<>();
        rsv_song = findViewById(R.id.rsv_song);

        mSong.add(new Song("Shape of You","Ed Sheeran","4:23",R.drawable.ed));

        adt = new CustomAdapter(mSong,this);
        rsv_song.setHasFixedSize(true);
        rsv_song.setAdapter(adt);
        rsv_song.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));


    }

    @Override
    public void clickitem(Song s, ImageView img) {
        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);
        Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
        intent.putExtra("song",s);
        ActivityOptionsCompat options =  ActivityOptionsCompat.makeSceneTransitionAnimation(
                MainActivity3.this, img,
                ViewCompat.getTransitionName(img));
        startActivity(intent,options.toBundle());

    }
}