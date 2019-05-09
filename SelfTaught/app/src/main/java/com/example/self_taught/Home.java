package com.example.self_taught;

import android.content.Intent;
import android.graphics.Point;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    int x;
    int y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        String userName = getIntent().getStringExtra("username");
        TextView userText = (TextView) findViewById(R.id.HomeUsernameDsply);
        userText.setText(userName);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        x = size.x;
        y = size.y;

        TextView homeMenuOne = (TextView) findViewById(R.id.HomeMenuOptOne);
        TextView homeMenuTwo = (TextView) findViewById(R.id.HomeMenuOptTwo);
        TextView homeMenuThree = (TextView) findViewById(R.id.HomeMenuOptThree);
        TextView homeMenuFour = (TextView) findViewById(R.id.HomeMenuOptFour);
        FrameLayout homeFrameLayout = (FrameLayout) findViewById(R.id.HomeFrameLayout);
        ConstraintLayout homeConLayout = (ConstraintLayout) findViewById(R.id.HomeMenuConstraint);

        homeConLayout.setMaxWidth(x);
        homeConLayout.setMinWidth(x);
        homeConLayout.setMaxHeight(y/2);
        homeConLayout.setMinHeight(y/2);

        Log.d("width", x + "");
        Log.d("height", y +"");

        //homeFrameLayout.setLayoutParams(new FrameLayout.LayoutParams(y, (x-(x/5))));


        //Starting menu for home
        loadFragment(new HomeFragmentOne());

        // First home page menu option
        homeMenuOne.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                loadFragment(new HomeFragmentOne());
            }
        });

        //Second home page menu option
        homeMenuTwo.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                loadFragment(new HomeFragmentTwo());
            }
        });

        //Third home page menu option
        homeMenuThree.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                loadFragment(new HomeFragmentThree());
            }
        });

        //Fourth home page menu option
        homeMenuFour.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                loadFragment(new HomeFragmentFour());
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
        finish();
    }

    private void loadFragment(Fragment frag)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.HomeFrameLayout, frag);
        fragmentTransaction.commit();
    }
}


