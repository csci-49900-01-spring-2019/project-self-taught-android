package com.example.self_taught;

import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
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

    private String token;
    private String client;
    private int status;
    private int temp;

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
                final String chckUser = userName.getText().toString();
                final String chckPass = passWord.getText().toString();
                Log.d("login", "Username: " + chckUser + "\nPassword: " + chckPass);

                //Api code to check if these match with the Database

                final String endPoint= "https://www.api.selftaughtapp.com/v1/users/sign_in";

                String urls[] = new String[3];
                urls[0] = endPoint;
                urls[1] = chckUser;
                urls[2] = chckPass;
                GetUrlContentTask login = new GetUrlContentTask();
                login.execute(urls);
                while(login.getNext() == 0){}
                token = login.getToken();
                client = login.getClient();
                status = login.getStat();

                Log.d("status", status + "");
                Log.d("client", client);
                Log.d("token", token);
                if(status == HttpsURLConnection.HTTP_OK) {
                    Intent homeIntent = new Intent(getApplicationContext(), Home.class);
                    homeIntent.putExtra("username", chckUser);
                    startActivity(homeIntent);
                }
                else{} //setup login error
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

    private String header = "";
    private String client = "";
    private int status = 0;
    private int time = 0;

    public String getToken()
    {
        return header;
    }

    public String getClient()
    {
        return client;
    }

    public int getStat()
    {
        Log.d("statusDuring", status + "");
        return status;
    }

    public int getNext()
    {
        return time;
    }

    protected String doInBackground(String... urls)
    {
        time = 0;
        HttpsURLConnection connection;
        String content = "", line;
        //String login = "email=" + urls[1] + "&password=" + urls[2];
        String login = "email=testaccount@gmail.com&password=password123";

        try {
            URL url = new URL(urls[0]);
            connection = (HttpsURLConnection) url.openConnection();
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

            status = connection.getResponseCode();

            if(status == HttpURLConnection.HTTP_OK) {
                header = connection.getHeaderField("access-token");
                client = connection.getHeaderField("client");
            }

            Log.d("Header", header);
            connection.disconnect();
            time = 1;
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