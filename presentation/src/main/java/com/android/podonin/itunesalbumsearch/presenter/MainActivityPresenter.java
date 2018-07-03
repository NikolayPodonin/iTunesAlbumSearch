package com.android.podonin.itunesalbumsearch.presenter;

import com.android.podonin.itunesalbumsearch.view.MainActivityView;

public class MainActivityPresenter {
    private MainActivityView mLayoutView;

    public MainActivityPresenter(MainActivityView layoutView) {
        mLayoutView = layoutView;
    }

    public void dispatchCreate() {
        mLayoutView.showAlbumsFragment();
    }


    public void dispatchDestroy() {
        mLayoutView = null;
    }
}
