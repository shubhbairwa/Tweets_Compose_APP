package com.shubh.tweets.repository

import com.shubh.tweets.api.TweetsyApi
import com.shubh.tweets.models.ResponseTweetsItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetRepository @Inject constructor(private val tweetsyApi: TweetsyApi) {
    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>>
        get() = _categories


    private val _tweets = MutableStateFlow<List<ResponseTweetsItem>>(emptyList())
    val tweets: StateFlow<List<ResponseTweetsItem>>
        get() = _tweets

    suspend fun getCategories() {
        val response = tweetsyApi.getCategories()
        if (response.isSuccessful && response.body() != null) {
            _categories.emit(response.body()!!)
        }

    }

    suspend fun getTweets(category: String) {
        val response = tweetsyApi.getTweets("""tweets[?(@.category=="$category")]""")
        if (response.isSuccessful && response.body() != null) {
            _tweets.emit(response.body()!!)
        }

    }
}