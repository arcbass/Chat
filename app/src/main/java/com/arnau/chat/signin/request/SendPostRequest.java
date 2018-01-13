package com.arnau.chat.signin.request;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Arnau on 11/01/2018.
 */

public class SendPostRequest extends AsyncTask<String,Void,String> {

    private String loginUrl;
    private String registerUrl;
    private HashMap<String, String> params;
    URL url = null;

    public SendPostRequest(HashMap<String, String> params, URL url){
        super();
        this.url = url;
        this.params = params;
    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            JSONObject postDataParams = new JSONObject();

            for(HashMap.Entry<String, String> entry : params.entrySet()) {
                String key = entry.getKey();
                String parameter = entry.getValue().toString();
                postDataParams.put(key, parameter);
                Log.i(entry.getKey(), entry.getValue().toString());
            }


            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000 /*milliseconds*/);
            conn.setConnectTimeout(15000 /*milliseconds*/);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();

            int responseCode = conn.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String line = "";

                while((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                in.close();
                Log.i("response", sb.toString());
                return sb.toString();

            } else {
                Log.i("response", "false: " + responseCode);
                return new String("false: " + responseCode);
            }

        } catch (Exception e){
            Log.i("exception", "Exception: " + e.getMessage());
            return new String ("Exception: " + e.getMessage());
        }

    }


    public String getPostDataString(JSONObject params) throws JSONException, UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> it = params.keys();
        while (it.hasNext()) {
            String key = it.next();
            Object value = params.get(key);

            if(first) first = false;
            else result.append("&");
            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
        }
        return result.toString();
    }
}
