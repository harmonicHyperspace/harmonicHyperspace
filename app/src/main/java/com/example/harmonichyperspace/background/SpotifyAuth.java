package com.example.harmonichyperspace.background;

import android.util.Base64;
import android.util.Log;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SpotifyAuth {
    private static final String TOKEN_URL = "https://accounts.spotify.com/api/token";
    private static final String CLIENT_ID = "ee49bc8b3bf04954b844e3c800152aa7";
    private static final String CLIENT_SECRET = "bb5846f2e0f140c79365aa226d06db77";

    public static String getAccessToken() {
        Log.d("SpotifyAuth", "getAccessToken: " + TOKEN_URL);
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials");

        String credentials = CLIENT_ID + ":" + CLIENT_SECRET;
        Log.d("SpotifyAuth", "getAccessToken: " + credentials);
        String auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

        Request request = new Request.Builder()
                .url(TOKEN_URL)
                .post(body)
                .addHeader("Authorization", auth)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String json = response.body().string();
                JSONObject jsonObject = new JSONObject(json);
                String accessToken = jsonObject.getString("access_token");
                Log.d("SpotifyAuth", "Access token: " + accessToken);
                return accessToken;
            } else {
                Log.e("SpotifyAuth", "Error fetching access token: " + response);
                return null;
            }
        } catch (Exception e) {
            Log.e("SpotifyAuth", "Error fetching access token", e);
            return null;
        }
    }
}
