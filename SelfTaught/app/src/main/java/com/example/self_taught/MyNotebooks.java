package com.example.self_taught;

import android.app.ActionBar;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MyNotebooks extends AppCompatActivity {
    View view;

    int numOfNotebooks = 0;
    int x, y;

    private int status;
    String response;
    ViewGroup contain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mynotebooks);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        x = size.x;
        y = size.y;

        String toSend[] = new String[3];
        toSend[0] = "GET";
        toSend[1] = "https://www.api.selftaughtapp.com/vq/users/:user_id/notebooks";
        toSend[2] = "0";
        GetUrlContentTask getTask = new GetUrlContentTask();
        getTask.setContext(getApplicationContext());
        getTask.execute(toSend);
        Runnable r = new Runnable() {
            @Override
            public void run() {

            }
        };

        Handler h = new Handler();
        int tempInt = 0;
        while (getTask.getStat() == 0 || tempInt++ == 5) {
            h.postDelayed(r, 1000);
        }
        status = getTask.getStat();

        if (status == HttpsURLConnection.HTTP_OK) {
            response = getTask.getContent();
        } else {
        } //setup login error

        Log.d("Response", response);

        ArrayList<String> names = decipher(getNotes());


        ArrayList<ImageButton> options = addNote(x, y, names);
    }


    private String getNotes()
    {
        String output = "";

        return output;
    }

    private ArrayList<String> decipher(String input)
    {
        ArrayList<String> output = new ArrayList<String>();

        return output;
    }

    private ArrayList<ImageButton> addNote(int x, int y,ArrayList<String> names)
    {
        setContentView(R.layout.mynotes);
        LinearLayout noteImages = (LinearLayout) findViewById(R.id.MyNotesLinearLayoutImage);
        LinearLayout noteContent = (LinearLayout) findViewById(R.id.MyNotesLinearLayoutContent);
        ArrayList<ImageButton> output = new ArrayList<ImageButton>();

        for(int z = 0; z < names.size(); z++) {
            ImageButton imgView = new ImageButton(this);
            imgView.setId(z);
            imgView.setMaxHeight(y / 7);
            imgView.setMinimumHeight(y / 7);
            imgView.setImageResource(R.color.blue);

            output.add(imgView);

            GradientDrawable gradDraw = new GradientDrawable();
            gradDraw.setCornerRadius(5);
            gradDraw.setStroke(1, 0xFF000000);

            TextView toAdd = new TextView(this);
            //toAdd.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, y/5));
            toAdd.setHeight(y / 7);
            toAdd.setBackground(gradDraw);
            toAdd.setText(names.get(z));
            toAdd.setId(z + 100);
            noteImages.addView(imgView);
            noteContent.addView(toAdd);
        }
        return output;
    }
}
