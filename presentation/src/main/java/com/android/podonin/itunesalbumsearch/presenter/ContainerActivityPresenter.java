package com.android.podonin.itunesalbumsearch.presenter;

import com.android.podonin.itunesalbumsearch.view.ContainerActivityView;

public class ContainerActivityPresenter {
    private ContainerActivityView mLayoutView;

    public ContainerActivityPresenter(ContainerActivityView layoutView) {
        mLayoutView = layoutView;
    }

    public void dispatchCreate() {
        mLayoutView.showAlbumsFragment();
    }


    public void dispatchDestroy() {
        mLayoutView = null;
    }
}
