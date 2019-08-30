//package com.linson.LSLibrary.CustomUI;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.BitmapShader;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.Shader;
//import android.util.AttributeSet;
//import android.view.View;
//import com.linson.LSLibrary.AndroidHelper.LSComponentsHelper.LS_CustomViewHelper.Enum_MeasureType;
//import com.linson.LSLibrary.AndroidHelper.LSComponentsHelper.LS_CustomViewHelper.SuggestMeasure;
//import com.linson.android.hiandroid2.R;
//import static com.linson.LSLibrary.AndroidHelper.LSComponentsHelper.LS_CustomViewHelper.getCommonMeasure;
//import static com.linson.LSLibrary.AndroidHelper.LSComponentsHelper.LS_CustomViewHelper.getTypedArray;
//
//
////宽高参数决定控件大小，半径决定圆的大小，半径如果超过宽或者高,或者未设定，那么设置为宽或者高的中较小的,padding 无视。
////测试没有bug，正式无错.
//public class LSCircleImageFinalV2 extends View
//{
//    private int mImgResourceID=0;
//    private int mRadius=0;
//    private static int mDefaultSize=100;
//    private Paint mPaint;
//
//    public LSCircleImageFinalV2(Context context,AttributeSet attrs)
//    {
//        super(context, attrs);
//        int[] genStyleid= R.styleable.circleArrtibute;
//        int radiusID=R.styleable.circleArrtibute_radius;
//        int imgID=R.styleable.circleArrtibute_img;
//
//        TypedArray typedArray=getTypedArray(getContext(), attrs, genStyleid);
//        mRadius=typedArray.getInteger(radiusID, mRadius);
//        mImgResourceID=typedArray.getResourceId(imgID, mImgResourceID);
//        mPaint=new Paint();
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
//    {
//        //默认宽高是等值的，所以只设定一个，那么另一个相等。
//        SuggestMeasure suggestMeasure=getCommonMeasure(widthMeasureSpec, heightMeasureSpec, mDefaultSize, mDefaultSize,Enum_MeasureType.rate);
//        setMeasuredDimension(suggestMeasure.width, suggestMeasure.height);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas)
//    {
//        //先把图面重建为控件大小，按拉伸模式。
//        //找圆形，并确定半径。画圆。
//        if(mImgResourceID!=0)
//        {
//            int controlWidth=getWidth();
//            int controlHeight=getHeight();
//            Bitmap bitmap_ornigal=BitmapFactory.decodeResource(getResources(), mImgResourceID);
//            Bitmap bitmap_fullControl=Bitmap.createScaledBitmap(bitmap_ornigal, controlWidth    ,controlHeight ,true );
//            int centerX=controlWidth/2;
//            int centerY=controlHeight/2;
//
//            int radies=Math.min(centerX, centerY);
//            if(mRadius>0 && mRadius< radies)
//            {
//                radies=mRadius;
//            }
//            mPaint.setShader(new BitmapShader(bitmap_fullControl, Shader.TileMode.CLAMP , Shader.TileMode.CLAMP));
//            canvas.drawCircle(centerX, centerY, radies, mPaint);
//        }
//        else
//        {
//            super.onDraw(canvas);
//        }
//    }
//}