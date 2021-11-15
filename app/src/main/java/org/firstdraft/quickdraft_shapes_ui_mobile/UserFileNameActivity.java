package org.firstdraft.quickdraft_shapes_ui_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.firstdraft.draw_transmit_shapes.R;

public class UserFileNameActivity extends AppCompatActivity {

    TextView file_name_view;
    TextView user_name_view;

    Button add_shape_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_file_name);

        addButtonListener();
    }

    public void addButtonListener() {

        Context context = this;

        file_name_view = (TextView) findViewById(R.id.ShapeFileName);
        user_name_view = (TextView) findViewById(R.id.UserName);

        add_shape_button = (Button) findViewById(R.id.AddShape);

        add_shape_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                TransmitRectangleUtility.user_name = user_name_view.getText().toString();
                TransmitRectangleUtility.file_name = file_name_view.getText().toString();

                TransmitRectangleUtility.reset_shape_group();

                Intent intent = new Intent(context, FinalizeRectangleActivity.class);
                startActivity(intent);

            }
        });

    }
}