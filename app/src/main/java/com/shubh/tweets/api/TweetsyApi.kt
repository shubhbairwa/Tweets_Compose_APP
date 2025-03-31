package com.shubh.tweets.api

import com.shubh.tweets.models.ResponseTweetsItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers


interface TweetsyApi {
    @GET("v3/b/67ea40548561e97a50f60511?meta=false")
   suspend fun getTweets(@Header("X-JSON-Path") category: String): Response<List<ResponseTweetsItem>>


    @GET("v3/b/67ea40548561e97a50f60511?meta=false")
    @Headers("X-JSON-Path:tweets..category")
  suspend  fun getCategories(): Response<List<String>>


}