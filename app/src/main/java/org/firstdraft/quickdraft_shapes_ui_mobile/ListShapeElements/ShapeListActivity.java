package org.firstdraft.quickdraft_shapes_ui_mobile.ListShapeElements;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import org.firstdraft.draw_transmit_shapes.R;
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

    }
}