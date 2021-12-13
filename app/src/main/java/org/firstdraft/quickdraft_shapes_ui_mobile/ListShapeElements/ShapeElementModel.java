package org.firstdraft.quickdraft_shapes_ui_mobile.ListShapeElements;

public class ShapeElementModel
{
    private float shape_element_scale;
    private String shape_element_text;
    private int shape_element_type;

    public String getShape_element_scale()
    {
        return Float.toString(shape_element_scale);
    }
    public void setShape_element_scale(float shape_element_scale)
    {
        this.shape_element_scale = shape_element_scale;
    }

    public String getShape_element_text()
    {
        return shape_element_text;
    }
    public void setShape_element_text(String shape_element_text)
    {
        this.shape_element_text = shape_element_text;
    }

    public int getShape_element_type()
    {
        return shape_element_type;
    }
    public void setShape_element_type(int shape_element_type)
    {
        this.shape_element_type = shape_element_type;
    }

}
