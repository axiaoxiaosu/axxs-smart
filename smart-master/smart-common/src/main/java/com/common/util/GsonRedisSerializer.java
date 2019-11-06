package com.common.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.List;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * 存入arraylist类型 取出时无法确定其类型 ???
 */
@Deprecated
public class GsonRedisSerializer<T> implements RedisSerializer<T> {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private final Type dataType;

    private final Gson gson;

    public GsonRedisSerializer(Type dataType) {
        this.dataType = dataType;
        this.gson = new Gson();
    }

    public GsonRedisSerializer(Type dataType, Gson gson) {
        this.dataType = dataType;
        this.gson = gson;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t != null) {
            if (t instanceof List) {
                String json = gson.toJson(t, new TypeToken<List>() {
                }.getType());
                return json.getBytes(DEFAULT_CHARSET);
            }

            String json = gson.toJson(t, t.getClass());
            return json.getBytes(DEFAULT_CHARSET);
        }
        return null;
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes != null) {
            String json = new String(bytes, DEFAULT_CHARSET);
            return gson.fromJson(json, dataType);
        }
        return null;
    }

}