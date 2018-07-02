package com.android.podonin.itunesalbumsearch.view.fragment;

import android.os.Bundle;
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

import com.android.podonin.data.repository.RepositoryProvider;
import com.android.podonin.domain.interactor.albuminteractor.AlbumsUseCase;
import com.android.podonin.domain.model.Album;
import com.android.podonin.itunesalbumsearch.R;
import com.android.podonin.itunesalbumsearch.adapter.AlbumsRvAdapter;
import com.android.podonin.itunesalbumsearch.navigation.FragmentNavigator;
import com.android.podonin.itunesalbumsearch.presenter.AlbumsFragmentPresenter;
import com.android.podonin.itunesalbumsearch.view.AlbumsFragmentView;
import com.android.podonin.itunesalbumsearch.view.activity.ContainerActivity;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AlbumsFragment extends Fragment implements AlbumsFragmentView {

    public static AlbumsFragment newInstance() {
        return new AlbumsFragment();
    }

    @Nullable
    private FragmentNavigator mFragmentNavigator;
    private AlbumsFragmentPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private AlbumsRvAdapter mAlbumsRvAdapter;
    private SearchView mSearchView;
    private SpinKitView mSpinKitView;
    private TextView mNoItemsText;

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
        mFragmentNavigator = getFragmentNavigator();

        AlbumsUseCase useCase = new AlbumsUseCase(
                RepositoryProvider.provideRepository(), Schedulers.newThread(), AndroidSchedulers.mainThread());
        mPresenter = new AlbumsFragmentPresenter(this, useCase);
        mPresenter.dispatchCreate();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAlbumsRvAdapter = new AlbumsRvAdapter();
        mAlbumsRvAdapter.setAlbumClickListener(albumId -> mPresenter.onAlbumChoose(albumId));
        mRecyclerView.setAdapter(mAlbumsRvAdapter);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mPresenter.onSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onDestroyView() {
        mPresenter.dispatchDestroy();
        super.onDestroyView();
    }

    private FragmentNavigator getFragmentNavigator() {
        ContainerActivity activity = (ContainerActivity) getActivity();
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
    public void showNextAlbum(@NonNull Album album) {
        mAlbumsRvAdapter.addData(album);
    }

    @Override
    public void clearAlbums() {
        mAlbumsRvAdapter.clearData();
    }

    @Override
    public void goToDetails(@NonNull Integer albumId) {
        if (mFragmentNavigator == null) {
            return;
        }
        mFragmentNavigator.showAlbumDetailsFragment(albumId);
    }
}
