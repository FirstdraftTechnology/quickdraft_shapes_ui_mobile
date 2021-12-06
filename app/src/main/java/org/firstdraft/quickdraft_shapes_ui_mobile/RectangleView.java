package org.firstdraft.quickdraft_shapes_ui_mobile;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

public class RectangleView
        extends AppCompatTextView
{
    static String file_name = "";
    static String user_name = "";

    Paint mpaint = new Paint();

    Paint paint2 = new Paint();

    float text_width,textSize;

    public static String s = "";
    public static float multiplication_factor = (float)1.0;
    public static String connector =  "";

    EditText ed;

    public final static int RECTANGLE_BASE_WIDTH = 150;
    public final static int RECTANGLE_BASE_HEIGHT = 100;
    public final static int TEXT_BASE_SIZE = 50;

    public static int base_width_current = RECTANGLE_BASE_WIDTH;
    public static int base_height_current = RECTANGLE_BASE_HEIGHT;
    public static int text_size_base = TEXT_BASE_SIZE;

    private void draw_rectangle(Canvas canvas,
                                int base_width,int base_height,
                                int text_size_base,
                                String text)
    {
        paint2.setColor(Color.BLACK);
        paint2.setTextSize(text_size_base);  //set text size

        text_width = paint2.measureText(text)/2;
        textSize = paint2.getTextSize();

        paint2.setTextAlign(Paint.Align.CENTER);

        mpaint.setStyle(Paint.Style.STROKE);
        mpaint.setColor(Color.BLACK);

        int x = (this.getWidth() - base_width)/2;
        int y = (this.getHeight() - base_height)/2;

        int text_x,text_y;
        text_x = x + (int)text_width/2;
        text_y = y + (int)textSize/2;

        canvas.drawRect(x - text_width,
                y - textSize,
                x + text_width + base_width,
                y - textSize + base_height, mpaint);

        canvas.drawText(s, text_x, text_y ,paint2); //x=250,y=250

    }

    private void set_params()
    {

        CurrentShapeElement.base_width = base_width_current;
        CurrentShapeElement.base_height = base_height_current;
        CurrentShapeElement.horizontal_deviation = text_width;

        CurrentShapeElement.shape_text = s;
        CurrentShapeElement.connector = connector;

        /*TransmitRectangleUtility.add_shape_element(RECTANGLE_BASE_WIDTH, RECTANGLE_BASE_HEIGHT,
                                                    text_width, s);*/

        TransmitRectangleUtility.canvas_width = this.getWidth();
        TransmitRectangleUtility.canvas_height = this.getHeight();

    }

    @Override
    public void onDraw(Canvas canvas) {

        base_width_current = (int)((float)base_width_current * multiplication_factor);
        base_height_current = (int)((float)base_height_current * multiplication_factor);
        text_size_base = (int)((float)text_size_base * multiplication_factor);

        RectangleView.multiplication_factor = 1.0f;

        /*base_width_current = (int)((float)RECTANGLE_BASE_WIDTH * multiplication_factor);
        base_height_current = (int)((float)RECTANGLE_BASE_HEIGHT * multiplication_factor);
        text_size_base = (int)((float)TEXT_BASE_SIZE * multiplication_factor);*/

        draw_rectangle(canvas,base_width_current,
                base_height_current,text_size_base,
                s);

        set_params();

    }

    public RectangleView(Context c) {
        super(c);
    }

    public RectangleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RectangleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
