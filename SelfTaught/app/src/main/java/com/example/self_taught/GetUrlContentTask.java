package com.example.self_taught;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class GetUrlContentTask extends AsyncTask <String, Integer, String>{
    private int status = 0;
    private Context context;
    private String content = "";

    public void setContext(Context context)
    {
        this.context = context;
    }

    public int getStat()
    {
        return status;
    }

    public String getContent()
    {
        return content;
    }

    protected String doInBackground(String... urls)
    {
        //preparing necessary objects
        HttpsURLConnection connection;
        content = "";
        String line;
        String callType = urls[0];
        String urlStr = urls[1];
        String toSend = "";

        if(callType.equalsIgnoreCase("POST"))
        {
            int arraySize =  Integer.parseInt(urls[2]);
            SharedPreferences shared = context.getSharedPreferences(String.valueOf(R.string.MYPREF), Context.MODE_PRIVATE);
            int i = 3;
            boolean prior = false;
            if(shared.getString("id", null) != null && shared.getString("uid", null) != null && shared.getString("client", null) != null && shared.getString("access-token", null) != null)
            {
                toSend += "uid" + shared.getString("uid", null) + "&client" + shared.getString("client", null) + "&access-token";
                prior = true;
            }
            if(urls[i] != null)
                if(prior)
                    toSend += "&" + urls[i++];
                else
                    toSend += urls[i++];

            for(i = i; i < (arraySize + 3); i++)
                toSend += "&" + urls[i++];
            try {
                //To initiate the https connection
                URL url = new URL(urlStr);
                connection = (HttpsURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);

                Log.d("Send", toSend);

                OutputStream os = connection.getOutputStream();
                BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                wr.write(toSend);
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
                    SharedPreferences.Editor editor = shared.edit();
                    editor.putString("uid", connection.getHeaderField("uid"));
                    editor.putString("access-token", connection.getHeaderField("access-token"));
                    editor.putString("client", connection.getHeaderField("client"));

                    int idstart = content.lastIndexOf(':');
                    int idend = content.lastIndexOf("\"");
                    String idStr = content.substring(idstart+2, idend);

                    Log.d("body", content);
                    Log.d("id", idstart + " - " + idend);

                    editor.putString("id", idStr);
                    editor.commit();
                    Log.d("id", shared.getString("id", ":user_id"));
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(callType.equalsIgnoreCase("GET"))
            {
                try {
                    SharedPreferences shared = context.getSharedPreferences(String.valueOf(R.string.MYPREF), Context.MODE_PRIVATE);
                    if(shared.getString("uid", null) != null && shared.getString("client", null) != null && shared.getString("access-token", null) != null)
                        toSend += "uid=" + shared.getString("uid", null) + "&client=" + shared.getString("client", null) + "&access-token=" + shared.getString("access-token", null);
                    URL url = new URL(urlStr + "?" + toSend);
                    connection = (HttpsURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);

                    /**OutputStream os = connection.getOutputStream();
                    BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    wr.write(toSend);
                    wr.flush();
                    wr.close();
                    os.close();**/

                    connection.connect();
                    BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    while((line = rd.readLine()) != null)
                    {
                        content += line + "\n";
                    }

                    status = connection.getResponseCode();
                    if(status == HttpURLConnection.HTTP_OK) {
                        SharedPreferences.Editor editor = shared.edit();
                        editor.putString("uid", connection.getHeaderField("uid"));
                        editor.putString("access-token", connection.getHeaderField("access-token"));
                        editor.putString("client", connection.getHeaderField("client"));
                        editor.putString("id", connection.getHeaderField("id"));
                        editor.commit();
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if(callType.equalsIgnoreCase("PUT"))
                {
                    int arraySize =  Integer.parseInt(urls[2]);
                    SharedPreferences shared = context.getSharedPreferences(String.valueOf(R.string.MYPREF), Context.MODE_PRIVATE);
                    int i = 3;
                    boolean prior = false;
                    if(shared.getString("uid", null) != null && shared.getString("client", null) != null && shared.getString("access-token", null) != null)
                    {
                        toSend += "uid" + shared.getString("uid", null) + "&client" + shared.getString("client", null) + "&access-token";
                        prior = true;
                    }
                    if(urls[i] != null)
                        if(prior)
                            toSend += "&" + urls[i++];
                        else
                            toSend += urls[i++];

                    for(i = i; i < (arraySize + 3); i++)
                        toSend += "&" + urls[i++];
                    try {
                        //To initiate the https connection
                        URL url = new URL(urlStr);
                        connection = (HttpsURLConnection) url.openConnection();
                        connection.setRequestMethod("PUT");
                        connection.setConnectTimeout(5000);
                        connection.setReadTimeout(5000);

                        Log.d("Send", toSend);

                        OutputStream os = connection.getOutputStream();
                        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                        wr.write(toSend);
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
                            SharedPreferences.Editor editor = shared.edit();
                            editor.putString("uid", connection.getHeaderField("uid"));
                            editor.putString("access-token", connection.getHeaderField("access-token"));
                            editor.putString("client", connection.getHeaderField("client"));
                            editor.putString("id", connection.getHeaderField("id"));
                            editor.commit();
                        }

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if(callType.equalsIgnoreCase("DELETE"))
                    {
                        try {
                            int arraySize =  Integer.parseInt(urls[2]);
                            int i = 3;

                            SharedPreferences shared = context.getSharedPreferences(String.valueOf(R.string.MYPREF), Context.MODE_PRIVATE);
                            if(shared.getString("uid", null) != null && shared.getString("client", null) != null && shared.getString("access-token", null) != null)
                                toSend += "uid=" + shared.getString("uid", null) + "&client=" + shared.getString("client", null) + "&access-token=" + shared.getString("access-token", null);
                            for(i = i; i < (arraySize + 3); i++)
                                toSend += "&" + urls[i++];
                            URL url = new URL(urlStr + "?" + toSend);
                            connection = (HttpsURLConnection) url.openConnection();
                            connection.setRequestMethod("DELETE");
                            connection.setConnectTimeout(5000);
                            connection.setReadTimeout(5000);

                            /**OutputStream os = connection.getOutputStream();
                             BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                             wr.write(toSend);
                             wr.flush();
                             wr.close();
                             os.close();**/

                            connection.connect();
                            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                            while((line = rd.readLine()) != null)
                            {
                                content += line + "\n";
                            }

                            status = connection.getResponseCode();
                            if(status == HttpURLConnection.HTTP_OK) {
                                SharedPreferences.Editor editor = shared.edit();
                                editor.putString("uid", connection.getHeaderField("uid"));
                                editor.putString("access-token", connection.getHeaderField("access-token"));
                                editor.putString("client", connection.getHeaderField("client"));
                                editor.putString("id", connection.getHeaderField("id"));
                                editor.commit();
                            }

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            return content;

/**
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
                Map<String, List<String>> headerFields = connection.getHeaderFields();
                List<String> cookiesHeader = headerFields.get("Set-Cookie");

                URI uri = new URI("https://www.api.selftaughtapp.com/v1");

                if(cookiesHeader != null)
                    for(String cookie : cookiesHeader)
                        cookieManager.getCookieStore().add(null, HttpCookie.parse(cookie).get(0));

            }

            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return content;**/
    }

    protected void onProgressUpdate(Integer... progress)
    {

    }

    protected void onPostExecute(String result)
    {
        Log.d("Result", result);
    }
}
