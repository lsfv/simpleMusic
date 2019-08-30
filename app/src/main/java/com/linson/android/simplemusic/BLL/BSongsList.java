package com.linson.android.simplemusic.BLL;

import com.linson.android.simplemusic.DAL.DSongsList;
import com.linson.android.simplemusic.MODEL.MSongsList;

import java.util.List;

public class BSongsList
{
    private DSongsList mDSongsList;
    public BSongsList()
    {
        mDSongsList=new DSongsList();
    }

    public int add(MSongsList model)
    {
        return mDSongsList.add(model);
    }

    public boolean delete(int sid)
    {
        return mDSongsList.delete(sid);
    }

    public boolean modify(MSongsList model)
    {
        return mDSongsList.modify(model);
    }

    public MSongsList getModel(int sid)
    {
        return mDSongsList.getModel(sid);
    }

    public List<MSongsList> geModelList(String where)
    {
        List<MSongsList> res=mDSongsList.geModelList(where);

        MSongsList allList=new MSongsList();
        allList.SL_name="全部歌曲";
        allList.SL_Songs="*";
        allList.SL_ID=0;

        res.add(0, allList);
        return res;
    }
}