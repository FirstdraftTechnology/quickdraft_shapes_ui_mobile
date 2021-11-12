package org.firstdraft.quickdraft_shapes_ui_mobile;

import android.util.Xml;
import org.xmlpull.v1.XmlSerializer;

import java.io.StringWriter;

public class TransmitRectangleUtility {

    static String file_name = "";
    static String user_name = "";

    static int canvas_width;
    static int canvas_height;

    /*static float base_width;
    static float base_height;
    static float horizontal_deviation;

    static String shape_text;*/

    static ShapeElementTag set_class_variable[];

    public static XmlSerializer serializer;

    /*static void create_shape_element(XmlSerializer serializer,
                                     float base_width, float base_height,
                                     float horizontal_deviation, String shape_text)
            throws Exception
    {

        serializer.startTag("", "shape_element");

        serializer.attribute("", "base_width", Float.toString(base_width));
        serializer.attribute("", "base_height", Float.toString(base_height));
        serializer.attribute("", "horizontal_deviation",
                                                        Float.toString(horizontal_deviation));

        serializer.text(shape_text);

        serializer.endTag("", "shape_element");

    }*/

    static void create_shape_element_wrapper(XmlSerializer serializer/*,
                                     float base_width, float base_height,
                                     float horizontal_deviation, String shape_text*/)
            throws Exception
    {
        /*ShapeElementTag set = new ShapeElementTag();

        set.base_width = base_width;
        set.base_height = base_height;

        set.horizontal_deviation  = horizontal_deviation;
        set.shape_text = shape_text;*/

        set_class_variable[0].create_shape_element(serializer);

    }

    static void add_shape_element(float base_width, float base_height,
                                  float horizontal_deviation, String shape_text)
    {

        ShapeElementTag set = new ShapeElementTag();

        set.base_width = base_width;
        set.base_height = base_height;

        set.horizontal_deviation  = horizontal_deviation;
        set.shape_text = shape_text;

        set_class_variable = new ShapeElementTag[1];
        set_class_variable[0] = set;

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

            /*serializer.startTag("", "shape_element");

            serializer.attribute("", "base_width", Float.toString(base_width));
            serializer.attribute("", "base_height", Float.toString(base_height));
            serializer.attribute("", "horizontal_deviation",
                                    Float.toString(horizontal_deviation));

            serializer.text(shape_text);

            serializer.endTag("", "shape_element");*/

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
