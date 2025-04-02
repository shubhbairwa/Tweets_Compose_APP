package com.shubh.tweets.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubh.tweets.models.ResponseTweetsItem
import com.shubh.tweets.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: TweetRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val tweets: StateFlow<List<ResponseTweetsItem>>
        get() = repository.tweets

    init {
        viewModelScope.launch {
            val category = savedStateHandle.get<String>("category") ?: "motivation"
            repository.getTweets(category)
        }
    }
}