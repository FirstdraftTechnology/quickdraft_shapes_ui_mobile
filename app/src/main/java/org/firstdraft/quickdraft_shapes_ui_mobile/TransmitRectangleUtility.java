package org.firstdraft.quickdraft_shapes_ui_mobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Xml;

import org.firstdraft.quickdraft_shapes_ui_mobile.ListShapeElements.ShapeListUtility;
import org.firstdraft.quickdraft_shapes_ui_mobile.RectanglesArrangementMobile.RectangleArrangementUtility;
import org.firstdraft.quickdraft_shapes_ui_mobile.SelectShape.EllipseView;
import org.firstdraft.quickdraft_shapes_ui_mobile.SelectShape.SelectShapeUtility;
import org.xmlpull.v1.XmlSerializer;

import java.io.StringWriter;

public class TransmitRectangleUtility {

    static String file_name = "";
    static String user_name = "";

    public static int canvas_width;
    public static int canvas_height;

    public static float vertical_mf = (float)1.0;
    public static float horizontal_mf = (float)1.0;

    public static ShapeElementTag set_array[];
    public static int set_count = 0;

    public static XmlSerializer serializer;

    public static boolean edit_operation = false;
    public static int edit_position = 0;

    public static void reset_shape_group()
    {
        set_count = 0;
        set_array = null;

        RectangleView.s = "";
        RectangleView.connector_output = "";

        RectangleArrangementUtility.initial_commit_done = false;
        RectangleArrangementUtility.last_commit_index = 0;
    }

    static void create_shape_element_wrapper(XmlSerializer serializer)
            throws Exception
    {
        for(int i=0;i<set_count;i++)
        {
            /*if(i%2 == 0)
            {
                set_array[i].distance_mf = (float)1.25;
            }
            else
            {
                set_array[i].distance_mf = (float)0.5;
            }*/
            set_array[i].create_shape_element(serializer);
        }

    }

    public static void update_distance_mf(int index,float distance_mf)
    {
        set_array[index].distance_mf = distance_mf;
    }

    static float get_distance_mf(int index)
    {
        return set_array[index].distance_mf;
    }

    public static void add_shape_element(int shape_type)
    {

        ShapeElementTag set = new ShapeElementTag();

        set.base_width = CurrentShapeElement.base_width;
        set.base_height = CurrentShapeElement.base_height;

        set.horizontal_deviation  = CurrentShapeElement.horizontal_deviation;
        set.shape_text = CurrentShapeElement.shape_text;

        set.connector = CurrentShapeElement.connector;
        set.scaling_factor = CurrentShapeElement.scaling_factor;

        int current_len = set_count;

        if(shape_type == SelectShapeUtility.SHAPE_ELLIPSE)
        {
            set.shape_type="ellipse";
        }
        else
        {
            set.shape_type = "rectangle";
        }

        ShapeElementTag newarr[] = new ShapeElementTag[current_len + 1];

        for (int i = 0; i < current_len; i++) {
                newarr[i] = set_array[i];
        }

        newarr[current_len] = set;

        set_array = newarr;
        set_count++;

    }

    //Assumption is shape_type will not change
    public static void edit_shape_element_commit(int position)
    {

        ShapeElementTag set = set_array[position];

        set.base_width = CurrentShapeElement.base_width;
        set.base_height = CurrentShapeElement.base_height;

        set.horizontal_deviation = CurrentShapeElement.horizontal_deviation;
        set.shape_text = CurrentShapeElement.shape_text;

        set.connector = CurrentShapeElement.connector;
        set.scaling_factor = CurrentShapeElement.scaling_factor;

        edit_operation = false;
    }

    public static void launch_edit_shape_element(Activity calling_activity,
                                                 Context context,
                                                 int position)
    {

        ShapeElementTag set = set_array[position];

        edit_operation = true;
        edit_position = position;

        float scaling_factor = set.scaling_factor;

        //RectangleView.s = set.shape_text;
        FinalizeShapeActivity.connector_string = set.connector;

        /*RectangleView.multiplication_factor = (float)1.0;

        RectangleView.base_width_current = (int)((float)RectangleView.RECTANGLE_BASE_WIDTH *
                                                        scaling_factor);
        RectangleView.base_height_current = (int)((float)RectangleView.RECTANGLE_BASE_HEIGHT *
                                                        scaling_factor);
        RectangleView.text_size_base = (int)((float)RectangleView.TEXT_BASE_SIZE *
                                                        scaling_factor);*/

        int shape_type_enum = SelectShapeUtility.string_to_shape_type(set.shape_type);
        if(shape_type_enum == SelectShapeUtility.SHAPE_RECTANGLE)
        {
            ShapeUtility.set_rectangle_view_params(scaling_factor,
                                                    set.shape_text);
        }
        else if(shape_type_enum == SelectShapeUtility.SHAPE_ELLIPSE)
        {
            ShapeUtility.set_ellipse_view_params(scaling_factor,
                                                    set.shape_text);
        }
        SelectShapeUtility.shape_type = shape_type_enum;

        FinalizeShapeActivity.lower_limit = FinalizeShapeActivity.LOWER_LIMIT_INIT
                                                /scaling_factor;
        FinalizeShapeActivity.upper_limit = FinalizeShapeActivity.UPPER_LIMIT_INIT
                                                /scaling_factor;
        FinalizeShapeActivity.mScaleFactor = 1.0f;

        Intent intent = new Intent(context, FinalizeShapeActivity.class);
        calling_activity.startActivity(intent);

        calling_activity.finish();
    }

    static String get_xml()
    {
        StringWriter writer = new StringWriter();
        serializer = Xml.newSerializer();

        try
        {

            serializer.setOutput(writer);
            serializer.startDocument("UTF-8", true);

            serializer.startTag("", "root");
            serializer.startTag("", "shape");

            serializer.attribute("", "file_name", file_name);
            serializer.attribute("", "canvas_width",
                                    Integer.toString(TransmitRectangleUtility.canvas_width));
            serializer.attribute("", "canvas_height",
                    Integer.toString(TransmitRectangleUtility.canvas_height));

            serializer.attribute("", "vertical_mf",
                    Float.toString(TransmitRectangleUtility.vertical_mf));
            serializer.attribute("", "horizontal_mf",
                    Float.toString(TransmitRectangleUtility.horizontal_mf));

            create_shape_element_wrapper(serializer);

            serializer.endTag("", "shape");
            serializer.endTag("", "root");

            serializer.endDocument();

        }
        catch (Exception e)
        {
            return null;
        }

        return writer.toString();
    }

}
