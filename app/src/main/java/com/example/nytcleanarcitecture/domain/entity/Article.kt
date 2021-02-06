package com.example.nytcleanarcitecture.domain.entity

data class Article(
    val uri: String,
    val url: String,
    val id: Long,
    val assetId: Long,
    val source: String,
    val publishedDate: String,
    val updated: String,
    val section: String,
    val subsection: String,
    val nytdsection: String,
    val adxKeywords: String,
    val byline: String,
    val type: String,
    val title: String,
    val `abstract`: String,
    val desFacet: List<String>,
    val orgFacet: List<String>,
    val perFacet: List<String>,
    val geoFacet: List<String>,
    val media: List<Media>,
    val etaId: Int
)

data class Media(
    val type: String,
    val subtype: String,
    val caption: String,
    val copyright: String,
    val approvedForSyndication: Int,
    val mediaMetadata: List<MediaMetadata>
)

data class MediaMetadata(
    val url: String,
    val format: String,
    val height: Int,
    val width: Int
)
