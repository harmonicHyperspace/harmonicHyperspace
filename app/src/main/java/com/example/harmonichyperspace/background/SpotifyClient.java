package com.example.harmonichyperspace.background;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpotifyClient {
    private static final String BASE_URL = "https://api.spotify.com/v1/";

    public static SpotifyService createSpotifyService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(SpotifyService.class);
    }
}
