package com.example.nytcleanarcitecture.util

import com.example.nytcleanarcitecture.data.remote.feature.mostpopular.model.ArticlesResponseResult
import com.example.nytcleanarcitecture.data.remote.feature.mostpopular.model.MediaMetadataResponse
import com.example.nytcleanarcitecture.data.remote.feature.mostpopular.model.MediaResponse

object DummyData {
    val mediaMetadataResponse: MediaMetadataResponse
        get() = MediaMetadataResponse(
            url = "https://url",
            format = "format",
            height = 293,
            width = 440
        )

    val mediaResponse: MediaResponse
        get() = MediaResponse(
            type = "image",
            subtype = "photo",
            caption = "caption",
            copyright = "copyright",
            approvedForSyndication = 1,
            mediaMetadata = listOf(mediaMetadataResponse)
        )

    val articlesResponseResult: ArticlesResponseResult
        get() = ArticlesResponseResult(
            uri = "uri",
            url = "url",
            id = 123456,
            assetId = 123456,
            source = "New York Times",
            publishedDate = "2021-01-31",
            updated = "2021-02-03 10:27:47",
            section = "U.S.",
            subsection = "subsection",
            nytdsection = "u.s.",
            adxKeywords = "title",
            byline = "byline",
            type = "type",
            title = "title",
            abstract = "abstract",
            desFacet = emptyList(),
            orgFacet = emptyList(),
            perFacet = emptyList(),
            geoFacet = emptyList(),
            media = listOf(mediaResponse),
            etaId = 123456
        )

}