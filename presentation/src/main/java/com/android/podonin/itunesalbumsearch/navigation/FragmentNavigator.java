package com.android.podonin.itunesalbumsearch.navigation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.android.podonin.itunesalbumsearch.R;
import com.android.podonin.itunesalbumsearch.view.fragment.AlbumDetailsFragment;
import com.android.podonin.itunesalbumsearch.view.fragment.AlbumsFragment;

import java.lang.ref.WeakReference;

/**
 * Simple Navigator between fragments.
 */
public class FragmentNavigator {
    private WeakReference<AppCompatActivity> mActivityWeakReference;

    public FragmentNavigator(@NonNull AppCompatActivity activity) {
        mActivityWeakReference = new WeakReference<>(activity);
    }

    @Nullable
    private FragmentManager getFragmentManager() {
        AppCompatActivity activity = mActivityWeakReference.get();
        if (activity == null) {
            return null;
        }
        return activity.getSupportFragmentManager();
    }

    private void showFragment(@NonNull Fragment fragment, @NonNull String tag) {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager == null) {
            return;
        }
        fragmentManager.beginTransaction()
                .replace(R.id.container_fragment, fragment, tag)
                .addToBackStack(null)
                .commit();
    }

    private void showFragment(@NonNull Fragment fragment) {
        String tag = fragment.getClass().getName();
        showFragment(fragment, tag);
    }

    public void showAlbumsFragment() {
        showFragment(AlbumsFragment.newInstance());
    }

    public void showAlbumDetailsFragment(@NonNull int albumId) {
        showFragment(AlbumDetailsFragment.newInstance(albumId));
    }
}
