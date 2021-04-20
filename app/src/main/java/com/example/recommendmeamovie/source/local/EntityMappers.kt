package com.example.recommendmeamovie.source.local

import com.example.recommendmeamovie.domain.Movie

fun List<MovieEntity>.asDomain() : List<Movie> {

    return map {
        Movie(
            it.id,
            it.title,
            it.poster,
            it.releaseDate
        )
    }

}

//class MovieEntityDetailsMapper
//@Inject constructor(private val creditEntityMapper: CreditEntityMapper) :
//    EntityMapper<MovieDetailsEntity, MovieDetails> {
//
//    override fun mapToDomain(entity: MovieDetailsEntity): MovieDetails {
//        return MovieDetails(
//            entity.id,
//            entity.title,
//            entity.overview,
//            entity.runtime,
//            entity.genres,
//            entity.cast.let{
//                creditEntityMapper.mapFromEntityList(it)
//            },
//            entity.crew.let{
//                creditEntityMapper.mapFromEntityList(it)
//            },
//            entity.poster,
//            entity.releaseDate,
//            entity.voteAverage
//        )
//    }
//
//}
//
//class CreditEntityMapper
//@Inject constructor() : EntityMapper<CreditEntity, Credit> {
//    override fun mapToDomain(entity: CreditEntity): Credit {
//        return Credit(
//            entity.name,
//            entity.role,
//            entity.picture
//        )
//    }
//
//}