package com.codepath.articlesearch
import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Keep
@Serializable
data class SearchPopularPeopleResponse(
    @SerialName("results")
    val people: List<Person>?
)

@Keep
@Serializable
data class Person(
    @SerialName("name")
    val name: String?,
    @SerialName("profile_path")
    val profile_path: String?,
    @SerialName("known_for_department")
    val known_for_department:String?,
    @SerialName("known_for")
    val known_for: List<Work>
) :  java.io.Serializable{
    val mediaImageUrl = "${profile_path.let{ if (it!= null )"https://image.tmdb.org/t/p/original/${it}" else ""}}"
}



@Keep
@Serializable
data class Work(
    @Keep
    @SerialName("media_type")
    val original: String?,
    @Keep
    @SerialName("title")
    val title: String? = "unavailable title",
    @Keep
    @SerialName("overview")
    val overview: String?,
    @Keep
    @SerialName("poster_path")
    val poster_path: String?

): java.io.Serializable{
    val posterImageUrl = "${poster_path.let{ if (it!= null )"https://image.tmdb.org/t/p/original/${it}" else ""}}"

}





