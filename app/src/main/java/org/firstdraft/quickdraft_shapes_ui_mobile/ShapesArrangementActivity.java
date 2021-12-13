package org.firstdraft.quickdraft_shapes_ui_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.firstdraft.draw_transmit_shapes.R;
import org.firstdraft.quickdraft_shapes_ui_mobile.ListShapeElements.ShapeListActivity;
import org.firstdraft.quickdraft_shapes_ui_mobile.RectanglesArrangementMobile.ShapeArrangementMobileView;
import org.firstdraft.quickdraft_shapes_ui_mobile.RectanglesArrangementMobile.ShapeArrangementUtility;

public class ShapesArrangementActivity extends AppCompatActivity {

    //ShapesArragementView arragement_view;
    ShapeArrangementMobileView arragement_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rectangles_arrangement);

        addButtonListener();

    }

    public void addButtonListener()
    {
        Context context = this;

        arragement_view = new ShapeArrangementMobileView(context);
        arragement_view.setBackgroundColor(Color.WHITE);

        Button commit_arrangement = (Button)findViewById(R.id.CommitArrangement);
        commit_arrangement.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                ShapeArrangementUtility.after_committing_arrangement();

                Intent intent = new Intent(context, ShapeListActivity.class);
                startActivity(intent);

                ShapesArrangementActivity.super.finish();

            }
        });

    }
}