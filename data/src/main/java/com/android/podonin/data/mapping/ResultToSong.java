package com.android.podonin.data.mapping;

import com.android.podonin.data.entity.Result;
import com.android.podonin.domain.model.Song;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Interface for map {@link Result} to {@link Song} with MapStruct.
 */
@Mapper
public interface ResultToSong {
    ResultToSong Instance = Mappers.getMapper(ResultToSong.class);

    Song getSong(Result result);
}
