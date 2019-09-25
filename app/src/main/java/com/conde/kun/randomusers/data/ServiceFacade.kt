package com.conde.kun.randomusers.data

import android.util.Log
import com.conde.kun.randomusers.data.entity.UserPageEntity
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.*

class ServiceFacade {

    lateinit var serviceApi: ServiceApi
    //val API_BASE_URL: String = "https://randomuser.me"
    val API_BASE_URL: String = "http://192.168.0.17:8088"
    val USER_AMOUNT_PER_PAGE: Int = 50

    init {
        Log.d("JLK", "constructor de service facade worked")
        val gsonBuilder = GsonBuilder()

        gsonBuilder.registerTypeAdapter(Calendar::class.java, object: JsonDeserializer<Calendar> {
            override fun deserialize(json: JsonElement, typeOf: Type, context: JsonDeserializationContext): Calendar {
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = json.asJsonPrimitive.asLong * 1000
                return calendar
            }
        })

        val builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        serviceApi = builder.create(ServiceApi::class.java)
    }

    suspend fun getUsers(page: Int, seed: String): UserPageEntity {
        return serviceApi.getUsers(page, seed, USER_AMOUNT_PER_PAGE)
    }

}