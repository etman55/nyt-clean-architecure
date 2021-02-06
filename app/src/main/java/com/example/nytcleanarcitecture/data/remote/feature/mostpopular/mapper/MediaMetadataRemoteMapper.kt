package com.example.nytcleanarcitecture.data.remote.feature.mostpopular.mapper

import com.example.nytcleanarcitecture.data.base.mapper.RemoteModelMapper
import com.example.nytcleanarcitecture.data.remote.feature.mostpopular.model.MediaMetadataResponse
import com.example.nytcleanarcitecture.domain.entity.MediaMetadata
import javax.inject.Inject

class MediaMetadataRemoteMapper @Inject constructor() : RemoteModelMapper<MediaMetadataResponse, MediaMetadata> {
    override fun mapFromModel(model: MediaMetadataResponse): MediaMetadata {
        return with(model) {
            MediaMetadata(
                url = url,
                format = format,
                height = height,
                width = width
            )
        }
    }
}
