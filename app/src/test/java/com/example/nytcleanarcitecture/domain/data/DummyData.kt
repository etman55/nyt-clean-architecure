package com.example.nytcleanarcitecture.domain.data

import com.example.nytcleanarcitecture.domain.entity.Article
import com.example.nytcleanarcitecture.domain.entity.Media
import com.example.nytcleanarcitecture.domain.entity.MediaMetadata

object DummyData {

    val mediaMetadata: MediaMetadata
        get() = MediaMetadata(
            url = "https://url",
            format = "format",
            height = 293,
            width = 440
        )

    val media: Media
        get() = Media(
            type = "image",
            subtype = "photo",
            caption = "caption",
            copyright = "copyright",
            approvedForSyndication = 1,
            mediaMetadata = listOf(mediaMetadata)
        )

    val article: Article
        get() = Article(
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
            media = listOf(media),
            etaId = 123456
        )
}
