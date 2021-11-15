package org.firstdraft.quickdraft_shapes_ui_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.firstdraft.draw_transmit_shapes.R;

public class FinalizeRectangleActivity extends AppCompatActivity {

    RectangleView drawView;

    TextView shape_text_view;

    TextView connector_view;
    //TextView file_name_view;
    //TextView user_name_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        addButtonListener();
    }

    public void addButtonListener()
    {

        Context context = this;

        Button shape_button = (Button) findViewById(R.id.ShowShape);

        shape_text_view = (TextView) findViewById(R.id.ShapeText);
        //file_name_view = (TextView) findViewById(R.id.ShapeFileName);
        //user_name_view = (TextView) findViewById(R.id.UserName);
        connector_view = (TextView) findViewById(R.id.Connector);

        Button transmit_button = (Button) findViewById(R.id.TransmitShape);
        Button append_button = (Button) findViewById(R.id.AppendShape);

        shape_text_view.setText(RectangleView.s);
        connector_view.setText(RectangleView.connector);
        //file_name_view.setText(RectangleView.file_name);
        //user_name_view.setText(RectangleView.user_name);

        shape_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String shape_string = shape_text_view.getText().toString();
                String connector_string = connector_view.getText().toString();
                //RectangleView.file_name = "after_delete.docx";
                //RectangleView.file_name = file_name_view.getText().toString();
                //RectangleView.user_name = user_name_view.getText().toString();

                RectangleView.s = shape_string;
                RectangleView.connector = connector_string;

                drawView = new RectangleView(context);
                drawView.setBackgroundColor(Color.WHITE);

                setContentView(R.layout.activity_main);
                addButtonListener();
                //setContentView(R.layout.activity_main);

            }
        });

        transmit_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                //Temp comment
                set_someMore_params();

                //Adding final shape
                TransmitRectangleUtility.add_shape_element();

                //Temp code for testing without UI
                /*CurrentShapeElement.base_width = 150;
                CurrentShapeElement.base_height = 100;
                CurrentShapeElement.horizontal_deviation = (float)106.5;

                CurrentShapeElement.shape_text = "Visual C#";

                TransmitRectangleUtility.add_shape_element();*/

                Intent intent = new Intent(context, TransmitRectangleActivity.class);
                startActivity(intent);

            }
        });

        append_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                TransmitRectangleUtility.add_shape_element();
                RectangleView.s = "";

                Intent intent = new Intent(context, FinalizeRectangleActivity.class);
                startActivity(intent);

            }
        });

    }

    private void set_someMore_params()
    {
            //TransmitRectangleUtility.user_name = user_name_view.getText().toString();
            //TransmitRectangleUtility.file_name = file_name_view.getText().toString();

    }

}