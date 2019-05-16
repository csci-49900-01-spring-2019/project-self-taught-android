package com.example.self_taught;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.prefs.Preferences;

public class BaseActivity extends AppCompatActivity {
    // Activity code here
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Log.d("item", item.getItemId()+"");
        Log.d("about us id", R.id.about_us_id+"");
        switch (item.getItemId()) {

            case R.id.about_us_id:
                Intent aboutIntent = new Intent(getApplicationContext(), AboutUs.class);
                startActivity(aboutIntent);
                break;
            case R.id.contact_us_id:
                //showHelp();
                Intent contactIntent = new Intent(getApplicationContext(), ContactUs.class);
                startActivity(contactIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}