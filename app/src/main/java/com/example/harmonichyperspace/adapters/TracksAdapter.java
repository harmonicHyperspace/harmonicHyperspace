package com.example.harmonichyperspace.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.harmonichyperspace.POJO.Track;
import com.example.harmonichyperspace.R;

import java.util.List;

public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.TrackViewHolder> {
    private Context context;
    private List<Track> tracks;

    public TracksAdapter(Context context, List<Track> tracks) {
        this.context = context;
        this.tracks = tracks;
    }

    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.track_item, parent, false);
        return new TrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackViewHolder holder, int position) {
        Track track = tracks.get(position);
        holder.trackName.setText(track.getName());

        if (track.getAlbum().getImages() != null && !track.getAlbum().getImages().isEmpty()) {
            String imageUrl = track.getAlbum().getImages().get(0).getUrl();
            Glide.with(context).load(imageUrl).into(holder.trackImage);
        } else {
            Glide.with(context).load(R.drawable.ic_launcher_background).into(holder.trackImage);
        }
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    public class TrackViewHolder extends RecyclerView.ViewHolder {
        private TextView trackName;
        private ImageView trackImage;

        public TrackViewHolder(@NonNull View itemView) {
            super(itemView);
            trackName = itemView.findViewById(R.id.track_name);
            trackImage = itemView.findViewById(R.id.track_image);
        }
    }
}
