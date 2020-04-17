package app.ytak.superheroes.features.comicdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.ytak.superheroes.common.extfun.updateWithLoading
import app.ytak.superheroes.core.AsyncState
import app.ytak.superheroes.core.Optional
import app.ytak.superheroes.data.model.Comic
import app.ytak.superheroes.data.model.Id
import app.ytak.superheroes.data.repository.ComicRepository
import kotlinx.coroutines.launch

class ComicDetailViewModel(
    private val comicRepository: ComicRepository
) : ViewModel() {

    private val _comic by lazy { MutableLiveData<AsyncState<Optional<Comic>>>(AsyncState.Ready) }
    val comic: LiveData<AsyncState<Optional<Comic>>> get() = _comic

    fun getComic(id: Id.Comic) = viewModelScope.launch {
        _comic.updateWithLoading {
            Optional.ofNullable(comicRepository.fetchComic(id))
        }
    }
}
