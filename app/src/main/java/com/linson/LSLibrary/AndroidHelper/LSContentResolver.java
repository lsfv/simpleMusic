package com.linson.LSLibrary.AndroidHelper;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

public class LSContentResolver
{
    private Context mContext;

    public LSContentResolver(Context context)
    {
        mContext=context;
    }

    public List<SongInfo> SearchSong(int duration)
    {
        List<SongInfo> res=new ArrayList<>();
        ContentResolver contentResolver=mContext.getContentResolver();
        Uri uri= MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        StringBuffer searchsql=new StringBuffer();
        searchsql.append(" 1=1 ");
        searchsql.append(" and " +MediaStore.Audio.Media.DURATION+" > " +duration);
        searchsql.append(" and " +MediaStore.Audio.Media.DURATION+" < 1000*60*20");//应该要小于20分钟，要不太占内存。
        String[] paras={MediaStore.Audio.Media._ID,MediaStore.Audio.Media.TITLE,MediaStore.Audio.Media.ARTIST,MediaStore.Audio.Media.DATA,MediaStore.Audio.Media.DURATION};

        Cursor cursor = contentResolver.query(uri, paras, searchsql.toString(), null, "");

        if(cursor.moveToFirst())
        {
            int size=cursor.getCount();
            for(int i=0;i<size;i++)
            {
                int tempID=cursor.getInt(0);
                String tempTitle=cursor.getString(1);
                String tempArtist=cursor.getString(2);
                String tempPath=cursor.getString(3);
                int tempDuration=cursor.getInt(4);
                SongInfo tempSonginfo=new SongInfo(tempTitle, tempArtist, tempDuration,  tempPath, tempID);
                res.add(tempSonginfo);
                cursor.moveToNext();
            }
        }
        return res;
    }


    public static class SongInfo
    {
        public String musicName;
        public String artist;
        public int duration;
        public String path;
        public int songId;//android系统的id

        public SongInfo(String name,String player,int durationa,String songpath,int _id)
        {
            musicName=name;
            artist=player;
            duration=durationa;
            path=songpath;
            songId=_id;
        }
    }
}

//region music info
//    public static class MusicInfo implements Parcelable
//    {
//
//        //public final static String KEY_MUSIC = "music";
//        public static final String KEY_SONG_ID = "songid";
//        public static final String KEY_ALBUM_ID = "albumid";
//        public static final String KEY_DURATION = "duration";
//        public static final String KEY_MUSIC_NAME = "musicname";
//        public static final String KEY_ARTIST = "artist";
//        public static final String KEY_DATA = "data";
//        public static final String KEY_FOLDER = "folder";
//
//        public int songId = -1;
//        public int albumId = -1;
//        public int duration;
//        public String musicName;
//        public String artist;
//        public String data;
//        public String folder;
//
//        @Override
//        public int describeContents()
//        {
//            return 0;
//        }
//
//        @Override
//        public void writeToParcel(Parcel dest, int flags)
//        {
//            Bundle bundle = new Bundle();
//            bundle.putInt(KEY_SONG_ID, songId);
//            bundle.putInt(KEY_ALBUM_ID, albumId);
//            bundle.putInt(KEY_DURATION, duration);
//            bundle.putString(KEY_MUSIC_NAME, musicName);
//            bundle.putString(KEY_ARTIST, artist);
//            bundle.putString(KEY_DATA, data);
//            bundle.putString(KEY_FOLDER, folder);
//            dest.writeBundle(bundle);
//        }
//
//        public static final Parcelable.Creator<MusicInfo> CREATOR = new Parcelable.Creator<MusicInfo>()
//        {
//            @Override
//            public MusicInfo createFromParcel(Parcel source)
//            {
//                MusicInfo music = new MusicInfo();
//                Bundle bundle = new Bundle();
//                bundle = source.readBundle();
//                music.songId = bundle.getInt(KEY_SONG_ID);
//                music.albumId = bundle.getInt(KEY_ALBUM_ID);
//                music.duration = bundle.getInt(KEY_DURATION);
//                music.musicName = bundle.getString(KEY_MUSIC_NAME);
//                music.artist = bundle.getString(KEY_ARTIST);
//                music.data = bundle.getString(KEY_DATA);
//                music.folder = bundle.getString(KEY_FOLDER);
//                return music;
//            }
//
//            @Override
//            public MusicInfo[] newArray(int size)
//            {
//                return new MusicInfo[size];
//            }
//        };
//    }
//
//    // 音乐检索方法
//    public static ArrayList<MusicInfo> getMusicList(Cursor cursor) {
//        if (cursor == null) { return null; }
//        ArrayList<MusicInfo> musicList = new ArrayList<MusicInfo>();
//        while (cursor.moveToNext()) {
//            MusicInfo music = new MusicInfo();
//            music.songId = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
//            music.albumId = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
//            music.duration = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
//            music.musicName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
//            music.artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
//
//            String filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
//            music.data = filePath;
//            String folderPath = filePath.substring(0, filePath.lastIndexOf(File.separator));
//            music.folder = folderPath;
//            musicList.add(music);
//        }
//        cursor.close();
//        return musicList;
//    }
//endregion
