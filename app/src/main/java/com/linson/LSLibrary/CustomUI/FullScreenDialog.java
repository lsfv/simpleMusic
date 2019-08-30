//package com.linson.LSLibrary.CustomUI;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.constraint.ConstraintLayout;
//import android.support.v7.app.AppCompatDialog;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.FrameLayout;
//
//
//public abstract class FullScreenDialog extends AppCompatDialog
//{
//    private static int mWindowStyle= R.style.Dialog_FullScreen;
//    private FrameLayout mContentView;
//    private Context mContext;
//
//    public FullScreenDialog(Context context)
//    {
//        super(context, mWindowStyle);
//        mContext=context;
//        SetupFullWindow();
//    }
//
//    @Override
//    public void setContentView(int layoutResID)
//    {
//        super.setContentView(R.layout.dialog_fullwhite);
//        mContentView=findViewById(R.id.content);
//        View view=View.inflate(mContext, layoutResID,null);
//        mContentView.addView(view);
//    }
//
//    private void SetupFullWindow()
//    {
//        Window window= getWindow();
//        window.setGravity(Gravity.CENTER);
//        window.getDecorView().setPadding(0, 0, 0, 0);
//
//        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height =-1;
//        window.setAttributes(lp);
//    }
//}