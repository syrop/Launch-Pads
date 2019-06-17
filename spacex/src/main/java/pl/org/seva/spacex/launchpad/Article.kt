package pl.org.seva.spacex.launchpad

data class Article(val thumbnail: Thumbnail) {
    data class Thumbnail(val source: String)
}