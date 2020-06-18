package com.example.pathdrawer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

import org.jetbrains.annotations.NotNull;


public class Shape {
    protected Paint mPaint;
    protected Path mPath;
    protected RectF mRegion;

    Shape() {
        Init();
        mRegion = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
    }
    Shape(@NotNull Shape s) {
        mPath = new Path(s.mPath);
        mPaint = new Paint(s.mPaint);
        mRegion = new RectF(s.mRegion);
    }
    Shape(RectF r) {
        Init();
        mRegion = new RectF(r);
    }

    Shape(float left,
          float top,
          float right,
          float bottom) {
        Init();
        mRegion = new RectF(left, top, right, bottom);
    }

    private void Init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.STROKE);

        mPath = new Path();
    }

    float getWidth() {return mRegion.width();}
    float getHeight() {return mRegion.height();}

    Shape offset(float dx,
                       float dy) {
        mRegion.offset(dx, dy);
        mPath.offset(dx, dy);
        return this;
    }

    Shape offsetTo(float newLeft,
                         float newTop) {
        mPath.offset(newLeft - mRegion.centerX() + mRegion.width() / 2,
                newTop - mRegion.centerY() + mRegion.height() / 2);
        mRegion.offsetTo(newLeft, newTop);
        return this;
    }

    public void draw(@org.jetbrains.annotations.NotNull Canvas canvas) {
        canvas.save();
        canvas.drawPath(mPath, mPaint);
        canvas.restore();
    }
}
