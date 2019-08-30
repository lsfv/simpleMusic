package com.linson.android.simplemusic.BLL;

import com.linson.android.simplemusic.DAL.DSong;
import com.linson.android.simplemusic.MODEL.MSong;

import java.util.ArrayList;
import java.util.List;

public class BSong
{
    private DSong mDSong;

    public BSong()
    {
        mDSong=new DSong();
    }
    public int add(MSong item)
    {
        return mDSong.add(item);
    }

    public MSong getModel(int rid)
    {
        return mDSong.getModel(rid);
    }

    public List<MSong> updateList()
    {
        return mDSong.getList();
    }

    public List<MSong> getListFromDB()
    {
        return mDSong.getListFromDB("");
    }

    public List<MSong> getListBySelect(String selected)
    {
        if(selected.length()>0 && selected.substring(selected.length()-1, selected.length()).equals(","))
        {
            selected=selected.substring(0, selected.length()-1);
        }
        return mDSong.getListFromDB("and id in ("+selected+")");
    }

    public String[] getListName(List<MSong> res)
    {
        int size=res.size();
        String[] array=new String[size];
        for(int i=0;i<size;i++)
        {
            array[i]=res.get(i).musicName;
        }
        return array;
    }

    public List<Integer> getSongs(String strSONGS)
    {
        List<Integer> res=new ArrayList<>();
        String[] strarray= strSONGS.split(",");
        for(int i=0;i<strarray.length;i++)
        {
            try
            {
                Integer tempInt=Integer.parseInt(strarray[i]);
                res.add(tempInt);
            }
            catch (Exception e) { }
        }
        return res;
    }

    public String getSongsStr(List<MSong> songs,boolean[] selectedsong)
    {
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < selectedsong.length; i++)
        {
            if (i == selectedsong.length - 1 && selectedsong[i])
            {
                res.append(songs.get(i).rID);
            }
            else if(selectedsong[i] && i != selectedsong.length - 1)
            {
                res.append(songs.get(i).rID + ",");
            }
        }
        return res.toString();
    }

    public boolean[] getChoose(List<MSong> songs,List<Integer> selecteds)
    {
        boolean[] chooseArray=new boolean[songs.size()];

        for(int i=0;i<songs.size();i++)
        {
            Integer id= songs.get(i).rID;
            if(selecteds.contains(id))
            {
                chooseArray[i]=true;
            }
            else
            {
                chooseArray[i]=false;
            }
        }
        return chooseArray;
    }
}