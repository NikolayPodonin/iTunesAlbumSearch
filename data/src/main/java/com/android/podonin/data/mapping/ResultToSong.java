package com.android.podonin.data.mapping;

import com.android.podonin.data.entity.Result;
import com.android.podonin.domain.model.Song;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResultToSong {
    ResultToSong Instance = Mappers.getMapper(ResultToSong.class);

    Song getSong(Result result);
}
