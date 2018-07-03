package com.android.podonin.itunesalbumsearch.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.podonin.domain.model.Song;
import com.android.podonin.itunesalbumsearch.R;
import com.android.podonin.itunesalbumsearch.model.SongParcel;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SongsRvAdapter extends RecyclerView.Adapter<SongsRvAdapter.SongViewHolder> {

    private List<SongParcel> mSongs = new ArrayList<>();

    public void setData(List<SongParcel> songs) {
        mSongs = songs;
        notifyDataSetChanged();
    }

    public void addData(@NonNull SongParcel song) {
        mSongs.add(song);
        notifyItemChanged(mSongs.size() - 1);
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_item_song, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        holder.bind(mSongs.get(position));
    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    class SongViewHolder extends RecyclerView.ViewHolder {
        private TextView mTrackNumber;
        private TextView mTrackName;
        private TextView mTrackTime;

        SongViewHolder(View itemView) {
            super(itemView);
            mTrackNumber = itemView.findViewById(R.id.track_number_text_view);
            mTrackName = itemView.findViewById(R.id.track_name_text_view);
            mTrackTime = itemView.findViewById(R.id.track_time_text_view);
        }

        void bind(@NonNull SongParcel song) {
            mTrackNumber.setText(song.getTrackNumber());
            mTrackName.setText(song.getTrackName());
            mTrackTime.setText(song.getTrackTime());
        }
    }
}
