package com.example.nytcleanarcitecture.data.remote.feature.mostpopular.mapper

import com.example.nytcleanarcitecture.data.remote.feature.mostpopular.data.DummyData
import com.example.nytcleanarcitecture.data.remote.feature.mostpopular.model.MediaMetadataResponse
import com.example.nytcleanarcitecture.domain.entity.MediaMetadata
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MediaMetadataRemoteMapperTest {

    private val mediaMetadataRemoteMapper = MediaMetadataRemoteMapper()

    @Test
    fun `check url mapped correctly`() = testData { entity, model ->
        assertThat(entity.url).isEqualTo(model.url)
    }

    @Test
    fun `check format mapped correctly`() = testData { entity, model ->
        assertThat(entity.format).isEqualTo(model.format)
    }

    @Test
    fun `check width mapped correctly`() = testData { entity, model ->
        assertThat(entity.width).isEqualTo(model.width)
    }

    @Test
    fun `check height mapped correctly`() = testData { entity, model ->
        assertThat(entity.height).isEqualTo(model.height)
    }

    private fun testData(action: (MediaMetadata, MediaMetadataResponse) -> Unit) {
        val model: MediaMetadataResponse = DummyData.mediaMetadataResponse
        val entity: MediaMetadata = mediaMetadataRemoteMapper.mapFromModel(model)
        action(entity, model)
    }
}
