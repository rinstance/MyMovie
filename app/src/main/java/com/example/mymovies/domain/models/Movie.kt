package com.example.mymovies.domain.models

import android.graphics.drawable.Drawable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.Ignore

data class Movie(
        var id: Int,
        var overview: String,
        var popularity: Double,
        @SerializedName("release_date")
        var releaseDate: String,
        @SerializedName("vote_average")
        var voteAverage: Double,
        @SerializedName("poster_path")
        var posterPath: String,
        var title: String,
        @Transient
        @Ignore
        var drawable: Drawable?
) : RealmObject()

/*
public class Movie extends RealmObject {
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("video")
    @Expose
    private Boolean video;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @Ignore
    @Transient
    private Drawable drawable = null;
 */