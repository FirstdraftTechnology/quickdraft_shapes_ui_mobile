package org.firstdraft.quickdraft_shapes_ui_mobile.ShapesArrangementMobile;

import org.firstdraft.quickdraft_shapes_ui_mobile.TransmitShapeGroupUtility;

public class ShapeArrangementUtility
{

    /*public static int committed_id = 0;*/
    public static boolean initial_commit_done = false;
    public static int last_commit_index = 0;

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
        int rect_id_left = ShapeArrangementParams.shape_left_array[rectangle_id];
        int rect_id_top = ShapeArrangementParams.shape_top;

        //int rect_width = RectangleViewParams.rect_width;
        int rect_width = ShapeArrangementParams.shape_width_array[rectangle_id];
        int rect_height = ShapeArrangementParams.shape_height;

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
        for(int i = 0; i< ShapeArrangementParams.shape_count; i++)
        {
            int rectangle_id = i;
            if(is_rectangle_touched(rectangle_id,x,y) == true)
            {
                return rectangle_id;
            }
        }

        return -1;
    }

    public static void after_committing_arrangement()
    {
        for(int i = 1; i < ShapeArrangementParams.shape_count; i++)
        {
            ShapeArrangementParams.shape_distance_array[i - 1]
                    = ShapeArrangementParams.shape_left_array[i]
                    - ShapeArrangementParams.shape_width_array[i-1]
                    - ShapeArrangementParams.shape_left_array[i - 1];

            float new_distance_mf
                    = (float) ShapeArrangementParams.shape_distance_array[i - 1]
                    / (float) ShapeArrangementParams.shape_distance_initial;
            TransmitShapeGroupUtility.update_distance_mf(i-1,new_distance_mf);

        }

        ShapeArrangementUtility.initial_commit_done = true;
        last_commit_index = ShapeArrangementParams.shape_count - 1;

        TransmitShapeGroupUtility.vertical_mf = (float) ShapeArrangementParams.shape_top /
                (float) ShapeArrangementParams.shape_top_initial;
        TransmitShapeGroupUtility.horizontal_mf = (float) ShapeArrangementParams.shape_left_start/
                (float) ShapeArrangementParams.shape_left_start_initial;
    }

}
