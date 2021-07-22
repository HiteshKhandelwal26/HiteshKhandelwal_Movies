package com.demo.movies.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
/*Setting up the Model class*/
class MovieList : ArrayList<MovieList.MovieListItem>() {
    @Keep
    class MovieListItem(
        @SerializedName("score")
        var score: Double = 0.0,
        @SerializedName("show")
        var show: Show = Show()
    ) {
        @Keep
        class Show(
            @SerializedName("averageRuntime")
            var averageRuntime: Int = 0,
            @SerializedName("dvdCountry")
            var dvdCountry: Any = Any(),
            @SerializedName("externals")
            var externals: Externals = Externals(),
            @SerializedName("genres")
            var genres: List<String> = listOf(),
            @SerializedName("id")
            var id: Int = 0,
            @SerializedName("image")
            var image: Image = Image(),
            @SerializedName("language")
            var language: String = "",
            @SerializedName("_links")
            var links: Links = Links(),
            @SerializedName("name")
            var name: String = "",
            @SerializedName("network")
            var network: Network = Network(),
            @SerializedName("officialSite")
            var officialSite: String = "",
            @SerializedName("premiered")
            var premiered: String = "",
            @SerializedName("rating")
            var rating: Rating = Rating(),
            @SerializedName("runtime")
            var runtime: Int = 0,
            @SerializedName("schedule")
            var schedule: Schedule = Schedule(),
            @SerializedName("status")
            var status: String = "",
            @SerializedName("summary")
            var summary: String = "",
            @SerializedName("type")
            var type: String = "",
            @SerializedName("updated")
            var updated: Int = 0,
            @SerializedName("url")
            var url: String = "",
            @SerializedName("webChannel")
            var webChannel: Any = Any(),
            @SerializedName("weight")
            var weight: Int = 0
        ) {
            @Keep
            class Externals(
                @SerializedName("imdb")
                var imdb: String = "",
                @SerializedName("thetvdb")
                var thetvdb: Int = 0,
                @SerializedName("tvrage")
                var tvrage: Any = Any()
            )

            @Keep
            class Image(
                @SerializedName("medium")
                var medium: String = "",
                @SerializedName("original")
                var original: String = ""
            )

            @Keep
            class Links(
                @SerializedName("previousepisode")
                var previousepisode: Previousepisode = Previousepisode(),
                @SerializedName("self")
                var self: Self = Self()
            ) {
                @Keep
                class Previousepisode(
                    @SerializedName("href")
                    var href: String = ""
                )

                @Keep
                class Self(
                    @SerializedName("href")
                    var href: String = ""
                )
            }

            @Keep
            class Network(
                @SerializedName("country")
                var country: Country = Country(),
                @SerializedName("id")
                var id: Int = 0,
                @SerializedName("name")
                var name: String = ""
            ) {
                @Keep
                class Country(
                    @SerializedName("code")
                    var code: String = "",
                    @SerializedName("name")
                    var name: String = "",
                    @SerializedName("timezone")
                    var timezone: String = ""
                )
            }

            @Keep
            class Rating(
                @SerializedName("average")
                var average: Any = Any()
            )

            @Keep
            class Schedule(
                @SerializedName("days")
                var days: List<String> = listOf(),
                @SerializedName("time")
                var time: String = ""
            )
        }
    }
}
