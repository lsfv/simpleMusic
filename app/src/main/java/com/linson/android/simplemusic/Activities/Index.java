package com.linson.android.simplemusic.Activities;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.linson.LSLibrary.AndroidHelper.LSComponentsHelper;
import com.linson.android.simplemusic.Activities.Adapter.AdapterSongsList;
import com.linson.android.simplemusic.Activities.Dialog.Dialog_inputList;
import com.linson.android.simplemusic.BLL.BSong;
import com.linson.android.simplemusic.BLL.BSongsList;
import com.linson.android.simplemusic.MODEL.MSong;
import com.linson.android.simplemusic.MODEL.MSongsList;
import com.linson.android.simplemusic.R;

import java.util.List;


//数据更新后，回退，再前进时的数据 更新问题。暂时用start重加载解决。应该？？？比如如何模板化，流程化。
public class Index extends AppCompatActivity implements View.OnClickListener ,LSComponentsHelper.VoidHandler
{
    private Button mBtnAdd;
    private RecyclerView mIvList;
    private Button mBtnSearch;

    //region  findcontrols and bind click event.
    private void findControls()
    {   //findControls
        mBtnAdd = (Button) findViewById(R.id.btn_add);
        mIvList = (RecyclerView) findViewById(R.id.iv_list);
        mBtnSearch = (Button) findViewById(R.id.btn_search);

        //set event handler
        mBtnAdd.setOnClickListener(this);
        mBtnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_add:
            {
                addList();
                break;
            }
            case R.id.btn_search:
            {
                searchLocal();
            }
            default:
            {
                break;
            }
        }
    }
    //endregion

    //region other member variable
    private BSongsList mBSongsList;
    private AdapterSongsList mAdapterSongsList;
    private BSong mBSong;

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        initMember();
        findControls();
        setupRecycle();
    }

    private void initMember()
    {
        mBSongsList=new BSongsList();
        mBSong=new BSong();
    }

    private void setupRecycle()
    {
        //get adapter ,set adapter and layoutmanager
        List<MSongsList> mSongsLists=mBSongsList.geModelList("");
        mAdapterSongsList=new AdapterSongsList(mSongsLists,this);
        mIvList.setAdapter(mAdapterSongsList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mIvList.setLayoutManager(linearLayoutManager);
    }

    private void searchLocal()
    {
        LSComponentsHelper.LS_Other.checkPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE, this,1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LSComponentsHelper.LS_Other.progressCheck(this, requestCode, grantResults,this,1);
    }

    private void addList()
    {
        //alertdialog, set click . add2adapter,and db.
        Dialog_inputList.Idialogcallback idialogcallback=new Dialog_inputList.Idialogcallback()
        {
            @Override
            public void submit(String name)
            {
                if(name.isEmpty()==false)
                {
                    MSongsList mSongsList_temp=new MSongsList();
                    mSongsList_temp.SL_name=name;
                    mSongsList_temp.SL_Songs="";
                    int lid= mBSongsList.add(mSongsList_temp);
                    if(lid>0)
                    {
                        mSongsList_temp=mBSongsList.getModel(lid);
                        mAdapterSongsList.addEnd(mSongsList_temp);
                        mAdapterSongsList.notifyDataSetChanged();
                    }
                }
            }
        };
        final Dialog_inputList dialogInputList=new Dialog_inputList(this,idialogcallback);
        dialogInputList.show();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        setupRecycle();
    }

    @Override
    public void doit()
    {
        RealSearchLocal();
    }

    private void RealSearchLocal()
    {
        List<MSong> res = mBSong.updateList();
        LSComponentsHelper.LS_Log.Log_INFO(res.size()+"");
    }
}