package org.firstdraft.quickdraft_shapes_ui_mobile.ListShapeElements;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.firstdraft.draw_transmit_shapes.R;
import org.firstdraft.quickdraft_shapes_ui_mobile.FinalizeShapeActivity;
import org.firstdraft.quickdraft_shapes_ui_mobile.ShapesArrangementActivity;
import org.firstdraft.quickdraft_shapes_ui_mobile.SelectShape.SelectShapeActivity;
import org.firstdraft.quickdraft_shapes_ui_mobile.SelectShape.SelectShapeUtility;
import org.firstdraft.quickdraft_shapes_ui_mobile.TransmitShapeGroupActivity;
import org.firstdraft.quickdraft_shapes_ui_mobile.TransmitShapeGroupUtility;

import java.util.ArrayList;

public class ShapeListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_list);

        ShapeListAdapter adapter;

        ShapeListUtility.sla_instance = this;
        ArrayList<ShapeElementModel> shapeList_as_arrayList =
                ShapeListUtility.convert_elementArray_to_arrayList(
                        TransmitShapeGroupUtility.set_array);

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

                Intent intent = new Intent(context, TransmitShapeGroupActivity.class);
                startActivity(intent);

            }
        });

        add_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                /*CurrentShapeElement.scaling_factor =
                        (float) RectangleView.base_width_current /
                                (float)RectangleView.RECTANGLE_BASE_WIDTH;*/

                //TransmitShapeGroupUtility.add_shape_element();

                /*RectangleView.s = "";
                RectangleView.connector_output = "";
                RectangleView.multiplication_factor = (float)1.0;

                RectangleView.base_width_current = RectangleView.RECTANGLE_BASE_WIDTH;
                RectangleView.base_height_current = RectangleView.RECTANGLE_BASE_HEIGHT;
                RectangleView.text_size_base = RectangleView.TEXT_BASE_SIZE;*/

                SelectShapeUtility.reset_views();

                FinalizeShapeActivity.mScaleFactor = (float)1.0;

                FinalizeShapeActivity.connector_string = "false";

                FinalizeShapeActivity.lower_limit = FinalizeShapeActivity.LOWER_LIMIT_INIT;
                FinalizeShapeActivity.upper_limit = FinalizeShapeActivity.UPPER_LIMIT_INIT;

                Intent intent = new Intent(context, SelectShapeActivity.class);
                startActivity(intent);

                ShapeListActivity.super.finish();

            }
        });

        arrange_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, ShapesArrangementActivity.class);
                startActivity(intent);

                ShapeListActivity.super.finish();
            }
        });

    }

}