package com.example.harmonichyperspace.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.harmonichyperspace.POJO.Album;
import com.example.harmonichyperspace.R;
import com.example.harmonichyperspace.search.moretracksActivity;
import com.example.harmonichyperspace.search.reviewAlbulmPage;

import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_VIEW_TYPE_ALBUM = 0;
    private static final int ITEM_VIEW_TYPE_SHOW_MORE = 1;
    private static final int MAX_ITEMS = 3;
    private Context context;
    private List<Album> albums;

    public AlbumsAdapter(Context context, List<Album> albums) {
        this.context = context;
        this.albums = albums;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == ITEM_VIEW_TYPE_ALBUM) {
            view = LayoutInflater.from(context).inflate(R.layout.album_item, parent, false);
            return new AlbumViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.show_more_button, parent, false);
            return new ShowMoreViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == ITEM_VIEW_TYPE_ALBUM) {
            Album album = albums.get(position);
            AlbumViewHolder albumHolder = (AlbumViewHolder) holder;
            albumHolder.albumName.setText(album.getName());

            if (album.getImages() != null && !album.getImages().isEmpty()) {
                String imageUrl = album.getImages().get(0).getUrl();
                Glide.with(context).load(imageUrl).into(albumHolder.albumImage);
            } else {
                Glide.with(context).load(R.drawable.ic_launcher_background).into(albumHolder.albumImage);
            }
        } else {
            // Set onClickListener for the showMoreButton
            ShowMoreViewHolder showMoreHolder = (ShowMoreViewHolder) holder;
            showMoreHolder.showMoreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start the activity that displays more albums
                    Intent intent = new Intent(context, moretracksActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return Math.min(albums.size(), MAX_ITEMS) + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == Math.min(albums.size(), MAX_ITEMS)) {
            return ITEM_VIEW_TYPE_SHOW_MORE;
        } else {
            return ITEM_VIEW_TYPE_ALBUM;
        }
    }

    public static class ShowMoreViewHolder extends RecyclerView.ViewHolder {
        Button showMoreButton;

        public ShowMoreViewHolder(@NonNull View itemView) {
            super(itemView);
            showMoreButton = itemView.findViewById(R.id.btn_show_more);
        }
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder {
        private TextView albumName;
        private ImageView albumImage;

        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            albumName = itemView.findViewById(R.id.album_name);
            albumImage = itemView.findViewById(R.id.album_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start the activity that displays the album
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Album clickedAlbum = albums.get(position);

                        //get the data from the album and pass it to the next activity
                        String albumId = clickedAlbum.getId();
                        String albumName = clickedAlbum.getName();
                        String albumImage = clickedAlbum.getImages().get(0).getUrl();
                        String artistName = clickedAlbum.getArtists().get(0).getName();
                        String genre = "genre";

                        //create the intent
                        Intent intent = reviewAlbulmPage.intentFactory(context, albumId, albumName, artistName, genre, albumImage);

                        //start the activity
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
