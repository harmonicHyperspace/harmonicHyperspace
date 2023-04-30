package com.example.harmonichyperspace.information;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.harmonichyperspace.POJO.Album;
import com.example.harmonichyperspace.R;
import com.example.harmonichyperspace.background.SpotifyClient;
import com.example.harmonichyperspace.background.SpotifyService;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class albulmInformation extends AppCompatActivity {

    private RecyclerView tracksRecyclerView;
    private RecyclerView tracksAdapter;
    private RecyclerView reviewsRecyclerView;
    private RecyclerView reviewsAdapter;
    private ImageView albumImage;
    private TextView albumName;
    private TextView albumInfo;
    private String albumId;
    private SpotifyService spotifyService;
    private String accessToken;

    public albulmInformation(String accessToken){
        this.accessToken = accessToken;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_albulm_information);

        wireupDisplay();

        //retireve the access token from the intent
        accessToken = getIntent().getStringExtra("accessToken");

        //initialize the SpotifyService
        spotifyService = SpotifyClient.createSpotifyService(accessToken);

        //fetch the album information
        fetchAlbulmInformation(albumId);
    }

    private void wireupDisplay() {
        albumImage = findViewById(R.id.profileAlbumImage);
        albumName = findViewById(R.id.profileAlbumName);
        albumInfo = findViewById(R.id.profileAlbumInfo);

        //get the album id from the intent
        Intent intent = getIntent();
        albumId = intent.getStringExtra("albumId");
    }

    private void fetchAlbulmInformation(String albulmId) {
        Call<Album> call = spotifyService.getAlbum(albumId);
        call.enqueue(new Callback<Album>() {
            @Override
            public void onResponse(Call<Album> call, Response<Album> response) {
                if (response.isSuccessful()) {
                    Album album = response.body();
                    updateAlbumInformation(album);
                } else {
                    Log.d("AlbumInformation", "Error: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Album> call, Throwable t) {
                Log.d("AlbumInformation", "Error: " + t.getMessage());
            }
        });
    }

    private void updateAlbumInformation(Album album) {
        // Update the UI elements with the album information
        albumName.setText(album.getName());
        albumInfo.setText(album.getArtistName());

        // Load the album image using an image loading library (e.g., Glide or Picasso)
        Glide.with(this)
                .load(album.getImageUrl())
                .into(albumImage);
    }
}