package com.linson.android.simplemusic.Activities.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linson.android.simplemusic.MODEL.MSong;
import com.linson.android.simplemusic.R;

import java.util.List;

public class AdapterSongs extends RecyclerView.Adapter<AdapterSongs.MyviewHolder>
{
    private List<MSong> mMSongs;

    public AdapterSongs(List<MSong> songs)
    {
        mMSongs=songs;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_song, viewGroup, false);

        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder myviewHolder, int i)
    {
        MSong song=mMSongs.get(i);
        String label=song.musicName+"-"+song.artist;
        int max=label.length()>30?30:label.length();
        myviewHolder.mTextView.setText(label.substring(0,max));
    }

    @Override
    public int getItemCount()
    {
        return mMSongs.size();
    }

    public void updateData (List<MSong> Songs)
    {
        mMSongs=Songs;
        this.notifyDataSetChanged();
    }


    public static class MyviewHolder  extends RecyclerView.ViewHolder
    {
        private TextView mTextView;
        public MyviewHolder(@NonNull View itemView)
        {
            super(itemView);
            mTextView=itemView.findViewById(R.id.tv_songname);
        }
    }
}
