package com.example.pathdrawer;

import android.graphics.PointF;
import android.graphics.RectF;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.graphics.Path.Direction.CW;

enum RectPointIndex {
    TOP_LEFT,
    BOTTOM_LEFT,
    BOTTOM_RIGHT,
    TOP_RIGHT
}

class Circle extends Shape {
    Circle(float x, float y, float radius) {
        super(x - radius, y - radius, x + radius, y + radius);
        mPath.addCircle(x, y, radius, CW);
    }
}

class Rectangle extends Shape {
    Rectangle(float left,
              float top,
              float right,
              float bottom) {
        super(left, top, right, bottom);
        mPath.addRect(mRegion, CW);
    }

    Rectangle(RectF rect) {
        this(rect.left, rect.top, rect.right, rect.bottom);
    }
}

class Oval extends Shape {
    Oval(float left,
         float top,
         float right,
         float bottom) {
        super(left, top, right, bottom);
        mPath.addOval(mRegion, CW);
    }

    Oval(float width,
         float height) {
        this(0.0f, 0.0f, width, height);
    }
}

class Arc extends Shape {
    Arc(float left,
        float top,
        float right,
        float bottom,
        float startAngle,
        float sweepAngle) {
        super(left, top, right, bottom);
        mPath.addArc(mRegion, startAngle, sweepAngle);
    }

    Arc(float width,
        float height,
        float startAngle,
        float sweepAngle) {
        this(0.0f, 0.0f, width, height, startAngle, sweepAngle);
    }
}

class Stroke extends Shape {

    Stroke(float left,
           float top,
           float right,
           float bottom,
           RectPointIndex start, RectPointIndex end) {
        super(left, top, right, bottom);

        PointF startPt = pointOf(start);
        mPath.moveTo(startPt.x, startPt.y);

        PointF endPt = pointOf(end);
        mPath.lineTo(endPt.x, endPt.y);

    }

    Stroke(RectF rect,
           RectPointIndex from, RectPointIndex to) {
        this(rect.left, rect.top, rect.right, rect.bottom, from, to);
    }

    Stroke(float width, float height,
           RectPointIndex from, RectPointIndex to) {
        this(0.0f, 0.0f, width, height, from, to);
    }

    PointF pointOf(@NotNull RectPointIndex in) {
        switch (in) {
            default:
            case TOP_LEFT:
                return new PointF(mRegion.left, mRegion.top);
            case BOTTOM_LEFT:
                return new PointF(mRegion.left, mRegion.bottom);
            case BOTTOM_RIGHT:
                return new PointF(mRegion.right, mRegion.bottom);
            case TOP_RIGHT:
                return new PointF(mRegion.right, mRegion.top);
        }
    }
}

public class MyShape {
    static class K extends Arc {
        K() {
            super(Block.K.width,Block.K.height,Sweep.K.startAngle,Sweep.K.sweepAngle);
            mRegion.set(mRegion.left, mRegion.top, mRegion.right, mRegion.bottom/2);
        }
    }
    static class G extends Arc {
        G() {
            super(Block.G.width,Block.G.height,Sweep.G.startAngle,Sweep.G.sweepAngle);
            mRegion.set(mRegion.left, mRegion.top, mRegion.right, mRegion.bottom/2);
        }
    }
    static class R extends Arc {
        R() {
            super(Block.R.width,Block.R.height,Sweep.R.startAngle,Sweep.R.sweepAngle);
            mRegion.set(mRegion.left, mRegion.top+mRegion.height()/2, mRegion.right, mRegion.bottom);
        }
    }
    static class L extends Arc {
        L() {
            super(Block.L.width,Block.L.height,Sweep.L.startAngle,Sweep.L.sweepAngle);
            mRegion.set(mRegion.left, mRegion.top+mRegion.height()/2, mRegion.right, mRegion.bottom);
        }
    }
    static class The extends Arc {
        The() {
            super(Block.THE.width,Block.THE.height,Sweep.THE.startAngle,Sweep.THE.sweepAngle);
            mRegion.set(mRegion.left, mRegion.top, mRegion.left+mRegion.width()/2, mRegion.top+mRegion.height()/2);
        }
    }
    static class Thr extends Arc {
        Thr() {
            super(Block.THR.width,Block.THR.height,Sweep.THR.startAngle,Sweep.THR.sweepAngle);
            mRegion.set(mRegion.left+mRegion.width()/2, mRegion.top+mRegion.height()/2, mRegion.right, mRegion.bottom);
        }
    }
    static class N extends Stroke {
        N() {
            super(Block.N.width,Block.N.height,RectPointIndex.BOTTOM_LEFT, RectPointIndex.BOTTOM_RIGHT);
        }
    }
    static class M extends Stroke {
        M() {
            super(Block.M.width, Block.M.height, RectPointIndex.BOTTOM_LEFT, RectPointIndex.BOTTOM_RIGHT);
        }
    }
    static class T extends Stroke {
        T() {
            super(Block.T.width, Block.T.height, RectPointIndex.BOTTOM_LEFT, RectPointIndex.TOP_RIGHT);
        }
    }
    static class D extends Stroke {
        D() {
            super(Block.D.width, Block.D.height, RectPointIndex.BOTTOM_LEFT, RectPointIndex.TOP_RIGHT);
        }
    }
    static class I extends Oval {
        I() {
            super(Block.I.width, Block.I.height);
        }
    }
    static class H extends Oval {
        H() {
            super(Block.H.width, Block.H.height);
        }
    }
    static class He extends Oval {
        He() {
            super(Block.HE.width, Block.HE.height);
        }
    }
    static Map<String, Shape> consonants = new HashMap<String, Shape>() {{
        put("k", new K());
        put("can", new K());

        put("g", new G());
        put("go", new G());
        put("good", new G());

        put("r", new R());
        put("are", new R());
        put("our", new R());
        put("hour", new R());

        put("l", new L());
        put("will", new L());
        put("well", new L());

        put("n", new N());
        put("in", new N());
        put("not", new N());

        put("m", new M());
        put("am", new M());
        put("more", new M());

        put("t", new T());
        put("it", new T());
        put("at", new T());

        put("d", new D());
        put("would", new D());

        put("i", new I());

        put("h", new H());
        put("a", new H());
        put("an", new H());

        put("he", new He());

        put("the", new The());
        put("thr", new Thr());
        put("there", new Thr());
        put("their", new Thr());
    }};

    static Shape getShape(String s) {
        String key = s.toLowerCase();
        if (consonants.containsKey(key))
            return new Shape(consonants.get(key));
        else
            return new Shape();
    }

    static final float STROKE_GAP = 10.0f;
    static final float COL_BEGIN = 0.0f;
    static final float ROW_BEGIN = 0.0f;
    static final float ROW_HEIGHT = 100.0f;
    static final float ROW_LENGTH = 500.0f;
    static final String SPLITTER = " ";
    static List<Shape> from(String script) {
        List<Shape> result = new ArrayList<>();
        List<String> utters = Arrays.asList(script.split(SPLITTER));
        float x = COL_BEGIN;
        float y = ROW_BEGIN;
        for (String utter : utters) {
            Shape shape = getShape(utter);
            shape.offset(x, y+ROW_HEIGHT-shape.getHeight());
            result.add(shape);
            x += shape.getWidth()+ STROKE_GAP;
            if (x > ROW_LENGTH) {
                x = COL_BEGIN;
                y += ROW_HEIGHT;
            }
        }
        return result;
    }

    enum Block {
        K(50.0f, 30.0f),
        G(80.0f, 30.0f),
        R(50.0f, 30.0f),
        L(80.0f, 30.0f),

        N(30.0f, 30.0f),
        M(50.0f, 50.0f),
        T(20.0f, 20.0f),
        D(40.0f, 40.0f),

        I(30.0f, 30.0f),
        H(3.0f, 3.0f),
        HE(10.0f, 10.0f),

        THE(30.0f, 30.0f),
        THR(30.0f, 30.0f);

        final float width;
        final float height;

        Block(float width, float height) {
            this.width = width;
            this.height = height;
        }
    }

    enum Sweep {
        K(180.0f, 180.0f),
        G(180.0f, 180.0f),
        R(0.0f, 180.0f),
        L(0.0f, 180.0f),

        THE(180.0f, 90.0f),
        THR(0.0f, 90.0f);

        final float startAngle;
        final float sweepAngle;

        Sweep(float startAngle, float sweepAngle) {
            this.startAngle = startAngle;
            this.sweepAngle = sweepAngle;
        }
    }
}