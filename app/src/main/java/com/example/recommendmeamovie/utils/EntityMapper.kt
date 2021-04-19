package com.example.recommendmeamovie.utils

interface EntityMapper <Entity, Domain> {

    fun mapToDomain(entity : Entity) : Domain

    fun mapToEntity(domain : Domain) : Entity {
        TODO("Default Implementation")
    }

    fun mapFromDomainList(domain : List<Domain>?): List<Entity>? {
        return domain?.map {
            mapToEntity(it)
        }
    }

    fun mapFromEntityList(entities : List<Entity>?): List<Domain>? {
        return entities?.map {
            mapToDomain(it)
        }
    }
}