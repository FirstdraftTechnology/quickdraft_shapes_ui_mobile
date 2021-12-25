package org.firstdraft.quickdraft_shapes_ui_mobile.SelectShape;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.firstdraft.draw_transmit_shapes.R;
import org.firstdraft.quickdraft_shapes_ui_mobile.FinalizeShapeActivity;

public class SelectShapeActivity extends AppCompatActivity {

    RadioButton select_shape_button;
    RadioGroup select_shape_radio_group;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_shape);

        addButtonListener();
    }

    public void addButtonListener()
    {

        Context context = this;
        Button commit_select = (Button)findViewById(R.id.CommitSelection);

        commit_select.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                select_shape_radio_group = (RadioGroup)findViewById(R.id.radioGroupShape);
                int selectedId = select_shape_radio_group.getCheckedRadioButtonId();
                select_shape_button = (RadioButton) findViewById(selectedId);

                if(selectedId == -1)
                {
                    return;
                }
                else
                {

                    String button_selected_rawString = select_shape_button.getText().toString();
                    String button_selected_refined = "rectangle";

                    if(button_selected_rawString.equals("  Rectangle"))
                    {
                        button_selected_refined = "rectangle";
                    }
                    else if(button_selected_rawString.equals("  Ellipse"))
                    {
                        button_selected_refined = "ellipse";
                    }

                    SelectShapeUtility.set_shape_type(button_selected_refined);

                }

                Intent intent = new Intent(context, FinalizeShapeActivity.class);
                startActivity(intent);

                SelectShapeActivity.super.finish();

            }
        });
    }

}