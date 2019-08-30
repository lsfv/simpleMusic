package com.linson.LSLibrary.AndroidHelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.Queue;
import java.util.Stack;


//1.页面进退记录。2，返回栈自定义管理。3，无值默认启动函数
public class LSBaseActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        BackStack.addActivity(this);
        LSComponentsHelper.Log_INFO(this.getClass().getSimpleName() +":push.size:"+BackStack.mData.size()+".task id:"+getTaskId()+".activity code:"+this.hashCode());
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy()
    {
        BackStack.deleteActivity(this);
        LSComponentsHelper.Log_INFO(this.getClass().getSimpleName() +":pop.size:"+BackStack.mData.size()+".task id:"+getTaskId()+".activity code:"+this.hashCode());
        super.onDestroy();
    }

    //
    public static abstract class BackStack
    {
        private static Stack<Activity> mData=new Stack<>();

        private static  void addActivity(Activity item)
        {
            mData.push(item);
        }

        private static  void deleteActivity(Activity item)
        {
            mData.pop();
        }

        public static  void ClearActivity()
        {
            while (true)
            {
                if(mData.size()==0)
                {
                    break;
                }
                Activity item=mData.pop();
                if(!item.isFinishing())
                {
                    item.finish();
                }
            }
        }
    }
}