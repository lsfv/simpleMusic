package com.linson.android.simplemusic.Activities.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.linson.android.simplemusic.R;

import org.jetbrains.annotations.NotNull;


public class Dialog_inputList extends Dialog
{
    private Button mBtnSubmit;
    private TextView mTextView;
    private EditText mEtName;
    private Button mBtnCancel;
    private Idialogcallback mIdialogcallback;


    //region  findcontrols and bind click event.
    private void findControls()
    {   //findControls
        mBtnSubmit = (Button) findViewById(R.id.btn_submit);
        mTextView = (TextView) findViewById(R.id.textView);
        mEtName = (EditText) findViewById(R.id.et_name);
        mBtnCancel = (Button) findViewById(R.id.btn_cancel);

        mBtnSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String name=mEtName.getText().toString().trim();
                mIdialogcallback.submit(name);
                Dialog_inputList.this.dismiss();
            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Dialog_inputList.this.dismiss();
            }
        });
    }
    //endregion

    //region other member variable
    //endregion

    public Dialog_inputList(@NotNull Context context,Idialogcallback callback)
    {
        super(context);
        mIdialogcallback=callback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_inputlistname);
        findControls();
    }


    public  interface Idialogcallback
    {
        public void submit(String name);
    }

}