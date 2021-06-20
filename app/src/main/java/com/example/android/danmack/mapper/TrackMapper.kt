package com.example.android.danmack.mapper

import com.example.android.danmack.local.localmodel.TrackEntity
import com.example.android.danmack.model.Track

/**
 * Asteroid mapper extension function to map database model to domain model
 * */

fun List<TrackEntity>.asTrackDomainModel() : List<Track> {

    return  map {trackEntity ->
        Track(
                key = trackEntity.key,
                layout = trackEntity.layout,
                title = trackEntity.title,
                subtitle = trackEntity.subtitle,
                type = trackEntity.title,
                url = trackEntity.url,
                hub = trackEntity.hub,
                artists = trackEntity.artists,
                images = trackEntity.images,
                share = trackEntity.share,
        )

    }
}