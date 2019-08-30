package com.linson.android.simplemusic.DAL;

import android.database.Cursor;

import com.linson.LSLibrary.AndroidHelper.LSComponentsHelper;
import com.linson.LSLibrary.AndroidHelper.LSContentResolver;
import com.linson.android.simplemusic.MODEL.MSong;
import com.linson.android.simplemusic.MainActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DSong
{
    private MUSICDB mMUSICDB;

    public DSong()
    {
        mMUSICDB=MUSICDB.getInstance(MainActivity.appContext);
    }

    public int add(MSong item)
    {
        int rid=0;
        return rid;
    }

    public MSong getModel(int rid)
    {
        MSong mSong=null;
        return mSong;
    }

    public List<MSong> getListFromDB(String where)
    {
        List<MSong> res=new ArrayList<>();
        String tempsql="";
        tempsql="select id,version,musicName,artist,duration,path,songId from song where 1=1 "+where;
        LSComponentsHelper.LS_Log.Log_INFO(tempsql);
        Cursor cursor_songs=mMUSICDB.getReadableDatabase().rawQuery(tempsql, null);
        if(cursor_songs.moveToFirst())
        {
            int size=cursor_songs.getCount();
            for(int i=0;i<size;i++)
            {
                int id=cursor_songs.getInt(0);
                String name=cursor_songs.getString(2);
                String player=cursor_songs.getString(3);
                int duration=cursor_songs.getInt(4);
                String path=cursor_songs.getString(5);
                int sid=cursor_songs.getInt(6);
                MSong mSong=new MSong(name, player, duration, path, sid, id);
                res.add(mSong);
                cursor_songs.moveToNext();
            }
        }
        return res;
    }

    public List<MSong> getList()
    {
        //得到本地数据，清空临时表。插入本地数据到临时表。把临时表和歌单列表合并(找出共同的歌,删除不存在的歌曲,临时表只保留未添加的。把临时表的数据添加上去)。
        //建立临时表的目的，可以用语句in()方便找到依然存在的歌曲。否则，拼凑string会很长
        List<MSong> res=new ArrayList<>();
        List<LSContentResolver.SongInfo> localSongs= getLocalList();
        addlocalSongs(localSongs);

        Date date = new Date();
        int version=(int)date.getTime();

        String tempsql="update song set version="+version+" where  path in (select path from localmusic)";
        mMUSICDB.getWritableDatabase().execSQL(tempsql);

        tempsql="delete from song where version !="+version;
        mMUSICDB.getWritableDatabase().execSQL(tempsql);

        tempsql="delete from localmusic where path in (select  path from song)";
        mMUSICDB.getWritableDatabase().execSQL(tempsql);

        tempsql="insert into song (version,musicName,artist,duration,path,songId) select "+version+",musicName,artist,duration,path,songId from localmusic";
        mMUSICDB.getWritableDatabase().execSQL(tempsql);

        tempsql="delete from localmusic";
        mMUSICDB.getWritableDatabase().execSQL(tempsql);

        tempsql="select id,version,musicName,artist,duration,path,songId from song";
        Cursor cursor_songs=mMUSICDB.getReadableDatabase().rawQuery(tempsql, null);
        if(cursor_songs.moveToFirst())
        {
            int size=cursor_songs.getCount();
            for(int i=0;i<size;i++)
            {
                int id=cursor_songs.getInt(0);
                String name=cursor_songs.getString(2);
                String player=cursor_songs.getString(3);
                int duration=cursor_songs.getInt(4);
                String path=cursor_songs.getString(5);
                int sid=cursor_songs.getInt(6);
                MSong mSong=new MSong(name, player, duration, path, sid, id);
                res.add(mSong);
                cursor_songs.moveToNext();
            }
        }

        return res;
    }

    private List<LSContentResolver.SongInfo> getLocalList()
    {
        try
        {
            LSContentResolver lsContentResolver = new LSContentResolver(MainActivity.appContext);
            List<LSContentResolver.SongInfo> res = lsContentResolver.SearchSong(60);
            LSComponentsHelper.LS_Log.Log_INFO("songs:" + res.size());
            return res;
        } catch (Exception e)
        {
            LSComponentsHelper.LS_Log.Log_Exception(e);
        }
        return null;
    }

    //create table localmusic ('id' integer primary key autoincrement  , 'musicName' text,'artist' text,'duration' integer,'path' text, 'songId' integer );");
    private void addlocalSongs(List<LSContentResolver.SongInfo> songs)
    {
        String tempsql="";
        tempsql="delete from localmusic";
        mMUSICDB.getWritableDatabase().execSQL(tempsql);
        for(LSContentResolver.SongInfo songInfo :songs)
        {
            tempsql="insert into localmusic('musicName','artist','duration','path','songId') values('"+songInfo.musicName+"','"+songInfo.artist+"',"+songInfo.duration+",'"+songInfo.path+"',"+songInfo.songId+")";
            mMUSICDB.getWritableDatabase().execSQL(tempsql);
        }
    }
}