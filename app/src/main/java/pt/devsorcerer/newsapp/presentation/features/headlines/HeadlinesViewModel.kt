package pt.devsorcerer.newsapp.presentation.features.headlines

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import pt.devsorcerer.newsapp.domain.model.Article
import pt.devsorcerer.newsapp.domain.repository.HeadlinesRepository
import pt.devsorcerer.newsapp.domain.util.onError
import pt.devsorcerer.newsapp.domain.util.onSuccess

class HeadlinesViewModel(
    private val headlinesRepository: HeadlinesRepository
) : ViewModel() {

    private val mState = MutableStateFlow(HeadlinesState())
    val state: StateFlow<HeadlinesState> = mState.asStateFlow()

    private var currentPage = 1
    private var isWorking = false

    val articles: StateFlow<List<Article>> = headlinesRepository.getHeadlines()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    fun init() {
        loadMoreItems()
    }

    fun loadMoreItems() {
        viewModelScope.launch {
            mState.value = mState.value.copy(
                isLoading = true
            )
            if (isWorking) return@launch
            isWorking = true
            Log.d("HeadlinesViewModel", "loadMoreItems: $currentPage")

            headlinesRepository
                .getRemoteHeadlines(page = currentPage)
                .onSuccess { articles ->
                    viewModelScope.launch(Dispatchers.IO) {
                        headlinesRepository.saveArticles(articles)
                    }

                    mState.value = mState.value.copy(
                        isLoading = false
                    )
                    currentPage++
                    isWorking = false
                }.onError {
                    Log.e("HeadlinesViewModel", "init: $it")
                    mState.value = mState.value.copy(
                        isLoading = false
                    )
                    isWorking = false
                }
        }

    }

}