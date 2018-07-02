package com.android.podonin.itunesalbumsearch.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.podonin.domain.model.Album;
import com.android.podonin.itunesalbumsearch.R;

import java.util.ArrayList;
import java.util.List;

public class AlbumsRvAdapter extends RecyclerView.Adapter<AlbumsRvAdapter.AlbumViewHolder> {

    public interface OnAlbumClickListener{
        void onAlbumClick(Integer albumId);
    }

    private List<Album> mAlbums = new ArrayList<>();
    private OnAlbumClickListener mAlbumClickListener;

    public void setData(List<Album> albums) {
        mAlbums = albums;
        notifyDataSetChanged();
    }

    public void addData(Album album) {
        mAlbums.add(album);
        notifyItemChanged(mAlbums.size() - 1);
    }

    public void clearData() {
        mAlbums.clear();
        notifyDataSetChanged();
    }

    public void setAlbumClickListener(OnAlbumClickListener albumClickListener) {
        mAlbumClickListener = albumClickListener;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_item_album, parent, false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        holder.bind(mAlbums.get(position));
    }

    @Override
    public int getItemCount() {
        return mAlbums.size();
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mContainer;
        AppCompatImageView mAlbumIcon;
        TextView mAlbumName;
        TextView mArtistName;
        TextView mTracksCount;
        TextView mPrice;
        Album mAlbum;

        AlbumViewHolder(View itemView) {
            super(itemView);
            mContainer = itemView.findViewById(R.id.rv_item_album);
            mAlbumIcon = itemView.findViewById(R.id.album_icon_image_view);
            mAlbumName = itemView.findViewById(R.id.album_name_text_view);
            mArtistName = itemView.findViewById(R.id.artist_name_text_view);
            mTracksCount = itemView.findViewById(R.id.tracks_count_text_view);
            mPrice = itemView.findViewById(R.id.album_price_text_view);
        }

        @SuppressLint("SetTextI18n")
        void bind(Album album) {
            mAlbum = album;
            if (mAlbumClickListener != null) {
                mContainer.setOnClickListener(v -> mAlbumClickListener.onAlbumClick(mAlbum.getCollectionId()));
            }
            if (mAlbum.getArtwork60() != null) {
                mAlbumIcon.setImageBitmap(mAlbum.getArtwork60());
            }
            if (mAlbum.getTrackCount() != null) {
                mTracksCount.setText(mAlbum.getTrackCount().toString());
            }
            if (mAlbum.getCollectionPrice() != null) {
                mPrice.setText(mAlbum.getCollectionPrice().toString());
            }
            mAlbumName.setText(mAlbum.getCollectionName());
            mArtistName.setText(mAlbum.getArtistName());
        }
    }
}
