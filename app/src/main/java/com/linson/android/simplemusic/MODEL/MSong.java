package com.linson.android.simplemusic.MODEL;

import com.linson.LSLibrary.AndroidHelper.LSContentResolver;

public class MSong extends LSContentResolver.SongInfo
{
    public int rID;
    public int version;//日期+秒。得到歌曲，是否存在，存在，给个时间错，没有添加，也给时间错，完毕后，删除所有时间错不同的。就完成更新。妙.
    public MSong(String name, String player, int durationa, String songpath, int _id, int _rid)
    {
        super(name, player, durationa, songpath, _id);
        rID=_rid;
    }
}