package com.linson.android.simplemusic.DAL;


import android.database.Cursor;

import com.linson.LSLibrary.AndroidHelper.LSComponentsHelper;
import com.linson.android.simplemusic.MODEL.MSongsList;
import com.linson.android.simplemusic.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class DSongsList
{
    private MUSICDB mMUSICDB;
    public DSongsList()
    {
        mMUSICDB=MUSICDB.getInstance(MainActivity.appContext);
    }
    public int add(MSongsList model)
    {
        int res=0;
        mMUSICDB.getWritableDatabase().execSQL("insert into SongsList(SL_name,SL_Songs) values ('"+model.SL_name+"','')");
        Cursor cursor=mMUSICDB.getReadableDatabase().rawQuery("select SL_ID from SongsList order by SL_ID desc limit 1",null);

        if(cursor.moveToFirst())
        {
            res=cursor.getInt(0);
        }
        return res;
    }

    public boolean delete(int sid)
    {
        if(sid==0)//skip allsongslist
        {
            return true;
        }
        else
        {
            mMUSICDB.getWritableDatabase().execSQL("delete from SongsList where SL_ID=" + sid + "");
            return true;
        }
    }

    public boolean modify(MSongsList model)
    {
        String sql="update SongsList set SL_name='"+model.SL_name.trim()+"',SL_Songs='"+model.SL_Songs.trim()+"'  where SL_ID="+model.SL_ID;
        try
        {
            mMUSICDB.getWritableDatabase().execSQL(sql);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public MSongsList getModel(int sid)
    {
        MSongsList res=null;
        try
        {
            Cursor cursor = mMUSICDB.getReadableDatabase().rawQuery("select SL_ID,SL_name,SL_Songs from SongsList where SL_ID=" + sid, null);
            if (cursor.moveToFirst())
            {
                res = new MSongsList();
                res.SL_ID = cursor.getInt(0);
                res.SL_name = cursor.getString(1);
                res.SL_Songs = cursor.getString(2);
            }
        } catch (Exception e)
        {
            LSComponentsHelper.LS_Log.Log_Exception(e);
        }
        return res;
    }

    public List<MSongsList> geModelList(String where)
    {
        List<MSongsList> lists = new ArrayList<>();
        try
        {
            Cursor cursor = mMUSICDB.getReadableDatabase().rawQuery("select SL_ID,SL_name,SL_Songs from SongsList where 1=1 " + where + " order by SL_ID", null);
            if (cursor.moveToFirst())
            {
                int size = cursor.getCount();
                for (int i = 0; i < size; i++)
                {
                    MSongsList templist = new MSongsList();
                    templist.SL_ID = cursor.getInt(0);
                    templist.SL_name = cursor.getString(1);
                    templist.SL_Songs = cursor.getString(2);

                    lists.add(templist);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e)
        {
            LSComponentsHelper.LS_Log.Log_Exception(e);
        }
        return lists;
    }

}