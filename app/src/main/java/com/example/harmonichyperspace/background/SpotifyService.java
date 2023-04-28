package com.example.harmonichyperspace.background;


import com.example.harmonichyperspace.POJO.SearchResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface SpotifyService {
    @GET("search")
    Call<SearchResults> search(
            @Header("Authorization") String authToken,
            @Query("q") String query,
            @Query("type") String type
            );
}
