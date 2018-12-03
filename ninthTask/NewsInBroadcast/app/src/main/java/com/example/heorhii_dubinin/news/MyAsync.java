package com.example.heorhii_dubinin.news;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyAsync extends AsyncTask<String, Integer, String> {

    private static final String LOG_TAG = MyAsync.class.getSimpleName();
    private IResultListener listener;

    public MyAsync(IResultListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String json = null;

        try {
            URL url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                listener.onError("ERROR");
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null)
                buffer.append(line + "\n");

            if (buffer.length() == 0) {
                listener.onError("ERROR");
                return null;
            }
            json = buffer.toString();
        } catch (IOException e) {
            Log.e(LOG_TAG, "ERROR", e);
            listener.onError(e.getMessage());
        } finally {
            if (urlConnection != null) urlConnection.disconnect();
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "ERROR", e);
                    listener.onError(e.getMessage());
                }
            }
        }
        return json;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (result == null) {
            return;
        }
        listener.onResult(result);
    }

    public interface IResultListener {
        void onResult(String result);

        void onError(String error);
    }
}
