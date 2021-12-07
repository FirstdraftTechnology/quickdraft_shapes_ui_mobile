package org.firstdraft.quickdraft_shapes_ui_mobile.ListShapeElements;

import org.firstdraft.quickdraft_shapes_ui_mobile.ShapeElementTag;
import org.firstdraft.quickdraft_shapes_ui_mobile.TransmitRectangleUtility;

import java.util.ArrayList;

public class ShapeListUtility
{
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

            shapeList_as_arrayList.add(s);

        }

        return shapeList_as_arrayList;
    }
}
