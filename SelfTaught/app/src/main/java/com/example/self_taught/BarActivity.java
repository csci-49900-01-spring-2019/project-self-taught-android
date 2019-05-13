package com.example.self_taught;

import android.view.Menu;
import android.view.MenuInflater;

public class BarActivity extends BaseActivity {

    // Activity code here

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}