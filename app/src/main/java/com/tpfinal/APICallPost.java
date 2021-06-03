package com.tpfinal;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class APICallPost extends AsyncTask<String, Integer, String> {

    private final APIResponseCallback apiResponseCallback;

    private final StringKeyValuePairsConvertible body;

    public APICallPost(APIResponseCallback apiResponseCallback, StringKeyValuePairsConvertible body) {
        this.apiResponseCallback = apiResponseCallback;
        this.body = body;
    }

    @Override
    protected String doInBackground(String... urls) {
        StringBuilder result = new StringBuilder();
        URL url;
        HttpURLConnection urlConnection;

        try {
            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Accept-Encoding", "identity");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("Content-type", "application/json; utf8");
            urlConnection.setDoOutput(true);

            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(body.toKeyValuePairs());

            OutputStream outputStream = urlConnection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            outputStreamWriter.write(requestBody);
            outputStreamWriter.close();
            outputStream.close();

            urlConnection.connect();

            int status = urlConnection.getResponseCode();

            if (status != HttpURLConnection.HTTP_OK) {
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

