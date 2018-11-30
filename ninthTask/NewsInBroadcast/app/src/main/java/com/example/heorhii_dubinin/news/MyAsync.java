package com.example.heorhii_dubinin.news;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyAsync extends AsyncTask<String, Void, String> {

    public static final String TAG = "Async";

    private Listener mListener;

    public MyAsync(Listener listener) {
        mListener = listener;
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String json = null;

        try {
            URL url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                mListener.onError("CONNECTION FAILED");
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null)
                buffer.append(line + "\n");

            if (buffer.length() == 0) {
                mListener.onError("EMPTY_RESPONSE");
                return null;
            }

            json = buffer.toString();
        } catch (IOException e) {
            Log.e(TAG, "ERROR", e);
            mListener.onError(e.getMessage());
        } finally {
            if (urlConnection != null) urlConnection.disconnect();
            {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(TAG, "CLOSING_STREAM", e);
                        mListener.onError(e.getMessage());
                    }
                }
            }
            return json;
        }
    }

    public interface Listener {

        void onLoaded(String json);

        void onError(String message);
    }
}
