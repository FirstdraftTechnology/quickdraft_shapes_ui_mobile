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

public class FinalizeRectangleActivity extends AppCompatActivity {

    RectangleView drawView;

    TextView shape_text_view;

    CheckBox connector_checkbox;
    String connector_string;

    private ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
        addButtonListener();
    }

    public void addButtonListener()
    {

        Context context = this;

        Button shape_button = (Button) findViewById(R.id.ShowShape);

        shape_text_view = (TextView) findViewById(R.id.ShapeText);
        connector_checkbox = (CheckBox) findViewById(R.id.Connector);

        Button transmit_button = (Button) findViewById(R.id.TransmitShape);
        Button append_button = (Button) findViewById(R.id.AppendShape);
        Button arrange_button = (Button) findViewById(R.id.ArrangeShapes);

        shape_text_view.setText(RectangleView.s);
        Boolean connector_state = ShapeUtility.convert_connector_status(connector_string);
        connector_checkbox.setChecked(connector_state);

        drawView = findViewById(R.id.myView);
        drawView.setBackgroundColor(Color.WHITE);

        shape_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String shape_string = shape_text_view.getText().toString();
                connector_string = ShapeUtility.get_connector_status(connector_checkbox);

                RectangleView.s = shape_string;
                RectangleView.connector = connector_string;

                //drawView = new RectangleView(context);
                //drawView = findViewById(R.id.myView);
                //drawView.setBackgroundColor(Color.WHITE);

                setContentView(R.layout.activity_main);
                addButtonListener();

            }
        });

        transmit_button.setOnClickListener(new View.OnClickListener() {

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

                TransmitRectangleUtility.add_shape_element();
                RectangleView.s = "";
                RectangleView.connector = "";

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
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
            //imageView.setScaleX(mScaleFactor);
            //imageView.setScaleY(mScaleFactor);

            drawView.setScaleX(mScaleFactor);
            drawView.setScaleY(mScaleFactor);

            return true;
        }
    }
}