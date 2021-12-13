package org.firstdraft.quickdraft_shapes_ui_mobile.RectanglesArrangementMobile;

import android.util.Log;

import org.firstdraft.quickdraft_shapes_ui_mobile.SelectShape.SelectShapeUtility;
import org.firstdraft.quickdraft_shapes_ui_mobile.ShapeUtility;
import org.firstdraft.quickdraft_shapes_ui_mobile.TransmitRectangleUtility;

public class ShapeArrangementParams {

    public static int shape_count = 0;

    /*public static String[] rect_string_array = {"Text 1","This is longer 2","3 Text",
            "This is longer 4"};*/
    public static String[] shape_string_array;

    public static int[] shape_width_array;
    public static boolean connector[];

    static int text_size;

    public static int shape_distance_array[];

    public static int shape_width = 75;
    public static int shape_height = 75;

    public static final int shape_distance_initial = 47;
    public static final int shape_top_initial = 200;
    public static final int text_size_initial = 51;
    public static int shape_left_start_initial = 15;

    public static int shape_left_array[];
    public static int shape_top = shape_top_initial;
    public static int shape_left_start = shape_left_start_initial;

    public static int shape_type[];

    static ShapeArrangementCallbacks arrangement_view;

    private static void init_for_string_array()
    {
        shape_string_array = new String[shape_count];

        for(int i = 0; i< shape_count; i++)
        {
            String summary_text = ShapeArrangementUtility.
                                    get_summary_string
                                            (TransmitRectangleUtility.set_array[i].shape_text);

            shape_string_array[i] = summary_text;

        }

        shape_width_array = new int [shape_count];
        connector = new boolean [shape_count];

        for(int i = 0; i< shape_count; i++)
        {
            float text_width = arrangement_view.get_text_measure(shape_string_array[i]);
            shape_width_array[i] = shape_width + (int)text_width;

            connector[i] = ShapeUtility.convert_connector_status
                    (TransmitRectangleUtility.set_array[i].connector);

        }

        //if(ShapeArrangementUtility.initial_commit_done == false)
        //{
            for (int i = 1; i < shape_count; i++) {

                /*if(i > ShapeArrangementUtility.last_commit_index)
                {
                    rectangle_distance_array[i] = rect_distance_initial;
                }*/

                shape_left_array[i] = shape_left_array[i - 1] +
                        (shape_width_array[i - 1] + shape_distance_array[i-1]);

            }
        //}

    }

    public static void init()
    {

        /*if(ShapeArrangementUtility.initial_commit_done == true)
        {
            init_for_string_array();
            return;
        }*/

        shape_count = TransmitRectangleUtility.set_array.length;

        shape_distance_array = new int [shape_count];
        shape_left_array = new int [shape_count];
        shape_type = new int [shape_count];

        //rectangle_distance_array[0] = rect_distance_initial;
        if(ShapeArrangementUtility.initial_commit_done == false)
        {

            shape_distance_array[0] = shape_distance_initial;
            shape_left_array[0] = shape_left_start_initial;
            shape_top = shape_top_initial;
            //shape_type[0] = SelectShapeUtility.SHAPE_RECTANGLE;
        }
        else
        {
            shape_distance_array[0] = (int)
                    (TransmitRectangleUtility.set_array[0].distance_mf
                            * shape_distance_initial);

            //Parameterize later
            shape_left_array[0] = shape_left_start;

            shape_top =
                    (int)(shape_top_initial * TransmitRectangleUtility.vertical_mf);

        }

        shape_type[0] = SelectShapeUtility.string_to_shape_type
                (TransmitRectangleUtility.set_array[0].shape_type);

        //rectangle_left_array[0] = rect_left_start;
        //rectangle_top = rect_top_initial;

        for(int i = 1; i< shape_count; i++)
        {
            if(i > ShapeArrangementUtility.last_commit_index)
            {
                shape_distance_array[i] = shape_distance_initial;
            }
            else
            {
                shape_distance_array[i] = (int)
                        (TransmitRectangleUtility.set_array[i].distance_mf
                                                * shape_distance_initial);

            }

            shape_type[i] = SelectShapeUtility.string_to_shape_type
                    (TransmitRectangleUtility.set_array[i].shape_type);

            /*rectangle_left_array[i] = rectangle_left_array[i - 1] +
                    (rect_width + rectangle_distance_array[i-1]);*/

        }

        init_for_string_array();
    }

    public static void increment_rectangle_left(int rect_id, int delta_x)
    {
        if(rect_id == -1)
        {
            //No rectangle touched
            return;
        }

        int final_x = shape_left_array[rect_id] + delta_x;

        Log.d("XY","delta_x = " + delta_x);

        if(rect_id != shape_count - 1 && delta_x > 0)
        {
            if(final_x + shape_width > shape_left_array[rect_id + 1])
            {
                final_x = shape_left_array[rect_id + 1] - shape_width;
            }
        }

        if(rect_id != 0 && delta_x < 0)
        {
            if(final_x < shape_left_array[rect_id - 1] + shape_width)
            {
                final_x = shape_left_array[rect_id - 1] + shape_width;
            }
        }

        shape_left_array[rect_id] = final_x;

    }

    public static void increment_rectangle_left_with_string(int rect_id, int delta_x)
    {
        if(rect_id == -1)
        {
            //No rectangle touched
            return;
        }

        int final_x = shape_left_array[rect_id] + delta_x;

        Log.d("XY","delta_x = " + delta_x);

        if(rect_id != shape_count - 1 && delta_x > 0)
        {
            if(final_x + shape_width_array[rect_id] > shape_left_array[rect_id + 1])
            {
                final_x = shape_left_array[rect_id + 1] - shape_width_array[rect_id];
            }
        }

        if(rect_id != 0 && delta_x < 0)
        {
            if(final_x < shape_left_array[rect_id - 1] + shape_width_array[rect_id - 1])
            {
                final_x = shape_left_array[rect_id - 1] + shape_width_array[rect_id - 1];
            }
        }

        shape_left_array[rect_id] = final_x;
        if(rect_id == 0)
        {
            shape_left_start = final_x;
        }

    }

    public static void increment_rectangle_top(int delta_y)
    {
        int final_y = shape_top + delta_y;
        shape_top = final_y;
    }

}
