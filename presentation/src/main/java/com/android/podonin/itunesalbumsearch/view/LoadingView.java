package com.android.podonin.itunesalbumsearch.view;

/**
 * Interface for view that should load something.
 */
interface LoadingView {
    void showLoadingProcess();
    void hideLoadingProcess();
    void showNoItemsText();
    void hideNoItemsText();
    void showConnectionError();
    void showHttpError();
    void showUnexpectedError();
}
