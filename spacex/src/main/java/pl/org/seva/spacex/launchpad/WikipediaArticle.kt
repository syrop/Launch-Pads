package pl.org.seva.spacex.launchpad

data class WikipediaArticle(val thumbnail: Thumbnail) {
    data class Thumbnail(val source: String)
}
