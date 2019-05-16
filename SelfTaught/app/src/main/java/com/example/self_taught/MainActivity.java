package com.example.self_taught;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Authenticator;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends BarActivity {
    int x;
    int y;

    private int status;

    CookieManager cookieManager;
    CookieHandler cookieHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**SharedPreferences shared = getApplicationContext().getSharedPreferences(String.valueOf(R.string.MYPREF), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("Key", "term");
        editor.commit();**/

        //Getting the screen size
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        x = size.x;
        y = size.y;

        //Different Views set beforehand
        Button loginBtn = (Button) findViewById(R.id.LoginBtn);
        Button signUpBtn = (Button) findViewById(R.id.SignUpBtn);

        //Get all views
        //Consider doing this all in a function
        ConstraintLayout loginBox = (ConstraintLayout) findViewById(R.id.LoginBox);
        TextView usernameText = (TextView) findViewById(R.id.UsernameText);
        TextView passwordText = (TextView) findViewById(R.id.PasswordText);

        //Set sizes (modular [is that the right word?])
         loginBox.setMaxHeight(y/2);
         loginBox.setMinHeight(y/2);
         loginBox.setMaxWidth((3*x)/5);
         loginBox.setMinWidth((3*x)/5);
         usernameText.setHeight(y/16);
         usernameText.setWidth((3*x)/5);
         passwordText.setHeight(y/16);
         passwordText.setWidth((3*x)/5);
         loginBtn.setWidth((3*x)/10);
         loginBtn.setHeight(y/16);
        signUpBtn.setWidth((3*x)/10);
        signUpBtn.setHeight(y/16);

        loginBtn.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                TextView userName = (TextView) findViewById(R.id.UsernameText);
                TextView passWord = (TextView) findViewById(R.id.PasswordText);
                final String chckUser = "csci499class@gmail.com";//userName.getText().toString();
                final String chckPass = "password499";//passWord.getText().toString();
                boolean pass = true;

                if(chckUser.length() != 0 && chckPass.length() != 0 || pass) {

                    //Log.d("login", "Username: " + chckUser + "\nPassword: " + chckPass);

                    //Api code to check if these match with the Database

                    final String endPoint = "https://www.api.selftaughtapp.com/v1/users/sign_in";

                    String urls[] = new String[5];
                    urls[0] = "POST";
                    urls[1] = endPoint;
                    urls[2] = "2";
                    urls[3] = "email=csci499class@gmail.com";//chckUser;
                    urls[4] = "password=password499";//chckPass;
                    GetUrlContentTask login = new GetUrlContentTask();
                    login.setContext(getApplicationContext());
                    login.execute(urls);

                    Runnable r = new Runnable() {
                        @Override
                        public void run() {

                        }
                    };

                    Handler h = new Handler();
                    int tempInt = 0;
                    while (login.getStat() == 0 || tempInt++ == 5) {
                        h.postDelayed(r, 1000);
                    }
                    status = login.getStat();

                    Log.d("status", status + "");

                    if (status == HttpsURLConnection.HTTP_OK) {
                        Intent homeIntent = new Intent(getApplicationContext(), Home.class);
                        int at = chckUser.indexOf('@');
                        String username = chckUser.substring(0, at - 1);
                        homeIntent.putExtra("username", username);
                        startActivity(homeIntent);
                    } else {
                    } //setup login error

                }
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("https://www.selftaughtapp.com/users/sign_up"));
                startActivity(viewIntent);
            }
        });
    }


}

