//package com.example.mymovies.domain.models
//
//import android.graphics.drawable.Drawable
//import com.google.gson.annotations.Expose
//import com.google.gson.annotations.SerializedName
//import io.realm.RealmObject
//import io.realm.annotations.Ignore
//
//data class Movie(
//        var id: Int,
//        @SerializedName("overview")
//        val overview: String,
//        @SerializedName("release_date")
//        val releaseDate: String,
//        @SerializedName("vote_average")
//        val voteAverage: Double,
//        @SerializedName("poster_path")
//        val posterPath: String,
//        @Transient
//        @Ignore
//        var drawable: Drawable?
//) : RealmObject()