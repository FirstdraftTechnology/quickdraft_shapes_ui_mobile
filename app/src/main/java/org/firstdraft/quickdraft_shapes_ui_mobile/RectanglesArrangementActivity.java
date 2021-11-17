package org.firstdraft.quickdraft_shapes_ui_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import org.firstdraft.draw_transmit_shapes.R;

public class RectanglesArrangementActivity extends AppCompatActivity {

    RectanglesArragementView arragement_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rectangles_arrangement);

        addButtonListener();

    }

    public void addButtonListener()
    {
        Context context = this;

        arragement_view = new RectanglesArragementView(context);
        arragement_view.setBackgroundColor(Color.WHITE);

        //setContentView(R.layout.activity_rectangles_arrangement);
        //addButtonListener();

    }
}