//package com.example.harmonichyperspace.adapters;
//
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.example.harmonichyperspace.POJO.Album;
//import com.example.harmonichyperspace.R;
////import com.example.harmonichyperspace.databinding.PopularAlbumsBinding;
//
//import java.util.List;
//
//public class PopularAlbumsAdapter extends RecyclerView.Adapter<PopularAlbumsAdapter.ViewHolder> {
//    private List<Album> mAlbums;
//
//    public PopularAlbumsAdapter(List<Album> albums) {
//        mAlbums = albums;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        PopularAlbumsBinding binding = PopularAlbumsBinding
//                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
//        return new ViewHolder(binding);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Album album = mAlbums.get(position);
//        holder.bind(album);
//    }
//
//    @Override
//    public int getItemCount() {
//        return mAlbums.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        private PopularAlbumsBinding binding;
//
//        public ViewHolder(@NonNull PopularAlbumsBinding binding) {
//            super(binding.getRoot());
//            this.binding = binding;
//        }
//
//        public void bind(Album album) {
//            Glide.with(binding.albumCover.getContext())
//                    .load(album.getImageUrl())
//                    .placeholder(R.drawable.ic_launcher_background)
//                    .into(binding.albumCover);
//
//            binding.albumTitle.setText(album.getName());
//            binding.albumArtist.setText(album.getArtistName());
//        }
//    }
//}
