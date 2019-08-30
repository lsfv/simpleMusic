package com.linson.LSLibrary.CustomUI;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public abstract class LSViewHelper extends View
{
    protected int mLeftRemovePadding,mTopRemovePadding,mRightRemovePadding,mBottomRemovePadding;

    public LSViewHelper(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    protected final void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width=MeasureSpec.getSize(widthMeasureSpec);
        int wMode=MeasureSpec.getMode(widthMeasureSpec);
        int height=MeasureSpec.getSize(heightMeasureSpec);
        int hMode=MeasureSpec.getMode(heightMeasureSpec);
        width=wMode==MeasureSpec.AT_MOST?setWrapContentWidth():width;
        height=hMode==MeasureSpec.AT_MOST?setWrapContentHeight():height;

        setMeasuredDimension(width, height);

        onAfterMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    protected abstract int setWrapContentWidth();
    protected abstract int setWrapContentHeight();
    protected abstract void onAfterMeasure(int widthMeasureSpec, int heightMeasureSpec);

    @Override
    protected final void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        super.onLayout(changed, left, top, right, bottom);
        mLeftRemovePadding =getLeft()+ getPaddingLeft();
        mTopRemovePadding = getTop()+getPaddingTop();
        mRightRemovePadding = getRight()+getPaddingLeft();
        mBottomRemovePadding = getBottom()+getPaddingTop();
        onLayout_real(changed, left, top, right, bottom);
    }

    protected abstract void onLayout_real(boolean changed, int left, int top, int right, int bottom);
}