package com.android.podonin.data.repository;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Converter.Factory;
import retrofit2.Retrofit;

public class BitmapConverterFactory extends Factory {

    private BitmapConverterFactory() {}

    public static BitmapConverterFactory create() {
        return new BitmapConverterFactory();
    }

    @Nullable
    @Override
    public Converter<ResponseBody, Bitmap> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type == Bitmap.class) {
            return value -> BitmapFactory.decodeStream(value.byteStream());
        }
        return null;
    }

    @Nullable
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return null;
    }
}
