package com.android.podonin.itunesalbumsearch.view.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
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

import com.android.podonin.data.repository.RepositoryProvider;
import com.android.podonin.domain.interactor.albuminteractor.AlbumDetailsUseCase;
import com.android.podonin.domain.model.Song;
import com.android.podonin.itunesalbumsearch.R;
import com.android.podonin.itunesalbumsearch.adapter.SongsRvAdapter;
import com.android.podonin.itunesalbumsearch.presenter.AlbumDetailsFragmentPresenter;
import com.android.podonin.itunesalbumsearch.view.AlbumDetailsFragmentView;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.Objects;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AlbumDetailsFragment extends Fragment
        implements AlbumDetailsFragmentView {
    private static final String ALBUM_ID = "albumId";

    public static AlbumDetailsFragment newInstance(@NonNull Integer albumId) {
        Bundle args = new Bundle();
        args.putInt(ALBUM_ID, albumId);
        AlbumDetailsFragment fragment = new AlbumDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

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
        if (getArguments() == null) {
            Objects.requireNonNull(getActivity()).onBackPressed();
            return;
        }
        int albumId = getArguments().getInt(ALBUM_ID);
        mPresenter.dispatchCreate(albumId);
    }

    @Override
    public void onDestroyView() {
        mPresenter.dispatchDestroy();
        super.onDestroyView();
    }

    @Override
    public void showAlbumDetails(@Nullable Bitmap icon, @Nullable String albumName, @Nullable String artistName,
                                 @Nullable String price, @Nullable String currency, @Nullable String releaseDate,
                                 @Nullable String country, @Nullable String copyright) {
        if (icon != null) mAlbumIcon.setImageBitmap(icon);
        if (albumName != null) mAlbumName.setText(albumName);
        if (artistName != null) mArtistName.setText(artistName);
        if (price != null) mPrice.setText(price);
        if (currency != null) mCurrency.setText(currency);
        if (releaseDate != null) mReleaseDate.setText(releaseDate);
        if (country != null) mCountry.setText(country);
        if (copyright != null) mCopyright.setText(copyright);
        mDetailsLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideAlbumDetails() {
        mDetailsLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showNextSong(Song song) {
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
}
