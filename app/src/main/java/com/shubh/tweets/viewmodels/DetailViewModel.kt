package com.shubh.tweets.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubh.tweets.models.ResponseTweetsItem
import com.shubh.tweets.repository.TweetRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val repository: TweetRepository) : ViewModel() {

    val tweets: StateFlow<List<ResponseTweetsItem>>
        get() = repository.tweets

    init {
        viewModelScope.launch {
            repository.getTweets("motivation")
        }
    }
}