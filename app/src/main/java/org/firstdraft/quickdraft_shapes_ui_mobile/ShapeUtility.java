package org.firstdraft.quickdraft_shapes_ui_mobile;

import android.widget.CheckBox;

public class ShapeUtility {

    public static String get_connector_status(CheckBox connector)
    {
        /*if(connector.isChecked()){
            return "true";
        }
        return "false";*/

        return boolean_to_string(connector.isChecked());
    }

    public static String boolean_to_string(Boolean connector_status)
    {
        if(connector_status == true){
            return "true";
        }
        return "false";
    }

    public static Boolean convert_connector_status(String status)
    {
        if(status == null)
        {
            return false;
        }

        if(status.equals("true"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
