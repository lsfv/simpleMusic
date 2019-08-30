package com.linson.LSLibrary.CustomUI;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class LSDrawable_circleMask extends Drawable
{
    private Paint mPaint;

    public LSDrawable_circleMask()
    {
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(0x00000000);
    }

    @Override
    public void draw(@NonNull Canvas canvas)
    {
        canvas.drawColor(0x66000000);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            canvas.clipOutRect(100, 100, 200, 200);
        }
        //canvas.drawCircle(300, 300, 100, mPaint);
    }

    @Override
    public void setAlpha(int alpha)
    {
        setAlpha(alpha);
        invalidateSelf();
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter)
    {
        setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override
    public int getOpacity()
    {
        return PixelFormat.TRANSLUCENT;
    }
}
