package org.firstdraft.quickdraft_shapes_ui_mobile;

import android.util.Xml;
import org.xmlpull.v1.XmlSerializer;

import java.io.StringWriter;

public class TransmitRectangleUtility {

    static String file_name = "";
    static String user_name = "";

    static int canvas_width;
    static int canvas_height;

    public static ShapeElementTag set_array[];
    static int set_count = 0;

    public static XmlSerializer serializer;

    public static void reset_shape_group()
    {
        set_count = 0;
        RectangleView.s = "";
        RectangleView.connector = "";

    }

    static void create_shape_element_wrapper(XmlSerializer serializer)
            throws Exception
    {
        for(int i=0;i<set_count;i++)
        {
            set_array[i].create_shape_element(serializer);
        }

    }

    static void add_shape_element()
    {

        ShapeElementTag set = new ShapeElementTag();

        set.base_width = CurrentShapeElement.base_width;
        set.base_height = CurrentShapeElement.base_height;

        set.horizontal_deviation  = CurrentShapeElement.horizontal_deviation;
        set.shape_text = CurrentShapeElement.shape_text;

        set.connector = CurrentShapeElement.connector;

        int current_len = set_count;

        ShapeElementTag newarr[] = new ShapeElementTag[current_len + 1];

        for (int i = 0; i < current_len; i++) {
                newarr[i] = set_array[i];
        }

        newarr[current_len] = set;

        set_array = newarr;
        set_count++;

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
