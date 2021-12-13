package org.firstdraft.quickdraft_shapes_ui_mobile.ListShapeElements;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.firstdraft.quickdraft_shapes_ui_mobile.TransmitShapeGroupUtility;

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

        if(TransmitShapeGroupUtility.set_array == null)
        {
            return 0;
        }

        return TransmitShapeGroupUtility.set_array.length;

    }

    public Object getItem(int position)
    {

        return TransmitShapeGroupUtility.set_array[position].shape_text;

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

            holder.shape_element_icon =
                    (ImageView) convertView.findViewById(R.id.shape_image_element);

            holder.shape_scale = (TextView) convertView.findViewById(R.id.shape_element_scale);
            holder.shape_text = (TextView) convertView.findViewById(R.id.shape_element_text);

            convertView.setTag(holder);

        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        ShapeElementModel current_element = shape_model_array
                .get(position);

        ShapeListUtility.set_element_icon(current_element.getShape_element_type(),
                holder.shape_element_icon);

        holder.shape_scale.setText(current_element.getShape_element_scale());
        holder.shape_text.setText(current_element.getShape_element_text());

        holder.editButton = (Button) convertView.findViewById(R.id.shape_edit_btn);

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                TransmitShapeGroupUtility.launch_edit_shape_element
                        (ShapeListUtility.sla_instance, context, position);

            }
        });

        return convertView;

    }

    private class ViewHolder {
        protected ImageView shape_element_icon;

        protected TextView shape_scale;
        protected TextView shape_text;

        protected Button editButton;
    }

}
