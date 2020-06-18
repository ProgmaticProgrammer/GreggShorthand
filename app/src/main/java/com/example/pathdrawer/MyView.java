package com.example.pathdrawer;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom view class for drawing path.
 */
public class MyView extends View {

    List<Shape> shapes;
    float ratioRadius, ratioInnerRadius;
    int numberOfPoint = 3; //default
    float rotate;

    //corresponding to UI element
    TextView textLayerInfo;

    public MyView(Context context) {
        super(context);
        initMyView();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initMyView();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initMyView();
    }

    public void initMyView() {
        shapes = new ArrayList<>();
        shapes.addAll(MyShape.from("I will"));
        shapes.addAll(MyShape.from("he can"));
        shapes.addAll(MyShape.from("it will"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        long starting = System.nanoTime();

        for (Shape shape : shapes)
            shape.draw(canvas);

        long end = System.nanoTime();

        String info = "myView.isHardwareAccelerated() = " + isHardwareAccelerated() + "\n"
                + "canvas.isHardwareAccelerated() = " + canvas.isHardwareAccelerated() + "\n"
                + "processing time (reference only) : " + (end - starting) + " (ns)";
        textLayerInfo.setText(info);

    }

    public void setShapeRadiusRatio(float ratio) {
        ratioRadius = ratio;
    }

    public void setShapeInnerRadiusRatio(float ratio) {
        ratioInnerRadius = ratio;
    }

    public void setNumberOfPoint(int pt) {
        numberOfPoint = pt;
    }

    public void passElements(TextView textLayerInfo) {
        this.textLayerInfo = textLayerInfo;
    }

    public void setShapeRotate(int rot) {
        rotate = (float) rot;
    }

}