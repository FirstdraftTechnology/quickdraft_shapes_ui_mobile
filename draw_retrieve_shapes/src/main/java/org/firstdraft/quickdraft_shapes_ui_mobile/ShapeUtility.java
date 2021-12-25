package org.firstdraft.quickdraft_shapes_ui_mobile;

import android.widget.CheckBox;

import org.firstdraft.quickdraft_shapes_ui_mobile.SelectShape.EllipseView;
import org.firstdraft.quickdraft_shapes_ui_mobile.SelectShape.RectangleView;

public class ShapeUtility {

    public static String get_connector_status(CheckBox connector)
    {
        /*if(connector.isChecked()){
            return "true";
        }
        return "false";*/

        return boolean_to_string(connector.isChecked());
    }

    public static String boolean_to_string(Boolean connector_status)
    {
        if(connector_status == true){
            return "true";
        }
        return "false";
    }

    public static Boolean convert_connector_status(String status)
    {
        if(status == null)
        {
            return false;
        }

        if(status.equals("true"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void set_rectangle_view_params(float scaling_factor, String shape_text)
    {
        RectangleView.s = shape_text;

        RectangleView.multiplication_factor = (float)1.0;

        RectangleView.base_width_current = (int)((float)RectangleView.RECTANGLE_BASE_WIDTH *
                scaling_factor);
        RectangleView.base_height_current = (int)((float)RectangleView.RECTANGLE_BASE_HEIGHT *
                scaling_factor);
        RectangleView.text_size_base = (int)((float)RectangleView.TEXT_BASE_SIZE *
                scaling_factor);
    }

    public static void set_ellipse_view_params(float scaling_factor, String shape_text)
    {
        EllipseView.s = shape_text;

        EllipseView.multiplication_factor = (float)1.0;

        EllipseView.base_width_current = (int)((float)EllipseView.ELLIPSE_BASE_WIDTH *
                scaling_factor);
        EllipseView.base_height_current = (int)((float)EllipseView.ELLIPSE_BASE_HEIGHT *
                scaling_factor);
        EllipseView.text_size_base = (int)((float)EllipseView.TEXT_BASE_SIZE *
                scaling_factor);

    }

}
