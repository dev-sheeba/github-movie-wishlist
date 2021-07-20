package com.hfad.moviewishlist.model

import com.google.gson.annotations.SerializedName


data class SearchMoviesResponse(

    @SerializedName("results")
    val results: List<Movie>
)