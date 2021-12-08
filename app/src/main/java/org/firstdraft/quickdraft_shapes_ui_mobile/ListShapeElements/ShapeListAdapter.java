package org.firstdraft.quickdraft_shapes_ui_mobile.ListShapeElements;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.firstdraft.quickdraft_shapes_ui_mobile.CurrentShapeElement;
import org.firstdraft.quickdraft_shapes_ui_mobile.FinalizeRectangleActivity;
import org.firstdraft.quickdraft_shapes_ui_mobile.RectangleView;
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

        if(TransmitRectangleUtility.set_array == null)
        {
            return 0;
        }

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

            holder.shape_scale = (TextView) convertView.findViewById(R.id.shape_element_scale);
            holder.shape_text = (TextView) convertView.findViewById(R.id.shape_element_text);

            convertView.setTag(holder);

        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        ShapeElementModel current_element = shape_model_array
                .get(position);

        holder.shape_scale.setText(current_element.getShape_element_scale());
        holder.shape_text.setText(current_element.getShape_element_text());

        holder.editButton = (Button) convertView.findViewById(R.id.shape_edit_btn);

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                CurrentShapeElement.scaling_factor =
                        (float) RectangleView.base_width_current /
                                (float)RectangleView.RECTANGLE_BASE_WIDTH;

                //TransmitRectangleUtility.add_shape_element();

                RectangleView.s = "";
                RectangleView.connector = "";
                RectangleView.multiplication_factor = (float)1.0;

                RectangleView.base_width_current = RectangleView.RECTANGLE_BASE_WIDTH;
                RectangleView.base_height_current = RectangleView.RECTANGLE_BASE_HEIGHT;
                RectangleView.text_size_base = RectangleView.TEXT_BASE_SIZE;

                FinalizeRectangleActivity.mScaleFactor = (float)1.0;

                Intent intent = new Intent(context, FinalizeRectangleActivity.class);
                ShapeListUtility.sla_instance.startActivity(intent);

            }
        });

        return convertView;

    }

    private class ViewHolder {
        protected TextView shape_scale;
        protected TextView shape_text;

        protected Button editButton;
    }

}
