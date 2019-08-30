package com.linson.android.simplemusic.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.linson.android.simplemusic.Activities.Adapter.AdapterSongs;
import com.linson.android.simplemusic.BLL.BSong;
import com.linson.android.simplemusic.BLL.BSongsList;
import com.linson.android.simplemusic.MODEL.MSong;
import com.linson.android.simplemusic.MODEL.MSongsList;
import com.linson.android.simplemusic.R;

import java.util.List;

public class ListDetail extends AppCompatActivity implements View.OnClickListener
{
    private ConstraintLayout mConstraintLayout;
    private TextView mListName;
    private RecyclerView mRvSongs;
    private ConstraintLayout mConstraintLayout2;
    private Button mBtnEdit;


    //region  findcontrols and bind click event.
    private void findControls()
    {   //findControls
        mConstraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);
        mListName = (TextView) findViewById(R.id.listName);
        mRvSongs = (RecyclerView) findViewById(R.id.rv_songs);
        mConstraintLayout2 = (ConstraintLayout) findViewById(R.id.constraintLayout2);
        mBtnEdit = (Button) findViewById(R.id.btn_edit);

        //set event handler
        mBtnEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_edit:
            {
                editlist();
                break;
            }
            default:
            {
                break;
            }
        }
    }
    //endregion

    //region other member variable
    private BSong mBSong;
    private BSongsList mBSongsList;
    private MSongsList mMSongsList;
    //endregion

    public static void actionStart(Context context, MSongsList songsList)
    {
        Intent intent=new Intent(context, ListDetail.class);
        intent.putExtra("songsList", songsList);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail);

        initMember();
        findControls();
        setupTitle();
        setupList();
    }

    private void setupList()
    {
        List<MSong> res= mBSong.getListBySelect(mMSongsList.SL_Songs);
        AdapterSongs adapterSongs=new AdapterSongs(res);
        mRvSongs.setAdapter(adapterSongs);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mRvSongs.setLayoutManager(linearLayoutManager);
    }

    private void initMember()
    {
        mBSong=new BSong();
        mBSongsList =new BSongsList();

        mMSongsList= (MSongsList) getIntent().getSerializableExtra("songsList");
    }

    private void setupTitle()
    {
        mListName.setText(mMSongsList.SL_name);
    }


    private void editlist()
    {
        //得到所有歌曲，分解出所有歌名，根据songs字段分解出数组，并得到是否选择，配置multichoice,配置事件，更新songs.字段.
        final List<MSong> allSongs=mBSong.getListFromDB();
        String[] allsongsName=mBSong.getListName(allSongs);
        List<Integer> selectSongs=mBSong.getSongs(mMSongsList.SL_Songs);
        final boolean[] chooseArray=mBSong.getChoose(allSongs,selectSongs);

        final AlertDialog.Builder alertDialog_editlist=new AlertDialog.Builder(this);
        alertDialog_editlist.setTitle("编辑列表");
        alertDialog_editlist.setMultiChoiceItems(allsongsName, chooseArray, new DialogInterface.OnMultiChoiceClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked)
            {
                chooseArray[which]=isChecked;
            }
        });
        alertDialog_editlist.setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                mMSongsList.SL_Songs=mBSong.getSongsStr(allSongs, chooseArray);
                mBSongsList.modify(mMSongsList);

                List<MSong> newarray=mBSong.getListBySelect(mMSongsList.SL_Songs);
                ((AdapterSongs)mRvSongs.getAdapter()).updateData(newarray);
            }
        });
        alertDialog_editlist.setNeutralButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
            }
        });


        alertDialog_editlist.create().show();
    }
}