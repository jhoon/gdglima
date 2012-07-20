package pe.gtug.android.hw.sensor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author andres
 * 
 */
public class CompassView extends View
{
    private float direction = 0;

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private boolean isFirstDraw;

    /**
     * @param context
     */
    public CompassView(Context context)
    {
        super(context);
        init();
    }

    /**
     * @param context
     * @param attrs
     */
    public CompassView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public CompassView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init();
    }

    /**
     * 
     */
    private void init()
    {
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setColor(Color.WHITE);
        paint.setTextSize(30);

        isFirstDraw = true;
    }

    /**
     * @see android.view.View#onMeasure(int, int)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
    }

    /**
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw(Canvas canvas)
    {
        float radioCompass;
        int xCompass = getMeasuredWidth() / 2;
        int yCompass = getMeasuredHeight() / 2;

        if (xCompass > yCompass)
        {
            radioCompass = (float) (yCompass * 0.9);
        }
        else
        {
            radioCompass = (float) (xCompass * 0.9);
        }

        canvas.drawCircle(xCompass, yCompass, radioCompass, paint);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);

        if (!isFirstDraw)
        {
            canvas.drawLine(xCompass, yCompass, (float) (xCompass + radioCompass
                    * Math.sin((double) (-direction) * 3.14 / 180)), (float) (yCompass - radioCompass
                    * Math.cos((double) (-direction) * 3.14 / 180)), paint);

            canvas.drawText(String.valueOf(direction), xCompass, yCompass, paint);
        }
    }

    /**
     * @param direction
     */
    public void updateDirection(float direction)
    {
        isFirstDraw = false;
        this.direction = direction;
        invalidate();
    }
}