package com.example.question.castom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public class CustomShapeView extends View {
    private Paint paint;
    private Path path;

    public CustomShapeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

  {
        paint = new Paint();
        paint.setColor(0xFF000000); // Устанавливаем цвет линии
        paint.setStrokeWidth(8);    // Устанавливаем толщину линии
        paint.setStyle(Paint.Style.STROKE); // Делаем линию непрозрачной
        paint.setAntiAlias(true); // Включаем сглаживание

        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        // Начальная точка
        path.moveTo(width * 0.5f, 0);

        // Рисуем линии, образующие фигуру
        path.lineTo(width, height * 0.5f);
        path.lineTo(width * 0.5f, height);
        path.lineTo(0, height * 0.5f);

        // Замыкаем фигуру, соединяя последнюю точку с начальной
        path.close();

        // Рисуем фигуру на канве
        canvas.drawPath(path, paint);
    }
}
