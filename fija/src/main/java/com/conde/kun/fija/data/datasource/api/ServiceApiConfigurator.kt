package com.conde.kun.fija.data.datasource.api

import android.content.Context
import com.conde.kun.core.domain.BaseError
import com.conde.kun.core.error.BusinessErrorException
import com.conde.kun.core.error.NoInternetException
import com.conde.kun.core.error.ServerErrorException
import com.conde.kun.core.util.isOnline
import com.conde.kun.fija.data.datasource.SettingsDataSource
import com.conde.kun.fija.data.entity.FactApi
import com.google.gson.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.*


class ServiceApiConfigurator(context: Context, settingsDataSource: SettingsDataSource) {

    val accessApi: AccessApi
    val factApi: FactApi
    val API_BASE_URL: String = "https://sakana.com.ar/api/"

    init {
        val gsonBuilder = GsonBuilder()

        gsonBuilder.registerTypeAdapter(Calendar::class.java, object : JsonDeserializer<Calendar> {
            override fun deserialize(
                json: JsonElement,
                typeOf: Type,
                context: JsonDeserializationContext
            ): Calendar {
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = json.asJsonPrimitive.asLong * 1000
                return calendar
            }
        })

        val okHttpClient = getOkHttpClient(context, settingsDataSource)

        val mononokeBuilder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .build()

        accessApi = mononokeBuilder.create(AccessApi::class.java)

        val factsBuilder = Retrofit.Builder()
            .baseUrl("http://104.131.111.67:8088/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .build()
        factApi = factsBuilder.create(FactApi::class.java)
    }

    fun getOkHttpClient(context: Context, settingsDataSource: SettingsDataSource): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor { chain ->
            if (!isOnline(context)) {
                throw NoInternetException()
            }

            val original = chain.request()
            val requestBuilder = original.newBuilder()
            settingsDataSource.getToken()?.let {
                requestBuilder.addHeader("Token", it)
            }

            chain.proceed(requestBuilder.build())

        }

        builder.addInterceptor { chain ->
            val request = chain.request()
            val response = chain.proceed(request)

            if (response.code() == 400) {
                val gson = Gson()

                response.body()?.let {
                    val error: BaseError
                    try {
                        error = gson.fromJson(it.charStream(), BaseError::class.java)
                    } catch (ex: Exception) {
                        throw ServerErrorException()
                    }
                    throw BusinessErrorException(error)
                }
            }

            response
        }

        return builder.build()
    }

}