package com.vakasai.a08drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawView extends View {
    private int y = 0;

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    private int dy = 5;
    private final Paint pnt = new Paint();

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        pnt.setColor(Color.argb(255, 23, 0, 230));
        canvas.drawCircle(100, y, 130.5f, pnt);
        y += dy;
        if (y < -130) {
            y = getHeight() + 130;
        }
        if (y > getHeight() + 130) {
            y = -130;
        }
        invalidate();
    }
}
