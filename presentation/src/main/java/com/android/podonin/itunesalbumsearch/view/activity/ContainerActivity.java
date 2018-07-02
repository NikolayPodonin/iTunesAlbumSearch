package com.android.podonin.itunesalbumsearch.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.podonin.itunesalbumsearch.R;
import com.android.podonin.itunesalbumsearch.navigation.FragmentNavigator;
import com.android.podonin.itunesalbumsearch.presenter.ContainerActivityPresenter;
import com.android.podonin.itunesalbumsearch.view.ContainerActivityView;

public class ContainerActivity extends AppCompatActivity
        implements ContainerActivityView {

    private ContainerActivityPresenter mActivityPresenter;
    private FragmentNavigator mFragmentNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        mActivityPresenter = new ContainerActivityPresenter(this);
        mFragmentNavigator = new FragmentNavigator(this);

        mActivityPresenter.dispatchCreate();
    }

    @Override
    protected void onDestroy() {
        mActivityPresenter.dispatchDestroy();
        super.onDestroy();
    }

    @Override
    public void showAlbumsFragment() {
        mFragmentNavigator.showAlbumsFragment();
    }

    public FragmentNavigator getFragmentNavigator() {
        return mFragmentNavigator;
    }
}
