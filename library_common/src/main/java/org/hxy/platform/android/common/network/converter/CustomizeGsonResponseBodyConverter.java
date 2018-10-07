package org.hxy.platform.android.common.network.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import org.hxy.platform.android.common.config.CommonConfig;
import org.hxy.platform.android.common.util.LogUtil;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;


final class CustomizeGsonResponseBodyConverter
        <T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    CustomizeGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        try {
            if(CommonConfig.DEBUG){
                LogUtil.e(CommonConfig.APPLICATION_ID,value.string());
            }
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }
}
