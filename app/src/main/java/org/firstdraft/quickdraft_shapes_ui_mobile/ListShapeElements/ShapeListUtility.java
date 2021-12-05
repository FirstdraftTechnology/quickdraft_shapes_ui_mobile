package org.firstdraft.quickdraft_shapes_ui_mobile.ListShapeElements;

import org.firstdraft.quickdraft_shapes_ui_mobile.ShapeElementTag;

import java.util.ArrayList;

public class ShapeListUtility
{
    public static ArrayList<ShapeElementModel> convert_elementArray_to_arrayList
            (ShapeElementTag[] shape_list_array)
    {
        ArrayList<ShapeElementModel> shapeList_as_arrayList = new ArrayList<>();

        for(int i = 0;
            i < shape_list_array.length; i++)
        {

            ShapeElementModel s = new ShapeElementModel();
            s.setShape_element_value(shape_list_array[i].shape_text);

            shapeList_as_arrayList.add(s);

        }

        return shapeList_as_arrayList;
    }
}
