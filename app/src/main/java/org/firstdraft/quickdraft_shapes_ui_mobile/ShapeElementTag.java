package org.firstdraft.quickdraft_shapes_ui_mobile;

import org.xmlpull.v1.XmlSerializer;

public class ShapeElementTag {

    float base_width;
    float base_height;

    float horizontal_deviation;
    public float distance_mf = (float)1.0;

    public String shape_text;

    public String connector = "true";
    public String shape_type = "rectangle";//default
    public float scaling_factor = (float)1.0;

    void create_shape_element(XmlSerializer serializer)
            throws Exception
    {

        serializer.startTag("", "shape_element");

        serializer.attribute("", "base_width", Float.toString(base_width));
        serializer.attribute("", "base_height", Float.toString(base_height));

        serializer.attribute("", "horizontal_deviation",
                Float.toString(horizontal_deviation));
        serializer.attribute("", "distance_mf",
                Float.toString(distance_mf));

        serializer.attribute("", "connector",
                connector);
        serializer.attribute("", "shape_type",
                shape_type);
        serializer.attribute("", "scaling_factor",
                Float.toString(scaling_factor));

        serializer.text(shape_text);

        serializer.endTag("", "shape_element");

    }
}
