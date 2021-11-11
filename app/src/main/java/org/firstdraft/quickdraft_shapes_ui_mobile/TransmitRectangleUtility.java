package org.firstdraft.quickdraft_shapes_ui_mobile;

import android.util.Xml;
import org.xmlpull.v1.XmlSerializer;

import java.io.StringWriter;

public class TransmitRectangleUtility {

    static String file_name = "";
    static String user_name = "";

    static int canvas_width;
    static int canvas_height;

    static float base_width;
    static float base_height;
    static float horizontal_deviation;

    static String shape_text;

    public static XmlSerializer serializer;

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

            serializer.startTag("", "shape_element");

            serializer.attribute("", "base_width", Float.toString(base_width));
            serializer.attribute("", "base_height", Float.toString(base_height));
            serializer.attribute("", "horizontal_deviation",
                                    Float.toString(horizontal_deviation));

            serializer.text(shape_text);

            serializer.endTag("", "shape_element");

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
