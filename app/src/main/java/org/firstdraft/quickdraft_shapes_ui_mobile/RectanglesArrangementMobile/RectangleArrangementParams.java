package org.firstdraft.quickdraft_shapes_ui_mobile.RectanglesArrangementMobile;

import android.util.Log;

import org.firstdraft.quickdraft_shapes_ui_mobile.ShapeUtility;
import org.firstdraft.quickdraft_shapes_ui_mobile.TransmitRectangleUtility;

public class RectangleArrangementParams {

    public static int rectangle_count = 0;

    /*public static String[] rect_string_array = {"Text 1","This is longer 2","3 Text",
            "This is longer 4"};*/
    public static String[] rect_string_array;

    public static int[] rect_width_array;
    public static boolean connector[];

    static int text_size;

    public static int rectangle_distance_array[];

    public static int rectangle_left_array[];
    public static int rectangle_top;

    public static int rect_width = 75;
    public static int rect_height = 75;

    public static final int rect_distance_initial = 47;
    public static final int rect_top_initial = 200;
    public static final int text_size_initial = 51;

    public static int rect_left_start = 15;

    static RectangleArrangementCallbacks arrangement_view;

    private static void init_for_string_array()
    {
        rect_string_array = new String[rectangle_count];

        for(int i=0;i<rectangle_count;i++)
        {
            String summary_text = RectangleArrangementUtility.
                                    get_summary_string
                                            (TransmitRectangleUtility.set_array[i].shape_text);

            rect_string_array[i] = summary_text;

        }

        rect_width_array = new int [rectangle_count];
        connector = new boolean [rectangle_count];

        for(int i=0;i<rectangle_count;i++)
        {
            float text_width = arrangement_view.get_text_measure(rect_string_array[i]);
            rect_width_array[i] = rect_width + (int)text_width;

            connector[i] = ShapeUtility.convert_connector_status
                    (TransmitRectangleUtility.set_array[i].connector);

        }

        if(RectangleArrangementUtility.initial_commit_done == false)
        {
            for (int i = 1; i < rectangle_count; i++) {
                rectangle_distance_array[i] = rect_distance_initial;

                rectangle_left_array[i] = rectangle_left_array[i - 1] +
                        (rect_width_array[i - 1] + rectangle_distance_array[i]);

            }
        }

    }

    public static void init()
    {

        if(RectangleArrangementUtility.initial_commit_done == true)
        {
            init_for_string_array();
            return;
        }

        rectangle_count = TransmitRectangleUtility.set_array.length;

        rectangle_distance_array = new int [rectangle_count];
        rectangle_left_array = new int [rectangle_count];

        rectangle_distance_array[0] = rect_distance_initial;

        rectangle_left_array[0] = rect_left_start;
        rectangle_top = rect_top_initial;

        for(int i=1;i<rectangle_count;i++)
        {
            rectangle_distance_array[i] = rect_distance_initial;

            rectangle_left_array[i] = rectangle_left_array[i - 1] +
                    (rect_width + rectangle_distance_array[i]);

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

        int final_x = rectangle_left_array[rect_id] + delta_x;

        Log.d("XY","delta_x = " + delta_x);

        if(rect_id != rectangle_count - 1 && delta_x > 0)
        {
            if(final_x + rect_width > rectangle_left_array[rect_id + 1])
            {
                final_x = rectangle_left_array[rect_id + 1] - rect_width;
            }
        }

        if(rect_id != 0 && delta_x < 0)
        {
            if(final_x < rectangle_left_array[rect_id - 1] + rect_width)
            {
                final_x = rectangle_left_array[rect_id - 1] + rect_width;
            }
        }

        rectangle_left_array[rect_id] = final_x;

    }

    public static void increment_rectangle_left_with_string(int rect_id, int delta_x)
    {
        if(rect_id == -1)
        {
            //No rectangle touched
            return;
        }

        int final_x = rectangle_left_array[rect_id] + delta_x;

        Log.d("XY","delta_x = " + delta_x);

        if(rect_id != rectangle_count - 1 && delta_x > 0)
        {
            if(final_x + rect_width_array[rect_id] > rectangle_left_array[rect_id + 1])
            {
                final_x = rectangle_left_array[rect_id + 1] - rect_width_array[rect_id];
            }
        }

        if(rect_id != 0 && delta_x < 0)
        {
            if(final_x < rectangle_left_array[rect_id - 1] + rect_width_array[rect_id - 1])
            {
                final_x = rectangle_left_array[rect_id - 1] + rect_width_array[rect_id - 1];
            }
        }

        rectangle_left_array[rect_id] = final_x;

    }

    public static void increment_rectangle_top(int delta_y)
    {
        int final_y = rectangle_top + delta_y;
        rectangle_top = final_y;
    }


}
