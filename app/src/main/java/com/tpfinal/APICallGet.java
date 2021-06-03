package com.tpfinal;

import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APICallGet extends AsyncTask<String, Integer, String> {

    APIResponseCallback apiResponseCallback;

    public APICallGet(APIResponseCallback apiResponseCallback) {
        this.apiResponseCallback = apiResponseCallback;
    }

    @Override
    protected String doInBackground(String... urls) {
        StringBuilder result = new StringBuilder();
        URL url;
        HttpURLConnection urlConnection;

        try {
            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept-Encoding", "identity");

            int status = urlConnection.getResponseCode();

            if (status != HttpURLConnection.HTTP_OK) {
                return null;
            }

            long downloadSize = urlConnection.getContentLengthLong();

            if(downloadSize == 0) {
                return null;
            }

            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);

            int data = reader.read();
            while(data != -1) {
                char current = (char) data;
                result.append(current);
                data = reader.read();
            }

            return result.toString();

        } catch (Exception e) {
            e.printStackTrace();
            if(e.getMessage() != null)
                Log.e("ERROR", e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        apiResponseCallback.onAPIResponse(s);
    }
}
