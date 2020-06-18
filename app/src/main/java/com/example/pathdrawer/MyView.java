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
        shapes = MyShape.from("K G R L N M T D H I He The Thr");
//        shapes = new ArrayList<>();
//        shapes.add(MyShape.getShape("K").offset(0.0f, 0.0f));
//        shapes.add(MyShape.getShape("G").offset(50.0f, 0.0f));
//        shapes.add(MyShape.getShape("R").offset(100.0f, 0.0f));
//        shapes.add(MyShape.getShape("L").offset(150.0f, 0.0f));
//
//        shapes.add(MyShape.getShape("N").offset(0.0f, 50.0f));
//        shapes.add(MyShape.getShape("M").offset(50.0f, 50.0f));
//        shapes.add(MyShape.getShape("T").offset(100.0f, 50.0f));
//        shapes.add(MyShape.getShape("D").offset(150.0f, 50.0f));
//
//        shapes.add(MyShape.getShape("H").offset(0.0f, 120.0f));
//        shapes.add(MyShape.getShape("I").offset(50.0f, 100.0f));
//        shapes.add(MyShape.getShape("He").offset(100.0f, 100.0f));
//
//        shapes.add(MyShape.getShape("The").offset(0.0f, 150.0f));
//        shapes.add(MyShape.getShape("Tha").offset(50.0f, 150.0f));
    }

    void drawOn(Canvas canvas) {
        for (Shape shape : shapes)
            shape.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        long starting = System.nanoTime();

        drawOn(canvas);

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