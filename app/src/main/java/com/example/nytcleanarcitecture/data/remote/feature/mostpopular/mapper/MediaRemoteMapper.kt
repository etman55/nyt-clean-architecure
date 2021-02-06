package com.example.nytcleanarcitecture.data.remote.feature.mostpopular.mapper

import com.example.nytcleanarcitecture.data.base.mapper.RemoteModelMapper
import com.example.nytcleanarcitecture.data.remote.feature.mostpopular.model.MediaResponse
import com.example.nytcleanarcitecture.domain.entity.Media
import javax.inject.Inject

class MediaRemoteMapper @Inject constructor(private val mapper: MediaMetadataRemoteMapper) :
    RemoteModelMapper<MediaResponse, Media> {
    override fun mapFromModel(model: MediaResponse): Media {
        return with(model) {
            Media(
                type = type,
                subtype = subtype,
                caption = caption,
                copyright = copyright,
                approvedForSyndication = approvedForSyndication,
                mediaMetadata = mapper.mapModelList(mediaMetadata)
            )
        }
    }
}
