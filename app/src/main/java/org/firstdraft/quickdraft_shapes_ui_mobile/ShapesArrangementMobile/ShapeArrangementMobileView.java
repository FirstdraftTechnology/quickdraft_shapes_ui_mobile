package org.firstdraft.quickdraft_shapes_ui_mobile.ShapesArrangementMobile;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import org.firstdraft.quickdraft_shapes_ui_mobile.SelectShape.SelectShapeUtility;

public class ShapeArrangementMobileView extends AppCompatTextView
    implements ShapeArrangementCallbacks
{

    Rect mRect;
    OvalShape mOval;

    Paint mPaint;
    static Paint text_paint;

    int rect_left = ShapeArrangementParams.shape_left_start;
    int rect_top = ShapeArrangementParams.shape_top_initial;
    int touched_rectangle;
    float press_down_x, press_down_y;

    public ShapeArrangementMobileView(Context context) {
        super(context);

        init(null);
    }

    public ShapeArrangementMobileView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public ShapeArrangementMobileView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    /*public ShapeArrangementMobileView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
        //text_paint.setTextSize(34);
        text_paint.setTextSize(ShapeArrangementParams.text_size_initial);

        ShapeArrangementParams.text_size = (int)text_paint.getTextSize();
        ShapeArrangementParams.arrangement_view = this;
        ShapeArrangementParams.init();

    }

    public int get_touched_rectangle(int x,int y)
    {
        return ShapeArrangementUtility.get_touched_rectangle(x,y);
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        boolean value = super.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {

                press_down_x = event.getX();
                press_down_y = event.getY();

                touched_rectangle =
                        ShapeArrangementUtility.
                                get_touched_rectangle((int) press_down_x, (int) press_down_y);

                return true;
            }
            case MotionEvent.ACTION_MOVE: {
                float movedX, movedY;

                movedX = event.getX();
                movedY = event.getY();

                float distanceX = movedX - press_down_x;
                float distanceY = movedY - press_down_y;

                rect_left = (int) press_down_x - ShapeArrangementParams.shape_width / 2 + (int) distanceX;
                rect_top = (int) press_down_y - ShapeArrangementParams.shape_height / 2 + (int) distanceY;

                ShapeArrangementParams.shape_top = rect_top;
                ShapeArrangementParams.increment_rectangle_left_with_string
                        (touched_rectangle, (int) distanceX);

                postInvalidate();

                press_down_x = event.getX();
                press_down_y = event.getY();

                return true;
            }
        }

        return value;
    }

    private void draw_single_shape_with_text(int shape_type,
                                            Canvas canvas,
                                             int rect_left_local, int rect_top_local,
                                             int rect_width_local, int rect_height_local,
                                             String text, boolean connector,
                                             int next_shape_left)
    {
        if(shape_type == SelectShapeUtility.SHAPE_RECTANGLE)
        {
            draw_single_rectangle_with_text(
                    canvas,
                    rect_left_local, rect_top_local,
                    rect_width_local, rect_height_local,
                    text,connector,
                    next_shape_left);
        }
        else if(shape_type == SelectShapeUtility.SHAPE_ELLIPSE)
        {
            draw_single_ellipse_with_text(
                    canvas,
                    rect_left_local, rect_top_local,
                    rect_width_local, rect_height_local,
                    text,connector,
                    next_shape_left);
        }

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
        float text_y = rect_top_local + (int) ShapeArrangementParams.text_size;

        float text_width = rect_width_local - ShapeArrangementParams.shape_width;
        canvas.drawText(text, (float)text_x + rect_width_local/2 - text_width/2,
                text_y ,text_paint);

    }

    private  void draw_single_ellipse_with_text(Canvas canvas,
                                                  int rect_left_local, int rect_top_local,
                                                  int rect_width_local, int rect_height_local,
                                                  String text, boolean connector,
                                                  int next_shape_left)
    {

        /*mRect.left = rect_left_local;
        mRect.top = rect_top_local;
        mRect.right = rect_left_local + rect_width_local;
        mRect.bottom = rect_top_local + rect_height_local;*/

        RectF oval_object = new RectF(rect_left_local, rect_top_local,
                                        rect_width_local, rect_height_local);

        canvas.drawOval(rect_left_local, rect_top_local,
                    rect_left_local + rect_width_local,
                rect_top_local + rect_height_local,
                        mPaint);

        //Connector
        if(connector == true)
        {
            canvas.drawLine(rect_left_local + rect_width_local,
                    rect_top_local + rect_height_local/2,
                    next_shape_left,
                    rect_top_local + rect_height_local/2,
                    mPaint);

        }

        //Text inside rectangle
        float text_x = (float)rect_left_local;
        float text_y = rect_top_local + (int) ShapeArrangementParams.text_size;

        float text_width = rect_width_local - ShapeArrangementParams.shape_width;
        canvas.drawText(text, (float)text_x + rect_width_local/2 - text_width/2,
                text_y ,text_paint);

    }



    protected void onDraw(Canvas canvas)
    {

        int rect_height_local = ShapeArrangementParams.shape_height;

        for(int i=0;
            i < ShapeArrangementParams.shape_count;
            i++)
        {

            boolean connector = false;
            int next_shape_left = -1;//initialization

            if(i != ShapeArrangementParams.shape_count - 1)
            {
                connector = ShapeArrangementParams.connector[i];
                next_shape_left = ShapeArrangementParams.shape_left_array[i+1];
            }

            draw_single_shape_with_text(ShapeArrangementParams.shape_type[i],
                    canvas,
                    ShapeArrangementParams.shape_left_array[i],
                    ShapeArrangementParams.shape_top,
                    ShapeArrangementParams.shape_width_array[i], rect_height_local,
                    ShapeArrangementParams.shape_string_array[i],
                    connector,
                    next_shape_left);


        }

    }

}
