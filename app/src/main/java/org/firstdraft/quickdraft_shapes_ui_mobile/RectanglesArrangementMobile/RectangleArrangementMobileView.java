package org.firstdraft.quickdraft_shapes_ui_mobile.RectanglesArrangementMobile;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import org.firstdraft.quickdraft_shapes_ui_mobile.TransmitRectangleUtility;

public class RectangleArrangementMobileView extends AppCompatTextView
    implements RectangleArrangementCallbacks
{

    Rect mRect;

    Paint mPaint;
    static Paint text_paint;

    int rect_left = 10;
    int rect_top = 10;
    int touched_rectangle;
    float press_down_x, press_down_y;

    public RectangleArrangementMobileView(Context context) {
        super(context);

        init(null);
    }

    public RectangleArrangementMobileView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public RectangleArrangementMobileView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    /*public RectangleArrangementMobileView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(attrs);
    }*/

    public int get_text_measure(String text)
    {
        return (int)text_paint.measureText(text);
    }

    public void init(@Nullable AttributeSet set)
    {
        mRect = new Rect();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        text_paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        //mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);

        text_paint.setTextAlign(Paint.Align.LEFT);
        text_paint.setColor(Color.BLACK);
        text_paint.setTextSize(34);

        RectangleArrangementParams.text_size = (int)text_paint.getTextSize();
        RectangleArrangementParams.arrangement_view = this;
        RectangleArrangementParams.init();

    }

    public int get_touched_rectangle(int x,int y)
    {
        return RectangleArrangementUtility.get_touched_rectangle(x,y);
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        boolean value = super.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {

                press_down_x = event.getX();
                press_down_y = event.getY();

                touched_rectangle =
                        RectangleArrangementUtility.
                                get_touched_rectangle((int) press_down_x, (int) press_down_y);

                return true;
            }
            case MotionEvent.ACTION_MOVE: {
                float movedX, movedY;

                movedX = event.getX();
                movedY = event.getY();

                float distanceX = movedX - press_down_x;
                float distanceY = movedY - press_down_y;

                rect_left = (int) press_down_x - RectangleArrangementParams.rect_width / 2 + (int) distanceX;
                rect_top = (int) press_down_y - RectangleArrangementParams.rect_height / 2 + (int) distanceY;

                RectangleArrangementParams.rectangle_top = rect_top;
                RectangleArrangementParams.increment_rectangle_left_with_string
                        (touched_rectangle, (int) distanceX);

                postInvalidate();

                press_down_x = event.getX();
                press_down_y = event.getY();

                return true;
            }
        }

        return value;
    }

    private  void draw_single_rectangle_with_text(Canvas canvas,
                                                  int rect_left_local, int rect_top_local,
                                                  int rect_width_local, int rect_height_local,
                                                  String text, boolean connector,
                                                  int next_rectangle_left)
    {

        mRect.left = rect_left_local;
        mRect.top = rect_top_local;
        mRect.right = rect_left_local + rect_width_local;
        mRect.bottom = rect_top_local + rect_height_local;

        canvas.drawRect(mRect,mPaint);

        //Connector
        if(connector == true)
        {
                canvas.drawLine(rect_left_local + rect_width_local,
                                rect_top_local + rect_height_local/2,
                                      next_rectangle_left,
                                rect_top_local + rect_height_local/2,
                                        mPaint);

        }

        //Text inside rectangle
        float text_x = (float)rect_left_local;
        float text_y = rect_top_local + (int) RectangleArrangementParams.text_size;

        float text_width = rect_width_local - RectangleArrangementParams.rect_width;
        canvas.drawText(text, (float)text_x + rect_width_local/2 - text_width/2,
                text_y ,text_paint);

    }

    protected void onDraw(Canvas canvas)
    {

        int rect_height_local = RectangleArrangementParams.rect_height;

        for(int i=0;
            i < RectangleArrangementParams.rectangle_count;
            i++)
        {

            boolean connector = false;
            int next_rectangle_left = -1;//initialization

            if(i != RectangleArrangementParams.rectangle_count - 1)
            {
                connector = RectangleArrangementParams.connector[i];
                next_rectangle_left = RectangleArrangementParams.rectangle_left_array[i+1];
            }

            draw_single_rectangle_with_text(canvas,
                    RectangleArrangementParams.rectangle_left_array[i],
                    RectangleArrangementParams.rectangle_top,
                    RectangleArrangementParams.rect_width_array[i], rect_height_local,
                    RectangleArrangementParams.rect_string_array[i],
                    connector,
                    next_rectangle_left);

        }

    }

}
