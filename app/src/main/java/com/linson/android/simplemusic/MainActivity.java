package com.linson.android.simplemusic;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.linson.LSLibrary.AndroidHelper.LSComponentsHelper;
import com.linson.android.simplemusic.Activities.Index;

public class MainActivity extends AppCompatActivity
{
    public static Context appContext;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        appContext=getApplicationContext();

        LSComponentsHelper.LS_Log.Log_INFO("address:"+com.amitshekhar.DebugDB.getAddressLog());
        LSComponentsHelper.startActivity(this, Index.class);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        this.finish();
    }
}