package com.example.self_taught;

import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
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
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends BarActivity {
    int x;
    int y;
    URL obj;
    HttpURLConnection conn;
    CookieManager cManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

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

        //cManager = new CookieManager();
       // CookieHandler.setDefault(cManager);

        //Login to get to the home page
        loginBtn.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                TextView userName = (TextView) findViewById(R.id.UsernameText);
                TextView passWord = (TextView) findViewById(R.id.PasswordText);
                final String chckUser = userName.getText().toString();
                final String chckPass = passWord.getText().toString();
                Log.d("login", "Username: " + chckUser + "\nPassword: " + chckPass);

                //Api code to check if these match with the Database

                String endPoint= "https://www.selftaughtapp.com/users/sign_in";
                /**HttpCookie cookie = new HttpCookie("lang", "en");
                cookie.setDomain("selftaught.com");
                cookie.setPath("/users/sign_in");
                cookie.setVersion(0);
                try {
                    cManager.getCookieStore().add(new URI("https://www.selftaughtapp.com/"), cookie);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }**/

                //String endPoint = "http://google.com";

                String urls[] = new String[3];
                urls[0] = endPoint;
                //urls[1] = "user[username]=" + chckUser + "&user[password]=" + chckPass;
                urls[1] = "user[username]=testaccount&user[password]=password123";
                urls[2] = String.valueOf(android.util.Base64.encode(urls[1].getBytes(), 0));
                //new GetUrlContentTask().execute(urls);

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
    }


}





class GetUrlContentTask extends AsyncTask<String, Integer, String>
{
    protected String doInBackground(String... urls)
    {
        HttpsURLConnection connection;
        String content = "", line;
        String login = urls[1];
        String encode = urls[2];

        try {
            URL url = new URL(urls[0]);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", "Bearer " + encode);
            connection.setRequestMethod("POST");
            //connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            //For POST
            OutputStream os = connection.getOutputStream();
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            wr.write(login);
            wr.flush();
            wr.close();
            os.close();

            connection.connect();
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while((line = rd.readLine()) != null)
            {
                content += line + "\n";
            }
            connection.disconnect();

            url = new URL("https://www.selftaughtapp.com/notebooks/new");
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", "Bearer " + encode);
            connection.setRequestMethod("GET");
            //connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();
            rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while((line = rd.readLine()) != null) {
                content += line + "\n";
            }
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    protected void onProgressUpdate(Integer... progress)
    {

    }

    protected void onPostExecute(String result)
    {
        Log.d("Result", result);
    }
}