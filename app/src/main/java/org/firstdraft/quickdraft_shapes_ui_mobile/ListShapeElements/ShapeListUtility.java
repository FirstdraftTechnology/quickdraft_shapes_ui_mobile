package org.firstdraft.quickdraft_shapes_ui_mobile.ListShapeElements;

import android.widget.ImageView;

import org.firstdraft.quickdraft_shapes_ui_mobile.RectangleView;
import org.firstdraft.quickdraft_shapes_ui_mobile.SelectShape.EllipseView;
import org.firstdraft.quickdraft_shapes_ui_mobile.SelectShape.SelectShapeUtility;
import org.firstdraft.quickdraft_shapes_ui_mobile.ShapeElementTag;
import org.firstdraft.quickdraft_shapes_ui_mobile.ShapeUtility;
import org.firstdraft.quickdraft_shapes_ui_mobile.TransmitRectangleUtility;

import java.util.ArrayList;
import org.firstdraft.draw_transmit_shapes.R;

public class ShapeListUtility
{

    static ShapeListActivity sla_instance;

    public static void set_element_icon(int shape_type, ImageView icon_view)
    {
        if(shape_type == SelectShapeUtility.SHAPE_RECTANGLE)
        {
            icon_view.setImageResource(R.drawable.rectangle_static);
        }
        if(shape_type == SelectShapeUtility.SHAPE_ELLIPSE)
        {
            icon_view.setImageResource(R.drawable.ellipse_static2);
        }
    }

    public static ArrayList<ShapeElementModel> convert_elementArray_to_arrayList
            (ShapeElementTag[] shape_list_array)
    {

        ArrayList<ShapeElementModel> shapeList_as_arrayList = new ArrayList<>();

        if(shape_list_array == null)
        {
            return shapeList_as_arrayList;
        }

        if(TransmitRectangleUtility.set_count == 0)
        {
            shapeList_as_arrayList = new ArrayList<>();
            return shapeList_as_arrayList;
        }

        for(int i = 0;
            i < TransmitRectangleUtility.set_count; i++)
        {

            ShapeElementModel s = new ShapeElementModel();

            s.setShape_element_scale(shape_list_array[i].scaling_factor);
            s.setShape_element_text(shape_list_array[i].shape_text);

            int shape_element_type = SelectShapeUtility.
                                        string_to_shape_type(shape_list_array[i].shape_type);
            s.setShape_element_type(shape_element_type);

            shapeList_as_arrayList.add(s);

        }

        return shapeList_as_arrayList;
    }
}
