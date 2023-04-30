package com.example.harmonichyperspace.background;


import com.example.harmonichyperspace.POJO.Album;
import com.example.harmonichyperspace.POJO.SearchResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SpotifyService {
    @GET("search")
    Call<SearchResults> search(
            @Query("q") String query,
            @Query("type") String type
            );
    @GET("albums/{id}")
    Call<Album> getAlbum(
            @Path("id") String albumId
    );
}
