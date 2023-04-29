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
import com.example.harmonichyperspace.POJO.Track;
import com.example.harmonichyperspace.R;
import com.example.harmonichyperspace.search.moretracksActivity;
import com.example.harmonichyperspace.search.reviewTrackPage;

import java.util.List;

public class TracksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Track> tracks;
    private static final int ITEM_VIEW_TYPE_TRACK = 0;
    private static final int ITEM_VIEW_TYPE_SHOW_MORE = 1;
    private static final int MAX_ITEMS = 3;

    public TracksAdapter(Context context, List<Track> tracks) {
        this.context = context;
        this.tracks = tracks;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == ITEM_VIEW_TYPE_TRACK) {
            view = LayoutInflater.from(context).inflate(R.layout.track_item, parent, false);
            return new TrackViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.show_more_button, parent, false);
            return new ShowMoreViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == ITEM_VIEW_TYPE_TRACK) {
            Track track = tracks.get(position);
            TrackViewHolder trackHolder = (TrackViewHolder) holder;
            trackHolder.trackName.setText(track.getName());

            if (track.getAlbum().getImages() != null && !track.getAlbum().getImages().isEmpty()) {
                String imageUrl = track.getAlbum().getImages().get(0).getUrl();
                Glide.with(context).load(imageUrl).into(trackHolder.trackImage);
            } else {
                Glide.with(context).load(R.drawable.ic_launcher_background).into(trackHolder.trackImage);
            }
        } else {
            // Set onClickListener for the showMoreButton
            ShowMoreViewHolder showMoreHolder = (ShowMoreViewHolder) holder;
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
        return Math.min(tracks.size(), MAX_ITEMS) + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == Math.min(tracks.size(), MAX_ITEMS)) {
            return ITEM_VIEW_TYPE_SHOW_MORE;
        } else {
            return ITEM_VIEW_TYPE_TRACK;
        }
    }

    public class TrackViewHolder extends RecyclerView.ViewHolder {
        private TextView trackName;
        private ImageView trackImage;

        public TrackViewHolder(@NonNull View itemView) {
            super(itemView);
            trackName = itemView.findViewById(R.id.track_name);
            trackImage = itemView.findViewById(R.id.track_image);

            // Set onClickListener for the track
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        Track clickedTrack = tracks.get(position);

                        // Get the data of the selected song
                        String trackId = clickedTrack.getId(); // Assuming you have a method called getId() in your Track class
                        String trackName = clickedTrack.getName();
                        String artistName = clickedTrack.getArtists().get(0).getName();
                        String trackThumbnail = clickedTrack.getAlbum().getImages().get(0).getUrl();

                        // Use the intentFactory method to create the intent
                        Intent intent = reviewTrackPage.intentFactory(v.getContext(), trackId, trackName, artistName, trackThumbnail);

                        // Start the reviewTrackPage activity
                        v.getContext().startActivity(intent);
                    }
                }
            });
        }
    }

    public static class ShowMoreViewHolder extends RecyclerView.ViewHolder {
        Button showMoreButton;

        public ShowMoreViewHolder(@NonNull View itemView) {
            super(itemView);
            showMoreButton = itemView.findViewById(R.id.btn_show_more);
        }
    }
}
