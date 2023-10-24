package com.vakasai.a08drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class AmongView extends View {
    public AmongView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint pnt = new Paint();
        pnt.setColor(Color.argb(255, 23, 100, 230));
        pnt.setColor(Color.argb(255, 23, 100, 230));
        canvas.drawArc(100, 50, getWidth() - 100, 650, 180, 180, false, pnt);
        pnt.setStrokeWidth(8);
        canvas.drawRect(100, 645, 1000, 1000, pnt);
        canvas.drawLine(100, 350, 100, 1200, pnt);
        canvas.drawLine(getWidth() - 100, 350, getWidth() - 100, 1200, pnt);
        canvas.drawArc(100, 1160, 400, 1250, 0, 180, false, pnt);
        canvas.drawLine(400, 800, 400, 1205, pnt);
        canvas.drawArc(getWidth() - 400, 1160, getWidth() - 100, 1250, 0, 180, false, pnt);
        canvas.drawLine(getWidth() - 400, 800, getWidth() - 400, 1205, pnt);
        canvas.drawArc(400, 775, getWidth() - 400, 800, 0, 180, false, pnt);
        pnt.setColor(Color.argb(255, 149, 202, 220));
        canvas.drawOval(getWidth() / 2, 300, getWidth() - 50, 600, pnt);
        pnt.setColor(Color.argb(255, 255, 255, 255));
        canvas.drawOval(1000, 300, 700, 400, pnt);
    }
}
