package org.firstdraft.quickdraft_shapes_ui_mobile.RectanglesArrangementMobile;

public class RectangleArrangementUtility
{

    public static String get_summary_string(String complete_text)
    {
        if(complete_text.length() < 5)
        {
            return complete_text;
        }

        String first_four_chars = complete_text.substring(0,4);
        return first_four_chars + "..";
    }


    public static boolean is_rectangle_touched(int rectangle_id,
                                               int x,
                                               int y)
    {
        int rect_id_left = RectangleArrangementParams.rectangle_left_array[rectangle_id];
        int rect_id_top = RectangleArrangementParams.rectangle_top;

        //int rect_width = RectangleViewParams.rect_width;
        int rect_width = RectangleArrangementParams.rect_width_array[rectangle_id];
        int rect_height = RectangleArrangementParams.rect_height;

        boolean inside_x = false;
        boolean inside_y = false;

        if(x > rect_id_left && x < rect_id_left + rect_width)
        {
            inside_x = true;
        }

        if(y > rect_id_top && y < rect_id_top + rect_height)
        {
            inside_y = true;
        }

        if(inside_x == true && inside_y == true)
        {
            return true;
        }

        return false;

    }
    
    public static int get_touched_rectangle(int x,int y)
    {
        for(int i=0;i<RectangleArrangementParams.rectangle_count;i++)
        {
            int rectangle_id = i;
            if(is_rectangle_touched(rectangle_id,x,y) == true)
            {
                return rectangle_id;
            }
        }

        return -1;
    }

}
