package com.example.recommendmeamovie.source.remote
import com.example.recommendmeamovie.domain.Credit
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.domain.MovieDetails
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviesContainer(
    val results: List<MovieDTO>,
    @Json(name = "total_results") val totalResults: Int
)

@JsonClass(generateAdapter = true)
data class MovieDTO(
    val id: Long,
    val title: String,
    @Json(name = "poster_path") val poster: String,
    @Json(name = "release_date") val releaseDate: String,
)

@JsonClass(generateAdapter = true)
data class MovieDetailsDTO(
    val id: Long,
    val title: String,
    val overview: String,
    val runtime: Int,
    val genres: List<Genre>?,
    val credits : CreditsDTO,
    @Json(name = "poster_path") val poster: String,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "vote_average") val voteAverage: Double
)

@JsonClass(generateAdapter = true)
data class Genre(
    val id: Long,
    val name: String
)

@JsonClass(generateAdapter = true)
data class CreditsDTO(
    val cast: List<Cast>?,
    val crew: List<Crew>?
)

@JsonClass(generateAdapter = true)
data class Cast(
    val name : String,
    val character: String,
    @Json(name = "profile_path")  val picture : String?
)

@JsonClass(generateAdapter = true)
data class Crew(
    val name : String,
    val job: String,
    @Json(name = "profile_path") val picture : String?
)


fun MoviesContainer.asDomainModel() : List<Movie> {
    return results.map {
        Movie(it.id, it.title, it.poster, it.releaseDate)
    }

}

fun MovieDetailsDTO.asDomainModel() : MovieDetails {
    return MovieDetails(
        id,
        title,
        overview,
        runtime,
        genres,
        credits.cast?.asCastDomainModel(),
        credits.crew?.asCrewDomainModel(),
        poster,
        releaseDate,
        voteAverage
    )
}

fun List<Cast>.asCastDomainModel() : List<Credit> {
    return this.map {
        Credit(it.name, it.character, it.picture)
    }
}

fun List<Crew>.asCrewDomainModel() : List<Credit> {
    return this.map {
        Credit(it.name, it.job, it.picture)
    }
}


//enum class Genre(val id : Int) {
//    ACTION (28),
//    ANIMATION (16),
//    COMEDY (35),
//    CRIME (80),
//    DRAMA (18),
//    FANTASY(14),
//    HORROR(27),
//    MYSTERY(9648),
//    ROMANCE(10749),
//    SCIENCE_FICTION(878),
//    THRILLER(53),
//    WAR(10752),
//    WESTERN(37)
//}