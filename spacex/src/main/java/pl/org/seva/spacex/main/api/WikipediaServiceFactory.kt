package pl.org.seva.spacex.main.api

import pl.org.seva.spacex.main.init.instance
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

val wikipediaServiceFactory by instance<WikipediaServiceFactory>()

class WikipediaServiceFactory {

    fun getWikipediaService(): WikipediaService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(WikipediaService::class.java)

    companion object {
        const val BASE_URL = "https://en.wikipedia.org/api/rest_v1/page/"
    }
}
