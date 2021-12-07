package org.firstdraft.quickdraft_shapes_ui_mobile.ListShapeElements;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.firstdraft.draw_transmit_shapes.R;
import org.firstdraft.quickdraft_shapes_ui_mobile.CurrentShapeElement;
import org.firstdraft.quickdraft_shapes_ui_mobile.FinalizeRectangleActivity;
import org.firstdraft.quickdraft_shapes_ui_mobile.RectangleView;
import org.firstdraft.quickdraft_shapes_ui_mobile.RectanglesArrangementActivity;
import org.firstdraft.quickdraft_shapes_ui_mobile.TransmitRectangleActivity;
import org.firstdraft.quickdraft_shapes_ui_mobile.TransmitRectangleUtility;

import java.util.ArrayList;

public class ShapeListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_list);

        ShapeListAdapter adapter;

        ArrayList<ShapeElementModel> shapeList_as_arrayList =
                ShapeListUtility.convert_elementArray_to_arrayList(
                        TransmitRectangleUtility.set_array);

        adapter = new ShapeListAdapter( shapeList_as_arrayList ,
                this);

        ListView shape_list_view = (ListView) findViewById(R.id.shape_list_pointer);
        // Assign adapter to List
        shape_list_view.setAdapter(adapter);

        addButtonListener();
    }

    void addButtonListener()
    {

        Button transmit_button = (Button) findViewById(R.id.ShapeList_TransmitShapeGroup);
        Button add_button = (Button) findViewById(R.id.ShapeList_AddShape);
        Button arrange_button = (Button) findViewById(R.id.ShapeList_ArrangeShapes);

        Context context = this;

        transmit_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, TransmitRectangleActivity.class);
                startActivity(intent);

            }
        });

        add_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                CurrentShapeElement.scaling_factor =
                        (float) RectangleView.base_width_current /
                                (float)RectangleView.RECTANGLE_BASE_WIDTH;

                //TransmitRectangleUtility.add_shape_element();

                RectangleView.s = "";
                RectangleView.connector = "";
                RectangleView.multiplication_factor = (float)1.0;

                RectangleView.base_width_current = RectangleView.RECTANGLE_BASE_WIDTH;
                RectangleView.base_height_current = RectangleView.RECTANGLE_BASE_HEIGHT;
                RectangleView.text_size_base = RectangleView.TEXT_BASE_SIZE;

                FinalizeRectangleActivity.mScaleFactor = (float)1.0;

                Intent intent = new Intent(context, FinalizeRectangleActivity.class);
                startActivity(intent);

                ShapeListActivity.super.finish();

            }
        });

        arrange_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, RectanglesArrangementActivity.class);
                startActivity(intent);

                ShapeListActivity.super.finish();
            }
        });

    }

}