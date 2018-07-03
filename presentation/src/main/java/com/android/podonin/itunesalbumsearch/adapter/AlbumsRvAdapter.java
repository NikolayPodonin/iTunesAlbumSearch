package com.android.podonin.itunesalbumsearch.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.podonin.itunesalbumsearch.R;
import com.android.podonin.itunesalbumsearch.model.AlbumParcel;

import java.util.ArrayList;
import java.util.List;

public class AlbumsRvAdapter extends RecyclerView.Adapter<AlbumsRvAdapter.AlbumViewHolder> {

    public interface OnAlbumClickListener{
        void onAlbumClick(Integer albumId);
    }

    private List<AlbumParcel> mAlbums = new ArrayList<>();
    private OnAlbumClickListener mAlbumClickListener;

    public void setData(List<AlbumParcel> albums) {
        mAlbums = albums;
        notifyDataSetChanged();
    }

    public void addData(AlbumParcel album) {
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
        TextView mCurrency;
        AlbumParcel mAlbum;

        AlbumViewHolder(View itemView) {
            super(itemView);
            mContainer = itemView.findViewById(R.id.rv_item_album);
            mAlbumIcon = itemView.findViewById(R.id.album_icon_image_view);
            mAlbumName = itemView.findViewById(R.id.album_name_text_view);
            mArtistName = itemView.findViewById(R.id.artist_name_text_view);
            mTracksCount = itemView.findViewById(R.id.tracks_count_text_view);
            mPrice = itemView.findViewById(R.id.album_price_text_view);
            mCurrency = itemView.findViewById(R.id.album_currency_text_view);
        }

        void bind(AlbumParcel album) {
            mAlbum = album;
            if (mAlbumClickListener != null) {
                mContainer.setOnClickListener(v -> mAlbumClickListener.onAlbumClick(mAlbum.getAlbumId()));
            }
            if (mAlbum.getIcon60() != null) {
                mAlbumIcon.setImageBitmap(mAlbum.getIcon60());
            }
            mPrice.setText(mAlbum.getPrice());
            mTracksCount.setText(mAlbum.getTrackCount());
            mCurrency.setText(mAlbum.getCurrency());
            mAlbumName.setText(mAlbum.getAlbumName());
            mArtistName.setText(mAlbum.getArtistName());
        }
    }
}
