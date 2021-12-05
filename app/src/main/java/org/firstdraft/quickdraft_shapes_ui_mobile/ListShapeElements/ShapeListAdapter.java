package org.firstdraft.quickdraft_shapes_ui_mobile.ListShapeElements;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.firstdraft.quickdraft_shapes_ui_mobile.TransmitRectangleUtility;

import org.firstdraft.draw_transmit_shapes.R;

import java.util.ArrayList;

public class ShapeListAdapter extends BaseAdapter
{

    private Context context;
    public static ArrayList<ShapeElementModel> shape_model_array;

    public ShapeListAdapter(ArrayList<ShapeElementModel> customDirFilesArray,
                                Context context) {
        shape_model_array = customDirFilesArray;
        this.context = context;
    }

    public int getCount()
    {

        return TransmitRectangleUtility.set_array.length;

    }

    public Object getItem(int position)
    {

        return TransmitRectangleUtility.set_array[position].shape_text;

    }

    public long getItemId(int position)
    {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {

            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.shape_element_layout,
                    null, true);

            holder.viewText = (TextView) convertView.findViewById(R.id.dir_element_view);
            convertView.setTag(holder);

        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        ShapeElementModel current_element = shape_model_array
                .get(position);

        holder.viewText.setText(current_element.getShape_element_value());

        return convertView;

    }

    private class ViewHolder {
        //protected ImageView element_icon;
        protected TextView viewText;
    }

}
