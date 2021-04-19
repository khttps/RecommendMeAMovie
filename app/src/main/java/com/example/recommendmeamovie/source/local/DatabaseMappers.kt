package com.example.recommendmeamovie.source.local

import com.example.recommendmeamovie.domain.Credit
import com.example.recommendmeamovie.domain.Movie
import com.example.recommendmeamovie.domain.MovieDetails
import com.example.recommendmeamovie.utils.EntityMapper
import javax.inject.Inject

class MovieEntityMapper
@Inject constructor() : EntityMapper<MovieEntity, Movie> {
    override fun mapToDomain(entity: MovieEntity): Movie {
        return Movie(
            id = entity.id,
            title = entity.title,
            poster = entity.poster,
            releaseDate = entity.releaseDate
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