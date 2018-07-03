package com.android.podonin.itunesalbumsearch.view.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.podonin.data.repository.RepositoryProvider;
import com.android.podonin.domain.interactor.albuminteractor.AlbumDetailsUseCase;
import com.android.podonin.itunesalbumsearch.R;
import com.android.podonin.itunesalbumsearch.adapter.SongsRvAdapter;
import com.android.podonin.itunesalbumsearch.model.AlbumParcel;
import com.android.podonin.itunesalbumsearch.model.SongParcel;
import com.android.podonin.itunesalbumsearch.presenter.AlbumDetailsFragmentPresenter;
import com.android.podonin.itunesalbumsearch.view.AlbumDetailsFragmentView;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AlbumDetailsFragment extends Fragment
        implements AlbumDetailsFragmentView {
    private static final String ALBUM_ID = "albumId";
    private static final String ALBUM_PARCEL = "AlbumDetailsFragment_albumParcel";
    private static final String ALBUM_SONGS = "AlbumDetailsFragment_albumSongs";

    public static AlbumDetailsFragment newInstance(int albumId) {
        Bundle args = new Bundle();
        args.putInt(ALBUM_ID, albumId);
        AlbumDetailsFragment fragment = new AlbumDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private int mAlbumId;
    private AlbumParcel mAlbumParcel;
    private List<SongParcel> mSongParcels = new ArrayList<>();

    private SpinKitView mSpinKitView;
    private TextView mNoItemsText;
    private LinearLayout mDetailsLayout;
    private AppCompatImageView mAlbumIcon;
    private TextView mAlbumName;
    private TextView mArtistName;
    private TextView mPrice;
    private TextView mCurrency;
    private TextView mReleaseDate;
    private TextView mCountry;
    private TextView mCopyright;
    private RecyclerView mRecyclerView;
    private SongsRvAdapter mSongsRvAdapter;

    private AlbumDetailsFragmentPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album_details, container, false);
        mSpinKitView = view.findViewById(R.id.spin_kit_view);
        mNoItemsText = view.findViewById(R.id.no_items_text_view);
        mDetailsLayout = view.findViewById(R.id.album_details_layout);
        mAlbumIcon = view.findViewById(R.id.album_icon_image_view);
        mAlbumName = view.findViewById(R.id.album_name_text_view);
        mArtistName = view.findViewById(R.id.artist_name_text_view);
        mPrice = view.findViewById(R.id.price_text_view);
        mCurrency = view.findViewById(R.id.currency_text_view);
        mReleaseDate = view.findViewById(R.id.date_text_view);
        mCountry = view.findViewById(R.id.country_text_view);
        mCopyright = view.findViewById(R.id.copyright_text_view);
        mRecyclerView = view.findViewById(R.id.songs_recycler_view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AlbumDetailsUseCase useCase = new AlbumDetailsUseCase(
                RepositoryProvider.provideRepository(), Schedulers.newThread(), AndroidSchedulers.mainThread());
        mPresenter = new AlbumDetailsFragmentPresenter(this, useCase);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mSongsRvAdapter = new SongsRvAdapter();
        mRecyclerView.setAdapter(mSongsRvAdapter);
        fragmentStateIdentify(savedInstanceState);
    }

    private void fragmentStateIdentify(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {                                           // Just opened window
            if (getArguments() == null) {                                           // Something completely wrong
                Objects.requireNonNull(getActivity()).onBackPressed();
                return;
            }
            mAlbumId = getArguments().getInt(ALBUM_ID);
            mPresenter.dispatchCreate(mAlbumId);
        } else {                                                                    // Configure changed
            mAlbumParcel = savedInstanceState.getParcelable(ALBUM_PARCEL);
            if (mAlbumParcel != null) {                                             // After data received from iTunes
                mSongParcels = savedInstanceState.getParcelableArrayList(ALBUM_SONGS);
                mPresenter.dispatchCreate(mAlbumParcel, mSongParcels);
            } else {                                                                // Before data received from iTunes
                mAlbumId = savedInstanceState.getInt(ALBUM_ID);
                mPresenter.dispatchCreate(mAlbumId);
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(ALBUM_PARCEL, mAlbumParcel);
        outState.putParcelableArrayList(ALBUM_SONGS, (ArrayList<? extends Parcelable>) mSongParcels);
        outState.putInt(ALBUM_ID, mAlbumId);
    }

    @Override
    public void onDestroyView() {
        mPresenter.dispatchDestroy();
        super.onDestroyView();
    }

    @Override
    public void showAlbumDetails(@NonNull AlbumParcel album) {
        mAlbumParcel = album;

        if (album.getIcon100() != null) mAlbumIcon.setImageBitmap(album.getIcon100());
        mAlbumName.setText(album.getAlbumName());
        mArtistName.setText(album.getArtistName());
        mPrice.setText(album.getPrice());
        mCurrency.setText(album.getCurrency());
        mReleaseDate.setText(album.getReleaseDate());
        mCountry.setText(album.getCountry());
        mCopyright.setText(album.getCopyright());

        mDetailsLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideAlbumDetails() {
        mDetailsLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showSongs(List<SongParcel> songs) {
        mSongsRvAdapter.setData(songs);
    }

    @Override
    public void showNextSong(@NonNull SongParcel song) {
        mSongParcels.add(song);
        mSongsRvAdapter.addData(song);
    }

    @Override
    public void showLoadingProcess() {
        mSpinKitView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingProcess() {
        mSpinKitView.setVisibility(View.GONE);
    }

    @Override
    public void showNoItemsText() {
        mNoItemsText.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoItemsText() {
        mNoItemsText.setVisibility(View.GONE);
    }

    @Override
    public void showConnectionError() {
        Toast.makeText(getContext(), R.string.connection_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showHttpError() {
        Toast.makeText(getContext(), R.string.http_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUnexpectedError() {
        Toast.makeText(getContext(), R.string.unexpected_error, Toast.LENGTH_SHORT).show();
    }
}
