package com.example.recommendmeamovie.utils

interface EntityMapper <Entity, Domain> {

    fun map(entity : Entity) : Domain {
        TODO("Default constructor")
    }

    fun mapList(entities : List<Entity>?): List<Domain>? {
        return entities?.map {
            map(it)
        }
    }
}