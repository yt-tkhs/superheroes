package app.ytak.superheroes.features.comics

import androidx.lifecycle.*
import app.ytak.superheroes.common.extfun.updateListWithLoading
import app.ytak.superheroes.common.extfun.updateWithLoading
import app.ytak.superheroes.core.AsyncListState
import app.ytak.superheroes.core.AsyncState
import app.ytak.superheroes.data.model.Comic
import app.ytak.superheroes.data.repository.ComicRepository
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
            comicRepository.fetchComicsOnsaleFuture(0)
        }
    }

    fun refreshThisMonthComics() = viewModelScope.launch {
        _thisMonthComics.updateListWithLoading {
            comicRepository.fetchComicsOnsaleThisMonth(0)
        }
    }
}
