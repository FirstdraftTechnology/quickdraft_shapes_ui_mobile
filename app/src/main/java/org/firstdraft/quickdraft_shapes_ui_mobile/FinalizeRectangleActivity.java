package org.firstdraft.quickdraft_shapes_ui_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import org.firstdraft.draw_transmit_shapes.R;
import org.firstdraft.quickdraft_shapes_ui_mobile.ListShapeElements.ShapeElementModel;
import org.firstdraft.quickdraft_shapes_ui_mobile.ListShapeElements.ShapeListActivity;
import org.firstdraft.quickdraft_shapes_ui_mobile.ListShapeElements.ShapeListAdapter;
import org.firstdraft.quickdraft_shapes_ui_mobile.ListShapeElements.ShapeListUtility;

import java.util.ArrayList;

public class FinalizeRectangleActivity extends AppCompatActivity {

    RectangleView drawView;

    TextView shape_text_view;

    CheckBox connector_checkbox;
    public static String connector_string;

    Button shape_button;

    private ScaleGestureDetector scaleGestureDetector;
    public static float mScaleFactor = 1.0f;

    public static final float LOWER_LIMIT_INIT = 0.5f;
    public static final float UPPER_LIMIT_INIT = 10.0f;

    public static float lower_limit = LOWER_LIMIT_INIT;
    public static float upper_limit = UPPER_LIMIT_INIT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
        mScaleFactor = 1.0f;
        addButtonListener();

        addListenerOnEditText();
    }


    public void addListenerOnEditText()
    {
        shape_text_view.setOnTouchListener(new View.OnTouchListener()
        {
            public boolean onTouch(View v, MotionEvent event)
            {
                shape_button.setTextColor(Color.WHITE);
                /*switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        shape_button.setTextColor(Color.WHITE);
                        return false;
                    case MotionEvent.ACTION_UP:
                        v.performClick();
                        break;
                    default:
                        break;
                }*/
                return false;

            }

        });
    }

    public void addButtonListener()
    {

        Context context = this;

        shape_button = (Button) findViewById(R.id.ShowShape);

        shape_text_view = (TextView) findViewById(R.id.ShapeText);
        connector_checkbox = (CheckBox) findViewById(R.id.Connector);

        /*Button transmit_button = (Button) findViewById(R.id.TransmitShape);
        Button append_button = (Button) findViewById(R.id.AppendShape);
        Button arrange_button = (Button) findViewById(R.id.ArrangeShapes);*/
        Button list_view = (Button) findViewById(R.id.ListView);

        shape_text_view.setText(RectangleView.s);
        Boolean connector_state = ShapeUtility.convert_connector_status(connector_string);
        connector_checkbox.setChecked(connector_state);

        drawView = findViewById(R.id.myView);
        drawView.setBackgroundColor(Color.WHITE);
        shape_button.setTextColor(Color.GREEN);

        shape_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String shape_string = shape_text_view.getText().toString();
                connector_string = ShapeUtility.get_connector_status(connector_checkbox);

                RectangleView.s = shape_string;
                RectangleView.multiplication_factor = mScaleFactor;

                lower_limit = lower_limit/RectangleView.multiplication_factor;
                upper_limit = upper_limit/RectangleView.multiplication_factor;

                mScaleFactor = 1.0f;

                RectangleView.connector_output = connector_string;

                //drawView = new RectangleView(context);
                //drawView = findViewById(R.id.myView);
                //drawView.setBackgroundColor(Color.WHITE);

                setContentView(R.layout.activity_main);
                addButtonListener();
                addListenerOnEditText();

                shape_button.setTextColor(Color.GREEN);
            }
        });

        /*transmit_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                //Temp comment
                set_someMore_params();

                //Adding final shape
                //TransmitRectangleUtility.add_shape_element();

                Intent intent = new Intent(context, TransmitRectangleActivity.class);
                startActivity(intent);

            }
        });

        append_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                CurrentShapeElement.scaling_factor =
                        (float)RectangleView.base_width_current /
                                (float)RectangleView.RECTANGLE_BASE_WIDTH;

                TransmitRectangleUtility.add_shape_element();

                RectangleView.s = "";
                RectangleView.connector = "";
                RectangleView.multiplication_factor = (float)1.0;

                RectangleView.base_width_current = RectangleView.RECTANGLE_BASE_WIDTH;
                RectangleView.base_height_current = RectangleView.RECTANGLE_BASE_HEIGHT;
                RectangleView.text_size_base = RectangleView.TEXT_BASE_SIZE;

                mScaleFactor = (float)1.0;

                Intent intent = new Intent(context, FinalizeRectangleActivity.class);
                startActivity(intent);

            }
        });

        arrange_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, RectanglesArrangementActivity.class);
                startActivity(intent);

            }
        });*/

        list_view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                CurrentShapeElement.scaling_factor =
                        (float)RectangleView.base_width_current /
                                (float)RectangleView.RECTANGLE_BASE_WIDTH;

                if(TransmitRectangleUtility.edit_operation == false)
                {
                    TransmitRectangleUtility.add_shape_element();
                }
                else
                {
                    TransmitRectangleUtility.edit_shape_element_commit
                            (TransmitRectangleUtility.edit_position);
                }

                RectangleView.s = "";
                RectangleView.connector_output = "";
                RectangleView.multiplication_factor = (float)1.0;

                RectangleView.base_width_current = RectangleView.RECTANGLE_BASE_WIDTH;
                RectangleView.base_height_current = RectangleView.RECTANGLE_BASE_HEIGHT;
                RectangleView.text_size_base = RectangleView.TEXT_BASE_SIZE;

                mScaleFactor = (float)1.0;

                Intent intent = new Intent(context, ShapeListActivity.class);
                startActivity(intent);

                FinalizeRectangleActivity.super.finish();

            }
        });

    }

    private void set_someMore_params()
    {
            //TransmitRectangleUtility.user_name = user_name_view.getText().toString();
            //TransmitRectangleUtility.file_name = file_name_view.getText().toString();
    }
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        scaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();

            /*if(mScaleFactor < 1.0f && RectangleView.multiplication_factor <= 0.5)
            {
                mScaleFactor = 1.0f;
                return false;
            }*/

            //mScaleFactor = Math.max(0.5f, Math.min(mScaleFactor, 10.0f));
            //mScaleFactor = Math.max(lower_limit, Math.min(mScaleFactor, 10.0f));
            mScaleFactor = Math.max(lower_limit, Math.min(mScaleFactor, Math.min(mScaleFactor, upper_limit)));
            //imageView.setScaleX(mScaleFactor);
            //imageView.setScaleY(mScaleFactor);

            drawView.setScaleX(mScaleFactor);
            drawView.setScaleY(mScaleFactor);

            shape_button.setTextColor(Color.WHITE);

            return true;
        }
    }
}