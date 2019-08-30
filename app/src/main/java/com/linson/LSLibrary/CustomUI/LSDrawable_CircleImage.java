//package com.linson.LSLibrary.CustomUI;
//
//import android.content.res.Resources;
//import android.content.res.TypedArray;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.BitmapShader;
//import android.graphics.Canvas;
//import android.graphics.ColorFilter;
//import android.graphics.Paint;
//import android.graphics.PixelFormat;
//import android.graphics.Shader;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.support.annotation.DrawableRes;
//import android.view.View;
//
//import com.linson.android.hiandroid2.R;
//
//public class LSDrawable_CircleImage extends Drawable
//{
//    private Integer mRadius=100;
//    private Paint mPaint;
//
//    public LSDrawable_CircleImage(Resources resources, @DrawableRes int sid)
//    {
//        Bitmap bitmap= BitmapFactory.decodeResource(resources, sid);
//        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
//        bitmap=Bitmap.createScaledBitmap(bitmap, mRadius*2, mRadius*2, false);
//        BitmapShader bitmapShader=new BitmapShader(bitmap, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
//        mPaint.setShader(bitmapShader);
//    }
//
//
//    public LSDrawable_CircleImage(Bitmap bitmap)
//    {
//        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
//        bitmap=Bitmap.createScaledBitmap(bitmap, mRadius*2, mRadius*2, false);
//        BitmapShader bitmapShader=new BitmapShader(bitmap, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
//        mPaint.setShader(bitmapShader);
//    }
//
//    @Override
//    public void draw(@android.support.annotation.NonNull Canvas canvas)
//    {
//        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
//    }
//
//    @Override
//    public void setAlpha(int alpha)
//    {
//        mPaint.setAlpha(alpha);
//        invalidateSelf();
//    }
//
//    @Override
//    public void setColorFilter(@android.support.annotation.Nullable ColorFilter colorFilter)
//    {
//        mPaint.setColorFilter(colorFilter);
//        invalidateSelf();
//    }
//
//    @Override
//    public int getOpacity()
//    {
//        return PixelFormat.TRANSLUCENT;
//    }
//
//    @Override
//    public int getIntrinsicHeight()
//    {
//        return mRadius*2;
//    }
//
//    @Override
//    public int getIntrinsicWidth()
//    {
//        return mRadius*2;
//    }
//}