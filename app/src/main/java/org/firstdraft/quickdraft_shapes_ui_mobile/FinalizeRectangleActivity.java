package org.firstdraft.quickdraft_shapes_ui_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        addButtonListener();
    }

    private String get_connector_status(CheckBox connector)
    {
        if(connector.isChecked()){
            return "true";
        }
        return "false";
    }

    private Boolean convert_connector_status(String status)
    {
        if(status == null)
        {
            return false;
        }

        if(status.equals("true"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void addButtonListener()
    {

        Context context = this;

        Button shape_button = (Button) findViewById(R.id.ShowShape);

        shape_text_view = (TextView) findViewById(R.id.ShapeText);
        connector_checkbox = (CheckBox) findViewById(R.id.Connector);

        Button transmit_button = (Button) findViewById(R.id.TransmitShape);
        Button append_button = (Button) findViewById(R.id.AppendShape);

        shape_text_view.setText(RectangleView.s);
        Boolean connector_state = convert_connector_status(connector_string);
        connector_checkbox.setChecked(connector_state);

        shape_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String shape_string = shape_text_view.getText().toString();
                connector_string = get_connector_status(connector_checkbox);

                RectangleView.s = shape_string;
                RectangleView.connector = connector_string;

                drawView = new RectangleView(context);
                drawView.setBackgroundColor(Color.WHITE);

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
                TransmitRectangleUtility.add_shape_element();

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

    }

    private void set_someMore_params()
    {
            //TransmitRectangleUtility.user_name = user_name_view.getText().toString();
            //TransmitRectangleUtility.file_name = file_name_view.getText().toString();
    }

}