package com.example.harmonichyperspace.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.example.harmonichyperspace.MainHomePage;
import com.example.harmonichyperspace.POJO.Album;
import com.example.harmonichyperspace.POJO.Albums;
import com.example.harmonichyperspace.POJO.Artist;
import com.example.harmonichyperspace.POJO.Artists;
import com.example.harmonichyperspace.POJO.SearchResults;
import com.example.harmonichyperspace.POJO.Track;
import com.example.harmonichyperspace.R;
import com.example.harmonichyperspace.adapters.AlbumsAdapter;
import com.example.harmonichyperspace.adapters.ArtistsAdapter;
import com.example.harmonichyperspace.adapters.TracksAdapter;
import com.example.harmonichyperspace.landing.signUp;

import java.util.List;

public class searchResults extends AppCompatActivity {
    
    private String mSearchQuery;
    private RecyclerView tracksReyclerView;
    private RecyclerView albumsRecyclerView;
    private RecyclerView artistsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search_results);

        tracksReyclerView = findViewById(R.id.tracksRecyclerView);
        albumsRecyclerView = findViewById(R.id.albumsRecyclerView);
        artistsRecyclerView = findViewById(R.id.artistsRecyclerView);
        
        //retrieving the search text, using shared preferences
        Intent intent = getIntent();
        SearchResults searchResults = (SearchResults) intent.getSerializableExtra("searchResult");
        
        //Access the search results and display them
        List<Track> tracks = searchResults.getTracks().getItems();
        List<Album> albums = searchResults.getAlbums().getItems();
        List<Artist> artists = searchResults.getArtists().getItems();

        //set up and display the tracks
        tracksReyclerView.setLayoutManager(new LinearLayoutManager(this));
        tracksReyclerView.setAdapter(new TracksAdapter(this, tracks));

        //set up and display the albums
        albumsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        albumsRecyclerView.setAdapter(new AlbumsAdapter(this, albums));

        //set up and display the artists
        artistsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        artistsRecyclerView.setAdapter(new ArtistsAdapter(this, artists));

    }

//    public static Intent intentFactory(Context context, int userId) {
//        Intent intent = new Intent(context, MainHomePage.class);
//        intent.putExtra(USER_ID_KEY, userId);
//        return intent;
//    }
}