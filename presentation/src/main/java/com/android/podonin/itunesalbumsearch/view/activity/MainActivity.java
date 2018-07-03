package com.android.podonin.itunesalbumsearch.view.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.podonin.itunesalbumsearch.R;
import com.android.podonin.itunesalbumsearch.navigation.FragmentNavigator;
import com.android.podonin.itunesalbumsearch.presenter.MainActivityPresenter;
import com.android.podonin.itunesalbumsearch.view.MainActivityView;

public class MainActivity extends AppCompatActivity
        implements MainActivityView {

    private MainActivityPresenter mActivityPresenter;
    private FragmentNavigator mFragmentNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivityPresenter = new MainActivityPresenter(this);
        mFragmentNavigator = new FragmentNavigator(this);

        if (savedInstanceState == null) {
            mActivityPresenter.dispatchCreate();
        }
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

    @Nullable
    public FragmentNavigator getFragmentNavigator() {
        return mFragmentNavigator;
    }
}
