package com.example.nytcleanarcitecture.data.remote.feature.mostpopular.mapper

import com.example.nytcleanarcitecture.data.base.mapper.RemoteModelMapper
import com.example.nytcleanarcitecture.data.remote.feature.mostpopular.model.ArticlesResponseResult
import com.example.nytcleanarcitecture.domain.entity.Article
import javax.inject.Inject

class ArticlesRemoteMapper @Inject constructor(
    private val mapper: MediaRemoteMapper
) : RemoteModelMapper<ArticlesResponseResult, Article> {
    override fun mapFromModel(model: ArticlesResponseResult): Article {
        return with(model) {
            Article(
                uri = uri,
                url = url,
                id = id,
                assetId = assetId,
                source = source,
                publishedDate = publishedDate,
                updated = updated,
                section = section,
                subsection = subsection,
                nytdsection = nytdsection,
                adxKeywords = adxKeywords,
                byline = byline,
                type = type,
                title = title,
                `abstract` = abstract,
                desFacet = desFacet,
                orgFacet = orgFacet,
                perFacet = perFacet,
                geoFacet = geoFacet,
                media = mapper.mapModelList(media),
                etaId = etaId
            )
        }
    }
}
