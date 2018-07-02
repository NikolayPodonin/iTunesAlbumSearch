package com.android.podonin.data.mapping;

import com.android.podonin.data.entity.Result;
import com.android.podonin.domain.model.Album;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResultToAlbum {
    ResultToAlbum Instance = Mappers.getMapper(ResultToAlbum.class);

    Album getAlbum(Result result);


}
