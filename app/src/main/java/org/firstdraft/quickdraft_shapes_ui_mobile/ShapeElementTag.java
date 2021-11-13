package org.firstdraft.quickdraft_shapes_ui_mobile;

import org.xmlpull.v1.XmlSerializer;

public class ShapeElementTag {

    float base_width;
    float base_height;

    float horizontal_deviation;
    String shape_text;

    void create_shape_element(XmlSerializer serializer)
            throws Exception
    {

        serializer.startTag("", "shape_element");

        serializer.attribute("", "base_width", Float.toString(base_width));
        serializer.attribute("", "base_height", Float.toString(base_height));
        serializer.attribute("", "horizontal_deviation",
                Float.toString(horizontal_deviation));

        serializer.text(shape_text);

        serializer.endTag("", "shape_element");

    }
}
