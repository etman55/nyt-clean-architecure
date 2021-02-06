package com.example.nytcleanarcitecture.data.remote.feature.mostpopular.mapper

import com.example.nytcleanarcitecture.data.remote.feature.mostpopular.data.DummyData
import com.example.nytcleanarcitecture.data.remote.feature.mostpopular.model.MediaMetadataResponse
import com.example.nytcleanarcitecture.data.remote.feature.mostpopular.model.MediaResponse
import com.example.nytcleanarcitecture.domain.entity.Media
import com.example.nytcleanarcitecture.domain.entity.MediaMetadata
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MediaRemoteMapperTest {

    private val mediaMetadataRemoteMapper = MediaMetadataRemoteMapper()
    private val mediaRemoteMapper = MediaRemoteMapper(mediaMetadataRemoteMapper)

    @Test
    fun `check type mapped correctly`() = testData { entity, model ->
        assertThat(entity.type).isEqualTo(model.type)
    }

    @Test
    fun `check subtype mapped correctly`() = testData { entity, model ->
        assertThat(entity.subtype).isEqualTo(model.subtype)
    }

    @Test
    fun `check caption mapped correctly`() = testData { entity, model ->
        assertThat(entity.caption).isEqualTo(model.caption)
    }

    @Test
    fun `check copyright mapped correctly`() = testData { entity, model ->
        assertThat(entity.copyright).isEqualTo(model.copyright)
    }

    @Test
    fun `check approvedForSyndication mapped correctly`() = testData { entity, model ->
        assertThat(entity.approvedForSyndication).isEqualTo(model.approvedForSyndication)
    }

    @Test
    fun `check mediaMetadata list has same content as mediaMetadataResponse`() {
        val mediaMetadataResponse: List<MediaMetadataResponse> = DummyData.mediaResponse.mediaMetadata
        val mediaMetadata: List<MediaMetadata> = mediaMetadataRemoteMapper.mapModelList(mediaMetadataResponse)
        assertThat(mediaMetadata.first().url).isEqualTo(mediaMetadataResponse.first().url)
        assertThat(mediaMetadata.first().format).isEqualTo(mediaMetadataResponse.first().format)
        assertThat(mediaMetadata.first().width).isEqualTo(mediaMetadataResponse.first().width)
        assertThat(mediaMetadata.first().height).isEqualTo(mediaMetadataResponse.first().height)
    }

    private fun testData(action: (Media, MediaResponse) -> Unit) {
        val model: MediaResponse = DummyData.mediaResponse
        val entity: Media = mediaRemoteMapper.mapFromModel(model)
        action(entity, model)
    }
}
