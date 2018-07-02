package com.android.podonin.data.repository;

public class RepositoryProvider {
    private RepositoryProvider() {}

    private static AlbumsRepositoryImpl sAlbumsRepository;

    public static AlbumsRepositoryImpl provideRepository() {
        if (sAlbumsRepository == null) {
            sAlbumsRepository = new AlbumsRepositoryImpl();
        }
        return sAlbumsRepository;
    }
}
