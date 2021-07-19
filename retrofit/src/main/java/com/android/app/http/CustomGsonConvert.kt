package com.android.app.http

import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonToken.END_DOCUMENT
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

internal class CustomGsonConvert(private val gson: Gson = Gson()) : Converter.Factory() {

    private val factory = GsonConverterFactory.create(gson)

    override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit): Converter<ResponseBody, *>? {
        if (((type as? ParameterizedType)?.rawType as? Class<*>) == ResponseResult::class.java) {
            return GsonResponseBodyConverter(gson, gson.getAdapter(TypeToken.get(type.actualTypeArguments[0])))
        }
        return factory.responseBodyConverter(type, annotations, retrofit)
    }

    private class GsonResponseBodyConverter<T>(
        private val gson: Gson,
        private val adapter: TypeAdapter<out T>
    ) : Converter<ResponseBody, ResponseResult<T>> {
        @Throws(IOException::class)
        override fun convert(value: ResponseBody): ResponseResult<T> {
            return try {
                val jsonReader = gson.newJsonReader(value.charStream())
                val result = adapter.read(jsonReader)
                if (jsonReader.peek() != END_DOCUMENT) {
                    throw JsonIOException("JSON document was not fully consumed.")
                }
                ResponseResult.success(result)
            } catch (e: Exception) {
                ResponseResult.failed(e)
            } finally {
                try {
                    value.close()
                } catch (e: Exception) {
                }
            }
        }
    }

}