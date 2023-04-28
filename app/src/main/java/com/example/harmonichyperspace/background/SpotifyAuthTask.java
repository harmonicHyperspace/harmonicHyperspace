package com.example.harmonichyperspace.background;

import android.os.AsyncTask;
import android.util.Log;

public class SpotifyAuthTask extends AsyncTask<Void, Void, String> {
    private SpotifyAuthListener listener;

    public SpotifyAuthTask(SpotifyAuthListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            String accessToken = SpotifyAuth.getAccessToken();
            return accessToken;
        } catch (Exception e) {
            Log.e("SpotifyAuthTask", "Error fetching access token", e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(String token) {
        if(listener != null) {
            listener.onAccessTokenReceived(token);
        }
    }

    public interface SpotifyAuthListener {
        void onAccessTokenReceived(String token);
    }
}
