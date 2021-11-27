package org.firstdraft.quickdraft_shapes_ui_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.firstdraft.draw_transmit_shapes.R;
import org.firstdraft.quickdraft_shapes_ui_mobile.RectanglesArrangementMobile.RectangleArrangementMobileView;
import org.firstdraft.quickdraft_shapes_ui_mobile.RectanglesArrangementMobile.RectangleArrangementParams;
import org.firstdraft.quickdraft_shapes_ui_mobile.RectanglesArrangementMobile.RectangleArrangementUtility;

public class RectanglesArrangementActivity extends AppCompatActivity {

    //RectanglesArragementView arragement_view;
    RectangleArrangementMobileView arragement_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rectangles_arrangement);

        addButtonListener();

    }

    public void addButtonListener()
    {
        Context context = this;

        arragement_view = new RectangleArrangementMobileView(context);
        arragement_view.setBackgroundColor(Color.WHITE);

        Button commit_arrangement = (Button)findViewById(R.id.CommitArrangement);
        commit_arrangement.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                //Move to utility and change rect_distance_final name
                for(int i=1;i < RectangleArrangementParams.rectangle_count;i++)
                {
                    RectangleArrangementParams.rectangle_distance_array[i - 1]
                            = RectangleArrangementParams.rectangle_left_array[i]
                                - RectangleArrangementParams.rect_width_array[i-1]
                                - RectangleArrangementParams.rectangle_left_array[i - 1];

                    float new_distance_mf
                            = (float)RectangleArrangementParams.rectangle_distance_array[i - 1]
                                / (float)RectangleArrangementParams.rect_distance_final;

                    TransmitRectangleUtility.update_distance_mf(i-1,new_distance_mf);

                }

                RectangleArrangementUtility.initial_commit_done = true;

                Intent intent = new Intent(context, FinalizeRectangleActivity.class);
                startActivity(intent);

            }
        });

    }
}