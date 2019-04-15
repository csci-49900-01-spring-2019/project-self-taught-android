package com.example.self_taught;

import android.content.Intent;
import android.graphics.Point;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int x;
    int y;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Getting the screen size
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        x = size.x;
        y = size.y;

        //Different Views set beforehand
        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        Button signUpBtn = (Button) findViewById(R.id.signUpBtn);

        //Login to get to the home page
        loginBtn.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                TextView userName = (TextView) findViewById(R.id.usernameText);
                TextView passWord = (TextView) findViewById(R.id.passwordText);
                String chckUser = userName.getText().toString();
                String chckPass = passWord.getText().toString();
                Log.d("login", "Username: " + chckUser + "\nPassword: " + chckPass);

                //Api code to check if these match with the Database

                Intent homeIntent = new Intent(getApplicationContext(), Home.class);
                homeIntent.putExtra("username", chckUser);
                startActivity(homeIntent);
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(homeIntent);
            }
        });



        //Set sizes for layouts
       /** ConstraintLayout loginBox = (ConstraintLayout) findViewById(R.id.LoginBox);
        loginBox.setMaxHeight(y/2);
        loginBox.setMinHeight(y/2);
        loginBox.setMaxWidth((3*x)/5);
        loginBox.setMinWidth((3*x)/5);
        userName.setHeight(y/16);
        userName.setWidth((3*x)/5);
        passWord.setHeight(y/16);
        passWord.setWidth((3*x)/5);**/
    }
}
