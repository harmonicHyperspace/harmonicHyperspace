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
import com.example.harmonichyperspace.POJO.Artist;
import com.example.harmonichyperspace.R;
import com.example.harmonichyperspace.search.artist_information;
import com.example.harmonichyperspace.search.moretracksActivity;

import java.util.List;

public class ArtistsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_VIEW_TYPE_ARTIST = 0;
    private static final int ITEM_VIEW_TYPE_SHOW_MORE = 1;
    private static final int MAX_ITEMS = 2;
    private Context context;
    private List<Artist> artists;

    public ArtistsAdapter(Context context, List<Artist> artists) {
        this.context = context;
        this.artists = artists;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == ITEM_VIEW_TYPE_ARTIST) {
            view = LayoutInflater.from(context).inflate(R.layout.artist_item, parent, false);
            return new ArtistsAdapter.ArtistViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.show_more_button, parent, false);
            return new ArtistsAdapter.ShowMoreViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == ITEM_VIEW_TYPE_ARTIST) {
            Artist artist = artists.get(position);
            ArtistViewHolder artistHolder = (ArtistViewHolder) holder;
            artistHolder.artistName.setText(artist.getName());

            if (artist.getImages() != null && !artist.getImages().isEmpty()) {
                String imageUrl = artist.getImages().get(0).getUrl();
                Glide.with(context).load(imageUrl).into(artistHolder.artistImage);
            } else {
                Glide.with(context).load(R.drawable.ic_launcher_background).into(artistHolder.artistImage);
            }
        } else {
            // Set onClickListener for the showMoreButton
            ArtistsAdapter.ShowMoreViewHolder showMoreHolder = (ArtistsAdapter.ShowMoreViewHolder) holder;
            showMoreHolder.showMoreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start the activity that displays more tracks
                    Intent intent = new Intent(context, moretracksActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return Math.min(artists.size(), MAX_ITEMS) + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == Math.min(artists.size(), MAX_ITEMS)) {
            return ITEM_VIEW_TYPE_SHOW_MORE;
        } else {
            return ITEM_VIEW_TYPE_ARTIST;
        }
    }

    public static class ShowMoreViewHolder extends RecyclerView.ViewHolder {
        Button showMoreButton;

        public ShowMoreViewHolder(@NonNull View itemView) {
            super(itemView);
            showMoreButton = itemView.findViewById(R.id.btn_show_more);
        }
    }

    public class ArtistViewHolder extends RecyclerView.ViewHolder {
        private TextView artistName;
        private ImageView artistImage;

        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);
            artistName = itemView.findViewById(R.id.artist_name);
            artistImage = itemView.findViewById(R.id.artist_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start the activity that displays more tracks
                    Intent intent = new Intent(context, artist_information.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}
