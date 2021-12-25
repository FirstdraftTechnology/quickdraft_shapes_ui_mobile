package org.firstdraft.draw_retrieve_caller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.firstdraft.quickdraft_shapes_ui_mobile.UserFileNameActivity;

public class Draw_Retrieve_Caller_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_retrieve_caller);

        addButtonListener();

    }

    public void addButtonListener()
    {
        Context context = this;

        Button launch_draw = (Button) findViewById
                (R.id.Launch_Draw);

        launch_draw.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0)
            {

                Intent intent = new Intent(context,
                        UserFileNameActivity.class);

                startActivity(intent);

            }
        });
    }

}