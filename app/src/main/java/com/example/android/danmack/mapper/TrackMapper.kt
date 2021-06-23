package com.example.android.danmack.mapper


import com.example.android.danmack.local.localmodel.LocalTrackEntity
import com.example.android.danmack.model.songmodel.Track

/**
 * Asteroid mapper extension function to map database model to domain model
 * */

//asTrackDomainModel()
fun List<LocalTrackEntity>.asTrackDomainModel(): List<Track> {

    return map { localTrackEntity ->

        Track(
                key = localTrackEntity.key,
                title = localTrackEntity.title,
                subtitle = localTrackEntity.subtitle,
                type = localTrackEntity.type,
                url = localTrackEntity.url,
                images = localTrackEntity.localImages
        )

    }
}


/**
 * Asteroid mapper extension function to map domain model to database model
 * */

//asTrackDatabaseModel

fun List<Track>.asTrackDatabaseModel(): List<LocalTrackEntity> {

    return map { track ->

        LocalTrackEntity(
            key = track.key,
            title = track.title,
            subtitle = track.subtitle,
            type = track.type,
            url = track.url,
            localImages = track.images,
                isRecommended = false

        )

    }
}

fun List<Track>.asRecommendedDatabaseModel(): List<LocalTrackEntity> {

    return map { track ->

        LocalTrackEntity(
                key = track.key,
                title = track.title,
                subtitle = track.subtitle,
                type = track.type,
                url = track.url,
                localImages = track.images,
                isRecommended = true

        )

    }
}

