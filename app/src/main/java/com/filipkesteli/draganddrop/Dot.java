package com.filipkesteli.draganddrop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;

/**
 * Created by programer on 9.6.2016..
 */
public class Dot extends View {

    private Paint normalPaint;
    private Paint draggingPaint;
    private boolean inDrag; //flag da li smo u dragu ili nismo
    private int radius = 30;

    public Dot(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
        setupListeners();
    }

    private void init() {
        normalPaint = new Paint();
        normalPaint.setColor(Color.RED);
        draggingPaint = new Paint();
        draggingPaint.setColor(Color.MAGENTA);
    }

    private void setupListeners() {
        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                v.startDrag(null, new DragShadowBuilder(v), v, 0);
                return true;
            }
        });
        setOnDragListener(new OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        inDrag = true;
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        inDrag = false;
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //moramo ga pocet crtat, pa moramo znati cx i cy
        float cx = getWidth() / 2 + getLeftPaddingOffset();
        float cy = getHeight() / 2 + getTopPaddingOffset();

        Paint paint = normalPaint;
        if (inDrag) {
            paint = draggingPaint;
        }
        canvas.drawCircle(cx, cy, radius, paint);
        invalidate(); //stalno ce se crtat -> invalidate stalno ponovo poziva onDraw
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = 2 * radius + getPaddingLeft() + getPaddingRight();
        setMeasuredDimension(size, size); //moramo pozvati ovu metodu, inace se aplikacija raspadne
    }
}
