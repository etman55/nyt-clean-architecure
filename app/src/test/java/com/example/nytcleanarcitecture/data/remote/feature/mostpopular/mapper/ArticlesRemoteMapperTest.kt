package com.example.nytcleanarcitecture.data.remote.feature.mostpopular.mapper

import com.example.nytcleanarcitecture.data.remote.feature.mostpopular.data.DummyData
import com.example.nytcleanarcitecture.data.remote.feature.mostpopular.model.ArticlesResponseResult
import com.example.nytcleanarcitecture.data.remote.feature.mostpopular.model.MediaMetadataResponse
import com.example.nytcleanarcitecture.data.remote.feature.mostpopular.model.MediaResponse
import com.example.nytcleanarcitecture.domain.entity.Article
import com.example.nytcleanarcitecture.domain.entity.Media
import com.example.nytcleanarcitecture.domain.entity.MediaMetadata
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ArticlesRemoteMapperTest {
    private val mediaMetadataRemoteMapper = MediaMetadataRemoteMapper()
    private val mediaRemoteMapper = MediaRemoteMapper(mediaMetadataRemoteMapper)
    private val articlesRemoteMapper = ArticlesRemoteMapper(mediaRemoteMapper)

    @Test
    fun `check uri mapped correctly`() = testData { entity, model ->
        assertThat(entity.uri).isEqualTo(model.uri)
    }

    @Test
    fun `check url mapped correctly`() = testData { entity, model ->
        assertThat(entity.url).isEqualTo(model.url)
    }

    @Test
    fun `check id mapped correctly`() = testData { entity, model ->
        assertThat(entity.id).isEqualTo(model.id)
    }

    @Test
    fun `check assetId mapped correctly`() = testData { entity, model ->
        assertThat(entity.assetId).isEqualTo(model.assetId)
    }

    @Test
    fun `check source mapped correctly`() = testData { entity, model ->
        assertThat(entity.source).isEqualTo(model.source)
    }

    @Test
    fun `check publishedDate mapped correctly`() = testData { entity, model ->
        assertThat(entity.publishedDate).isEqualTo(model.publishedDate)
    }

    @Test
    fun `check updated mapped correctly`() = testData { entity, model ->
        assertThat(entity.updated).isEqualTo(model.updated)
    }

    @Test
    fun `check section mapped correctly`() = testData { entity, model ->
        assertThat(entity.section).isEqualTo(model.section)
    }

    @Test
    fun `check subsection mapped correctly`() = testData { entity, model ->
        assertThat(entity.subsection).isEqualTo(model.subsection)
    }

    @Test
    fun `check nytdsection mapped correctly`() = testData { entity, model ->
        assertThat(entity.nytdsection).isEqualTo(model.nytdsection)
    }

    @Test
    fun `check adxKeywords mapped correctly`() = testData { entity, model ->
        assertThat(entity.adxKeywords).isEqualTo(model.adxKeywords)
    }

    @Test
    fun `check byline mapped correctly`() = testData { entity, model ->
        assertThat(entity.byline).isEqualTo(model.byline)
    }

    @Test
    fun `check type mapped correctly`() = testData { entity, model ->
        assertThat(entity.type).isEqualTo(model.type)
    }

    @Test
    fun `check title mapped correctly`() = testData { entity, model ->
        assertThat(entity.title).isEqualTo(model.title)
    }

    @Test
    fun `check abstract mapped correctly`() = testData { entity, model ->
        assertThat(entity.abstract).isEqualTo(model.abstract)
    }

    @Test
    fun `check desFacet mapped correctly`() = testData { entity, model ->
        assertThat(entity.desFacet).isEqualTo(model.desFacet)
    }

    @Test
    fun `check orgFacet mapped correctly`() = testData { entity, model ->
        assertThat(entity.orgFacet).isEqualTo(model.orgFacet)
    }

    @Test
    fun `check perFacet mapped correctly`() = testData { entity, model ->
        assertThat(entity.perFacet).isEqualTo(model.perFacet)
    }

    @Test
    fun `check geoFacet mapped correctly`() = testData { entity, model ->
        assertThat(entity.geoFacet).isEqualTo(model.geoFacet)
    }

    @Test
    fun `check media list has same content as mediaResponse`() {
        val mediaResponse: List<MediaResponse> = DummyData.articlesResponseResult.media
        val media: List<Media> = mediaRemoteMapper.mapModelList(mediaResponse)
        assertThat(media.first().type).isEqualTo(mediaResponse.first().type)
        assertThat(media.first().subtype).isEqualTo(mediaResponse.first().subtype)
        assertThat(media.first().caption).isEqualTo(mediaResponse.first().caption)
        assertThat(media.first().copyright).isEqualTo(mediaResponse.first().copyright)
        assertThat(media.first().approvedForSyndication).isEqualTo(mediaResponse.first().approvedForSyndication)
        val mediaMetadataResponse: List<MediaMetadataResponse> = DummyData.mediaResponse.mediaMetadata
        val mediaMetadata: List<MediaMetadata> = mediaMetadataRemoteMapper.mapModelList(mediaMetadataResponse)
        assertThat(mediaMetadata.first().url).isEqualTo(mediaMetadataResponse.first().url)
        assertThat(mediaMetadata.first().format).isEqualTo(mediaMetadataResponse.first().format)
        assertThat(mediaMetadata.first().width).isEqualTo(mediaMetadataResponse.first().width)
        assertThat(mediaMetadata.first().height).isEqualTo(mediaMetadataResponse.first().height)
    }

    @Test
    fun `check etaId mapped correctly`() = testData { entity, model ->
        assertThat(entity.etaId).isEqualTo(model.etaId)
    }

    private fun testData(action: (Article, ArticlesResponseResult) -> Unit) {
        val model: ArticlesResponseResult = DummyData.articlesResponseResult
        val entity: Article = articlesRemoteMapper.mapFromModel(model)
        action(entity, model)
    }
}
