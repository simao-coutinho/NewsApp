package pt.devsorcerer.newsapp.presentation.features.headlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import pt.devsorcerer.newsapp.model.model.Article
import pt.devsorcerer.newsapp.model.repository.HeadlinesRepository

class HeadlinesViewModel(
    private val headlinesRepository: HeadlinesRepository
): ViewModel() {

    private val mState = MutableStateFlow(HeadlinesState())
    val state: StateFlow<HeadlinesState> = mState.asStateFlow()

    val articles: StateFlow<List<Article>> = headlinesRepository.getHeadlines("")
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    fun init() {
        mState.value = mState.value.copy(
            isLoading = true
        )


        mState.value = mState.value.copy(
            isLoading = false
        )
    }

}