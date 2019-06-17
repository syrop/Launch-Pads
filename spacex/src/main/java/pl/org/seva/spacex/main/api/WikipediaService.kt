package pl.org.seva.spacex.main.api

import pl.org.seva.spacex.launchpad.Article
import pl.org.seva.spacex.main.init.instance
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

val wikipediaService by instance<WikipediaService>()

interface WikipediaService {
    @GET("summary/{article}")
    suspend fun getSummary(@Path("article") article: String): Response<Article>
}
