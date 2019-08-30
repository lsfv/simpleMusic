//package com.linson.LSLibrary.CustomUI;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.BitmapShader;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Rect;
//import android.graphics.Shader;
//import android.graphics.drawable.Drawable;
//import android.util.AttributeSet;
//import android.view.View;
//
//import com.linson.LSLibrary.AndroidHelper.LSComponentsHelper;
//import com.linson.android.hiandroid2.R;
//
//import static com.linson.LSLibrary.AndroidHelper.LSComponentsHelper.*;
//import static com.linson.LSLibrary.AndroidHelper.LSComponentsHelper.LS_CustomViewHelper.*;
//
////1.定义属性文件：res/values 下定义一个xml属性文件。节点依次为：resources，declare-styleable，attr，给它们取name就好。
////2.定义自定义控件java文件.extend view.
////3.在xml布局文件放置控件，xml的根节点定义命名控件：xmlns:app="http://schemas.android.com/apk/res-auto"，定义控件属性值app:attr1="xxx",
////4.构造函数xxx(Context context, AttributeSet attrs),获取自定义属性值:context.obtainStyledAttributes.
////5.重载函数onMeasure,获取控件的宽高和各自的模式。测试发现会被执行3或者6次.如果是EXACTLY，会执行3次。所以一般在onlayout获得确切的大小。
//// 并且还会执行一次onSizeChanged，宽高由0，变成Measure的值，文档描述是view加入控件树的时候所触发。
////5.1 EXACTLY：一般表示你给控件给定一个accurate number.或者约束模式下2边都约束，等一切可以表示限制大小expression.
////5.2 AT_MOST: 一般设置为wrap_content的时候，
////5.3 UNSPECIFIED:系统使用，表示正在计算大小的状态，此刻先给控件充分的宽高。
////6.总结，任何时刻当前view的预估大小和模式是确定的，结合当前view和他的子view信息，计算出所有子view的预估大小和模式。一般会多次，所以一般在onlayout获得确切的大小。
//// 6.1预估大小而已，确定就确定， 满父就满父，wrap_content，就看是否给出了特定值，没有就默认满父。如textview,wrap_content的时候，是有自己的大小的，而不是满父。
//// 6.2子控件测绘的模式,是结合父控件的模式和自己的参数，也是按照人的思维习惯，子具体值，那么EXACTLY.  子parent，那么就随父的模式，子wrap_content，那么AT_MOST.
////7.自定义view，最好不要去获得最终大小，报着给出默认值的心态处理大小问题，很需要大小也应该在在onlayout获得确切的大小。
////  在activity中，最好在onstart.通过view.post(new runable),把消息投递到队列尾部。
////  onMeasure会执行多次，中间会连续插入onSizeChanged，onLayout，onDraw。最后已onLayout和onDraw收尾。所以自定义view，最好不要去获得最终大小，报着给出默认值的心态处理大小问题
////8.onlayout,可以获知view的位置。
//// getMeasureWidth()方法在measure()过程结束后就可以获取到了，而getWidth()方法要在layout()过程结束后才能获取到。再重申一遍！！！！！
//
////.ondraw ,一定要获取padding.
//public class LSCircleImageFinal extends View
//{
//    private int mGeometry=1;
//    private int mImgResourceID=0;
//    private int mRadius=100;
//
//    public LSCircleImageFinal(Context context, AttributeSet attrs)
//    {
//        super(context, attrs);
//        //it is no error. when attrs is null!
//        int[] genStyleid= R.styleable.circleArrtibute;
//        int radiusID=R.styleable.circleArrtibute_radius;
//        int imgID=R.styleable.circleArrtibute_img;
//        int geoID=R.styleable.circleArrtibute_geometry;
//
//        TypedArray typedArray=getTypedArray(getContext(), attrs, genStyleid);
//        mRadius=typedArray.getInteger(radiusID, mRadius);
//        mGeometry=typedArray.getInteger(geoID, mGeometry);
//        mImgResourceID=typedArray.getResourceId(imgID, mImgResourceID);
//    }
//
//    @Override
//    protected void  onMeasure(int widthMeasureSpec, int heightMeasureSpec)
//    {
//        SuggestMeasure suggestMeasure=getCommonMeasure(widthMeasureSpec, heightMeasureSpec, mRadius*2, mRadius*2,LSComponentsHelper.LS_CustomViewHelper.Enum_MeasureType.rate);
//        setMeasuredDimension(suggestMeasure.width, suggestMeasure.height);
//        LS_Log.Log_INFO("onmeasure");
//    }
//
//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh)
//    {
//        super.onSizeChanged(w, h, oldw, oldh);
//        LS_Log.Log_INFO(this.getId()+ "oldw:%d,oldh:%d,w:%d,h:%d.",oldw,oldh,w,h);
//    }
//
//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
//    {
//        LS_Log.Log_INFO("left:%d,top:%d,right:%d,bottom:%d,changed:%b",left,top,right,bottom,changed);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas)
//    {
//        super.onDraw(canvas);
//        LS_Log.Log_INFO("ondraw");
//        //图片切割成正方形。再缩放为view的大小。
//        //设置shader.画圆。
//        //padding,实现意义不大。因为有4个padding，实现要多点代码，这个完全可以用marging或其他代替。
//
//        Bitmap bitmap=BitmapFactory.decodeResource(getResources(), mImgResourceID);
//        int length=Math.min(bitmap.getHeight(), bitmap.getWidth());
//        bitmap= Bitmap.createBitmap(bitmap, (bitmap.getWidth()-length)/2, (bitmap.getHeight()-length)/2, length, length);
//
//        int viewMinLine=Math.min(getHeight(), getWidth());
//        bitmap=Bitmap.createScaledBitmap(bitmap, viewMinLine, viewMinLine, false);
//
//        Paint paint=new Paint();
//        BitmapShader bitmapShader=new BitmapShader(bitmap,Shader.TileMode.CLAMP , Shader.TileMode.CLAMP);
//        paint.setShader(bitmapShader);
//
//
//        int radius=Math.min((getRight()-getLeft())/2,(getBottom()-getTop())/2);
//        int centerx=radius;
//        int centery=radius;
//
//
//        if(getRight()-getLeft()>getBottom()-getTop())
//        {
//            int transforX=(getRight()-getLeft()-radius*2)/2;
//            canvas.translate(transforX, 0);
//        }
//        else
//        {
//            int transforY=(getBottom()-getTop()-radius*2)/2;
//            canvas.translate(0, transforY);
//        }
//
//        canvas.drawCircle(centerx, centery, radius, paint);
//    }
//}