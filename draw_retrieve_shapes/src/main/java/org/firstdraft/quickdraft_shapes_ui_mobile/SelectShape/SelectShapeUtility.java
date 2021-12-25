package org.firstdraft.quickdraft_shapes_ui_mobile.SelectShape;

public class SelectShapeUtility
{
    public static final int SHAPE_RECTANGLE = 0;
    public static final int SHAPE_ELLIPSE = 1;

    public static int shape_type = SHAPE_RECTANGLE;

    public static void reset_views()
    {
        RectangleView.s = "";
        RectangleView.connector_output = "";
        RectangleView.multiplication_factor = (float)1.0;

        RectangleView.base_width_current = RectangleView.RECTANGLE_BASE_WIDTH;
        RectangleView.base_height_current = RectangleView.RECTANGLE_BASE_HEIGHT;
        RectangleView.text_size_base = RectangleView.TEXT_BASE_SIZE;

        EllipseView.s = "";
        EllipseView.connector_output = "";
        EllipseView.multiplication_factor = (float)1.0;

        EllipseView.base_width_current = RectangleView.RECTANGLE_BASE_WIDTH;
        EllipseView.base_height_current = RectangleView.RECTANGLE_BASE_HEIGHT;
        EllipseView.text_size_base = RectangleView.TEXT_BASE_SIZE;
    }

    public static void set_shape_type(String shape_string)
    {
        if(shape_string == "rectangle")
        {
            shape_type = SHAPE_RECTANGLE;
        }
        else if(shape_string == "ellipse")
        {
            shape_type = SHAPE_ELLIPSE;
        }
    }

    public static int string_to_shape_type(String shape_type_string)
    {
        if(shape_type_string == "rectangle")
        {
            return SHAPE_RECTANGLE;
        }
        else if(shape_type_string == "ellipse")
        {
            return SHAPE_ELLIPSE;
        }

        return SHAPE_RECTANGLE; //default
    }

    public static String get_current_shape_string()
    {

        if(shape_type == SHAPE_RECTANGLE)
        {
            return "rectangle";
        }
        else if(shape_type == SHAPE_ELLIPSE)
        {
            return "ellipse";
        }

        return "rectangle";

    }

}
