package org.firstdraft.quickdraft_shapes_ui_mobile;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import org.firstdraft.quickdraft_shapes_ui_mobile.SelectShape.SelectShapeUtility;

public class ShapesArragementView extends AppCompatTextView {

    Paint mpaint = new Paint();
    Paint paint2 = new Paint();

    float textSize;

    int SHAPE_BASE_WIDTH = 100;
    int SHAPE_BASE_HEIGHT = 67;

    //In main code, part of utility
    private String get_summary_string(String complete_text)
    {
        if(complete_text.length() < 5)
        {
            return complete_text;
        }

        String first_four_chars = complete_text.substring(0,4);
        return first_four_chars + "..";
    }

    private float draw_single_rectangle(Canvas canvas,
                                        int base_width, int base_height,
                                        String text,
                                        float y,
                                        float text_y,
                                        float horizontal_deviation,
                                        boolean connector)
    {
        float text_width = paint2.measureText(text)/2;
        float text_x = (float)7 + text_width/2;

        canvas.drawRect(7 + horizontal_deviation,
                y - textSize,
                base_width + 7 + text_width * 2 + horizontal_deviation,
                y - textSize + base_height, mpaint);

        canvas.drawText(text, (float)text_x + base_width/2 - text_width/2 + horizontal_deviation,
                text_y ,paint2);

        if(connector == true)
        {
            canvas.drawLine(base_width + 7 + text_width * 2 + horizontal_deviation,y - textSize + base_height/2,
                    base_width + 67 +  7 + text_width * 2 + horizontal_deviation, y - textSize + base_height/2,
                    mpaint);
        }

        return base_width + text_width * 2 + horizontal_deviation;
    }

    private float draw_single_ellipse(Canvas canvas,
                                        int base_width, int base_height,
                                        String text,
                                        float y,
                                        float text_y,
                                        float horizontal_deviation,
                                        boolean connector)
    {
        float text_width = paint2.measureText(text)/2;
        float text_x = (float)7 + text_width/2;

        canvas.drawOval(7 + horizontal_deviation,
                y - textSize,
                base_width + 7 + text_width * 2 + horizontal_deviation,
                y - textSize + base_height, mpaint);

        canvas.drawText(text, (float)text_x + base_width/2 - text_width/2 + horizontal_deviation,
                text_y ,paint2);

        if(connector == true)
        {
            canvas.drawLine(base_width + 7 + text_width * 2 + horizontal_deviation,y - textSize + base_height/2,
                    base_width + 67 +  7 + text_width * 2 + horizontal_deviation, y - textSize + base_height/2,
                    mpaint);
        }

        return base_width + text_width * 2 + horizontal_deviation;
    }

    private float draw_single_shape(int shape_type,
                                    Canvas canvas,
                                    int base_width, int base_height,
                                    String text,
                                    float y,
                                    float text_y,
                                    float horizontal_deviation,
                                    boolean connector)
    {
        float horizontal_deviation_new = horizontal_deviation;

        if(shape_type == SelectShapeUtility.SHAPE_RECTANGLE)
        {
            horizontal_deviation_new = draw_single_rectangle(canvas, base_width, base_height,
                    text,
                    y, text_y,
                    horizontal_deviation,
                    connector);
        }
        else if(shape_type == SelectShapeUtility.SHAPE_ELLIPSE)
        {
            horizontal_deviation_new = draw_single_ellipse(canvas, base_width, base_height,
                    text,
                    y, text_y,
                    horizontal_deviation,
                    connector);
        }

        return horizontal_deviation_new;
    }

    private void draw_shape_set(Canvas canvas,
                                      int base_width,int base_height
                                   )
    {
        paint2.setColor(Color.BLACK);
        paint2.setTextSize(34);

        textSize = paint2.getTextSize();

        paint2.setTextAlign(Paint.Align.LEFT);
        mpaint.setStyle(Paint.Style.STROKE);
        mpaint.setColor(Color.BLACK);

        int x = (this.getWidth()) / 2;
        int y = (this.getHeight() - base_height) / 2;

        float text_y;
        text_y = y + (int) textSize / 2;

        float horizontal_deviation = 0;

        boolean connector = true;

        int length = TransmitShapeGroupUtility.set_array.length;

        for (int i = 0; i < length; i++) {

            connector = ShapeUtility.convert_connector_status
                    (TransmitShapeGroupUtility.set_array[i].connector);

            if (i == length - 1) {
                connector = false;
            }

            String complete_text = TransmitShapeGroupUtility.set_array[i].shape_text;
            String summary_text = get_summary_string(complete_text);

            /*horizontal_deviation = draw_single_rectangle(canvas, base_width, base_height,
                    summary_text,
                    y, text_y,
                    horizontal_deviation,
                    connector);*/

            int shape_type = SelectShapeUtility.string_to_shape_type
                                (TransmitShapeGroupUtility.set_array[i].shape_type);

            horizontal_deviation = draw_single_shape(shape_type,
                    canvas, base_width, base_height,
                    summary_text,
                    y, text_y,
                    horizontal_deviation,
                    connector);

            horizontal_deviation += (67);

        }
    }

    public void onDraw(Canvas canvas) {

        draw_shape_set(canvas,SHAPE_BASE_WIDTH,SHAPE_BASE_HEIGHT);

    }

    public ShapesArragementView(Context c) {
        super(c);

    }

    public ShapesArragementView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public ShapesArragementView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

}
