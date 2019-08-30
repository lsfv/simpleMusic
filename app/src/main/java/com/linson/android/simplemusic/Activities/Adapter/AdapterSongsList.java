package com.linson.android.simplemusic.Activities.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.linson.android.simplemusic.Activities.ListDetail;
import com.linson.android.simplemusic.BLL.BSongsList;
import com.linson.android.simplemusic.MODEL.MSongsList;
import com.linson.android.simplemusic.R;

import java.util.List;

public class AdapterSongsList extends RecyclerView.Adapter<AdapterSongsList.MyViewHolder>
{
    private List<MSongsList> mLists;
    private Activity mActivity;
    public AdapterSongsList(List<MSongsList> data,Activity activity)
    {
        mLists=data;
        mActivity=activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_songslist, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i)
    {
        final MSongsList mSongsList=mLists.get(i);
        myViewHolder.mTvListname.setText(mSongsList.SL_name+"");
        myViewHolder.mBtnDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(mSongsList.SL_ID>0)
                {
                    mLists.remove(mSongsList);
                    AdapterSongsList.this.notifyDataSetChanged();
                    BSongsList bSongsList = new BSongsList();
                    bSongsList.delete(mSongsList.SL_ID);
                }
            }
        });
        myViewHolder.mItem.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ListDetail.actionStart(mActivity, mSongsList);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mLists.size();
    }


    public void addEnd(MSongsList item)
    {
        mLists.add(item);
    }


    //viewholder
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView mTvListname;
        public Button mBtnDelete;
        public View mItem;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            mTvListname = (TextView) itemView.findViewById(R.id.tv_listname);
            mBtnDelete = (Button) itemView.findViewById(R.id.btn_delete);
            mItem=itemView;
        }
    }

}