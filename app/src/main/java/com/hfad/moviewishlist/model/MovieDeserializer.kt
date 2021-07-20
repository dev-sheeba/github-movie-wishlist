//package com.hfad.moviewishlist.model
//
//import com.google.common.reflect.TypeToken
//import com.google.gson.Gson
//import com.google.gson.JsonDeserializationContext
//import com.google.gson.JsonDeserializer
//import com.google.gson.JsonElement
//import java.lang.reflect.Type
//
//class MovieDeserializer : JsonDeserializer<List<Movie>> {
//
//    companion object {
//        val moviesType = object : TypeToken<List<Movie>>() {}.type
//    }
//
//    override fun deserialize(
//        json: JsonElement?,
//        typeOfT: Type?,
//        context: JsonDeserializationContext?
//    ): List<Movie> {
//        val results = json?.asJsonObject?.get("results")?.asJsonArray
//        return Gson().fromJson(results, moviesType)
//    }
//}