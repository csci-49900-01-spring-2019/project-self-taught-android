package com.example.self_taught;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Home extends BarActivity {

    int x;
    int y;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        loadFragment(new HomeFragmentOne());

        SharedPreferences shared = getApplicationContext().getSharedPreferences(String.valueOf(R.string.MYPREF), Context.MODE_PRIVATE);
        Log.d("Shared", shared.getString("Key", "Fail"));


        /**TextView userText = (TextView) findViewById(R.id.HomeUsernameDsply);
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

         homeFrameLayout.setLayoutParams(new ConstraintLayout.LayoutParams(x, y - (y/7)));
         homeConLayout.setLayoutParams(new ConstraintLayout.LayoutParams(x, y/7));

         /**ViewGroup.LayoutParams params = homeFrameLayout.getLayoutParams();
         params.height = (y-(y/7));
         params.height = (x);
         homeFrameLayout.setLayoutParams(params);

         homeConLayout.setMaxWidth(x);
         homeConLayout.setMinWidth(x);
         homeConLayout.setMaxHeight(y/7);
         homeConLayout.setMinHeight(y/7);

         Log.d("width", x + "");
         Log.d("height", y +"");

         //homeFrameLayout.setLayoutParams(new FrameLayout.LayoutParams(y, (x-(x/5))));


         //Starting menu for home
         loadFragment(new HomeFragmentOne());

         // First home page menu option
         homeMenuOne.setOnClickListener(new View.OnClickListener()
         {

         @Override public void onClick(View v) {
         loadFragment(new HomeFragmentOne());
         }
         });

         //Second home page menu option
         homeMenuTwo.setOnClickListener(new View.OnClickListener()
         {

         @Override public void onClick(View v) {
         loadFragment(new HomeFragmentTwo());
         }
         });

         //Third home page menu option
         homeMenuThree.setOnClickListener(new View.OnClickListener()
         {

         @Override public void onClick(View v) {
         loadFragment(new HomeFragmentThree());
         }
         });

         //Fourth home page menu option
         homeMenuFour.setOnClickListener(new View.OnClickListener()
         {

         @Override public void onClick(View v) {
         loadFragment(new HomeFragmentFour());
         }
         });**/
    }
/*
    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.search_item:
                // do your code
                return true;
            case R.id.upload_item:
                // do your code
                return true;
            case R.id.copy_item:
                // do your code
                return true;
            case R.id.print_item:
                // do your code
                return true;
            case R.id.share_item:
                // do your code
                return true;
            case R.id.bookmark_item:
                // do your code
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    */

    /*@Override

    public void onBackPressed()

    {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
        finish();
    }
*/
    private void loadFragment(HomeFragmentOne frag)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.HomeMenuFragLayout, frag);
        fragmentTransaction.commit();
    }
}


