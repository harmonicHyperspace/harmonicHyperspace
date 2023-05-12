package com.example.harmonichyperspace.background;

import com.example.harmonichyperspace.POJO.NewReleasesResponse;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpotifyClient {
    private static final String BASE_URL = "https://api.spotify.com/v1/";
    public static SpotifyService createSpotifyService(String accessToken) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new AccessTokenInterceptor(accessToken))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(SpotifyService.class);
    }
    public static class AccessTokenInterceptor implements Interceptor{
        private String accessToken;

        public AccessTokenInterceptor(String accessToken){
            this.accessToken = accessToken;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder()
                    .addHeader("Authorization", "Bearer " + accessToken);
            return chain.proceed(builder.build());
        }
    }
}
