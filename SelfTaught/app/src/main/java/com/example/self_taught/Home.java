package com.example.self_taught;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        String userName = getIntent().getStringExtra("username");
        TextView userText = (TextView) findViewById(R.id.HomeUsernameDsply);
        userText.setText(userName);

        //Starting menu for home
        loadFragment(new HomeFragmentOne());

        // First home page menu option
        TextView homeMenuOne = (TextView) findViewById(R.id.HomeMenuOptOne);
        homeMenuOne.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                loadFragment(new HomeFragmentOne());
            }
        });

        //Second home page menu option
        TextView homeMenuTwo = (TextView) findViewById(R.id.HomeMenuOptTwo);
        homeMenuTwo.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                loadFragment(new HomeFragmentTwo());
            }
        });

        //Third home page menu option
        TextView homeMenuThree = (TextView) findViewById(R.id.HomeMenuOptThree);
        homeMenuThree.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                loadFragment(new HomeFragmentThree());
            }
        });

        //Fourth home page menu option
        TextView homeMenuFour = (TextView) findViewById(R.id.HomeMenuOptFour);
        homeMenuFour.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                loadFragment(new HomeFragmentFour());
            }
        });
    }

    private void loadFragment(Fragment frag)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.homeFrameLayout, frag);
        fragmentTransaction.commit();
    }
}


