package app.ytak.superheroes.comics

import androidx.lifecycle.*
import app.ytak.superheroes.data.dto.Comic
import app.ytak.superheroes.data.repository.ComicRepository
import app.ytak.superheroes.ext.updateListWithLoading
import app.ytak.superheroes.ext.updateWithLoading
import app.ytak.superheroes.util.AsyncListState
import app.ytak.superheroes.util.AsyncState
import kotlinx.coroutines.launch

class ComicsViewModel(
    private val comicRepository: ComicRepository
) : ViewModel(), LifecycleObserver {

    private val _thisWeekComics by lazy { MutableLiveData<AsyncState<List<Comic>>>(AsyncState.Ready) }
    val thisWeekComics: LiveData<AsyncState<List<Comic>>> get() = _thisWeekComics

    private val _thisMonthComics by lazy { MutableLiveData<AsyncListState<Comic>>(AsyncListState.Ready) }
    val thisMonthComics: LiveData<AsyncListState<Comic>> get() = _thisMonthComics

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() = viewModelScope.launch {
        refreshThisWeekComics().join()
        refreshThisMonthComics().join()
    }

    fun refreshThisWeekComics() = viewModelScope.launch {
        _thisWeekComics.updateWithLoading {
            comicRepository.fetchComicsOnsaleThisWeek(0)
        }
    }

    fun refreshThisMonthComics() = viewModelScope.launch {
        _thisMonthComics.updateListWithLoading {
            comicRepository.fetchComicsOnsaleThisMonth(0)
        }
    }
}
