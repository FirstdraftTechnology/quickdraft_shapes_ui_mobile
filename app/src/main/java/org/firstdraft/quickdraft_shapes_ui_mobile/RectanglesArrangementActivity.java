package org.firstdraft.quickdraft_shapes_ui_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import org.firstdraft.draw_transmit_shapes.R;
import org.firstdraft.quickdraft_shapes_ui_mobile.RectanglesArrangementMobile.RectangleArrangementMobileView;

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

    }
}