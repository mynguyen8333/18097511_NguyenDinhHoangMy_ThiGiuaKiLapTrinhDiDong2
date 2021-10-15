package com.example.a18097511_nguyendinhhoangmy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{
    private ArrayList<Song> mSong;
    private Onclick onclick;

    public CustomAdapter(ArrayList<Song> mSong, Onclick onclick) {
        this.mSong = mSong;
        this.onclick = onclick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }



    @Override
    public int getItemCount() {
        return mSong.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Song songclick;
        TextView tenbh,tencs,thoiluong;
        ImageView img_anh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tenbh = itemView.findViewById(R.id.tvsong);
            tencs = itemView.findViewById(R.id.tvname);
            thoiluong = itemView.findViewById(R.id.tvkt);
            img_anh = itemView.findViewById(R.id.img_casi);
            itemView.setOnClickListener(view->{
                onclick.clickitem(songclick,img_anh);
            });
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = mSong.get(position);
        holder.img_anh.setImageResource(song.getAnh());
        holder.tenbh.setText(song.getTenbh());
        holder.tencs.setText(song.getTencs());
        holder.thoiluong.setText(song.getThoiluong());
        holder.songclick = song;

    }
}
