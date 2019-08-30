//package com.linson.LSLibrary.CustomUI;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.BitmapShader;
//import android.graphics.Canvas;
//import android.graphics.ColorFilter;
//import android.graphics.Paint;
//import android.graphics.Path;
//import android.graphics.PixelFormat;
//import android.graphics.Rect;
//import android.graphics.Shader;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.ColorDrawable;
//import android.graphics.drawable.Drawable;
//import android.support.annotation.DrawableRes;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.util.AttributeSet;
//import android.view.View;
//
//import com.linson.LSLibrary.AndroidHelper.LSComponentsHelper;
//import com.linson.android.hiandroid2.R;
//
////view的外观表示，基本靠shader和path就可以完成。这里演示下圆形头像
////BitmapShader, ComposeShader, LinearGradient, RadialGradient,SweepGradient
////shader用BitmapShader.        用系统自带的画圆就可以.不必要用path，path可以画不规则形状.
//public class LSCircleImage extends View
//{
//    private Integer mRadius=100;
//    private int mGeometry=1;
//    private Paint mPaint;
//    private Bitmap mBitmap;
//
//    public LSCircleImage(Context context)
//    {
//        this(context,null);
//    }
//
//    public LSCircleImage(Context context, @Nullable AttributeSet attrs)
//    {
//        super(context, attrs);
//        TypedArray ta =context.obtainStyledAttributes(attrs, R.styleable.circleArrtibute);
//        mRadius=ta.getInteger(R.styleable.circleArrtibute_radius, 100);
//        mGeometry=ta.getInteger(R.styleable.circleArrtibute_geometry, 1);
//
//        Drawable drawable= ta.getDrawable(R.styleable.circleArrtibute_img);
//        BitmapDrawable bd = (BitmapDrawable) drawable;
//        if(bd==null)
//        {
//            mBitmap=BitmapFactory.decodeResource(getResources(), android.R.drawable.star_off);
//        }
//        else
//        {
//            mBitmap=bd.getBitmap();
//        }
//
//        mPaint=new Paint();
//        mPaint.setAntiAlias(true);//抗锯齿
//    }
//
//    public void setmDrawable(@DrawableRes int drawable)
//    {
//        mBitmap= BitmapFactory.decodeResource(getResources(), drawable);
//    }
//
//    public void setBitmap(@NonNull Bitmap bitmap)
//    {
//        mBitmap=bitmap;
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
//    {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(mRadius*2, mRadius*2);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas)
//    {
//        super.onDraw(canvas);
//        mBitmap=Bitmap.createScaledBitmap(mBitmap, mRadius*2, mRadius*2, false);
//        BitmapShader theShader=new BitmapShader(mBitmap, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
//        mPaint.setShader(theShader);
//        switch (mGeometry)
//        {
//            case 1:
//            {
//                drawCricle(canvas);
//                break;
//            }
//            case 2:
//            {
//                drawStart(canvas);
//                break;
//            }
//        }
//    }
//
//    private void drawCricle(Canvas canvas)
//    {
//        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
//    }
//
//    private void drawStart(Canvas canvas)
//    {
//        Path path=new Path();
//        path.moveTo(100,0);
//        path.rLineTo(-100, 200);
//        path.rLineTo(200,0);
//        path.rLineTo(-100,-200);
//        canvas.drawPath(path,mPaint );
//    }
//}