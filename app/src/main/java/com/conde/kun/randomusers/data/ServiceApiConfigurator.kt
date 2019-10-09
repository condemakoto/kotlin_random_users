package com.conde.kun.randomusers.data

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.*

class ServiceApiConfigurator {

    var serviceApi: ServiceApi
    val API_BASE_URL: String = "https://randomuser.me"

    init {
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
            .build()

        serviceApi = builder.create(ServiceApi::class.java)
    }

}