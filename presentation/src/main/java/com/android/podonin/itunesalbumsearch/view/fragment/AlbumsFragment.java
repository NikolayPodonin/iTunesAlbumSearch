package com.android.podonin.itunesalbumsearch.view.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.podonin.data.repository.RepositoryProvider;
import com.android.podonin.domain.interactor.albuminteractor.AlbumsUseCase;
import com.android.podonin.domain.model.Album;
import com.android.podonin.itunesalbumsearch.R;
import com.android.podonin.itunesalbumsearch.adapter.AlbumsRvAdapter;
import com.android.podonin.itunesalbumsearch.model.AlbumParcel;
import com.android.podonin.itunesalbumsearch.navigation.FragmentNavigator;
import com.android.podonin.itunesalbumsearch.presenter.AlbumsFragmentPresenter;
import com.android.podonin.itunesalbumsearch.view.AlbumsFragmentView;
import com.android.podonin.itunesalbumsearch.view.activity.MainActivity;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AlbumsFragment extends Fragment implements AlbumsFragmentView {

    public static AlbumsFragment newInstance() {
        return new AlbumsFragment();
    }

    private static final String ALBUM_PARCELS = "AlbumsFragment_albumParcels";

    private List<AlbumParcel> mAlbumParcels = new ArrayList<>();

    @Nullable
    private FragmentNavigator mFragmentNavigator;
    private AlbumsFragmentPresenter mPresenter;

    private RecyclerView mRecyclerView;
    private AlbumsRvAdapter mAlbumsRvAdapter;
    private SpinKitView mSpinKitView;
    private TextView mNoItemsText;

    private SearchView mSearchView;
    private SearchView.OnQueryTextListener mQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            mPresenter.onSearch(query);
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_albums, container, false);
        mRecyclerView = view.findViewById(R.id.albums_recycler_view);
        mSearchView = view.findViewById(R.id.albums_search_view);
        mSpinKitView = view.findViewById(R.id.spin_kit_view);
        mNoItemsText = view.findViewById(R.id.no_items_text_view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            mAlbumParcels = savedInstanceState.getParcelableArrayList(ALBUM_PARCELS);
        }

        mFragmentNavigator = getFragmentNavigator();
        mSearchView.setOnQueryTextListener(mQueryTextListener);
        initRecyclerView();
        initPresenter();
    }

    private void initPresenter() {
        AlbumsUseCase useCase = new AlbumsUseCase(
                RepositoryProvider.provideRepository(), Schedulers.newThread(), AndroidSchedulers.mainThread());
        mPresenter = new AlbumsFragmentPresenter(this, useCase);
        mPresenter.dispatchCreate();
        mPresenter.onSavedAlbumsRestore(mAlbumParcels);
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAlbumsRvAdapter = new AlbumsRvAdapter();
        mAlbumsRvAdapter.setAlbumClickListener(albumId -> mPresenter.onAlbumChoose(albumId));
        mRecyclerView.setAdapter(mAlbumsRvAdapter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(ALBUM_PARCELS, (ArrayList<? extends Parcelable>) mAlbumParcels);
    }

    @Override
    public void onDestroyView() {
        mPresenter.dispatchDestroy();
        super.onDestroyView();
    }

    @Nullable
    private FragmentNavigator getFragmentNavigator() {
        MainActivity activity = (MainActivity) getActivity();
        if (activity == null) {
            return null;
        }
        return activity.getFragmentNavigator();
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

    @Override
    public void showAlbums(List<AlbumParcel> albums) {
        mAlbumsRvAdapter.setData(albums);
    }

    @Override
    public void showNextAlbum(@NonNull AlbumParcel album) {
        mAlbumParcels.add(album);
        mAlbumsRvAdapter.addData(album);
    }

    @Override
    public void clearAlbums() {
        mAlbumsRvAdapter.clearData();
    }

    @Override
    public void goToDetails(int albumId) {
        if (mFragmentNavigator == null) {
            return;
        }
        mFragmentNavigator.showAlbumDetailsFragment(albumId);
    }
}
