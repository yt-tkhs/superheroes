package app.ytak.superheroes.ext

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.ytak.superheroes.util.*
import kotlinx.coroutines.launch

suspend fun <T : Any> MutableLiveData<AsyncState<T>>.updateWithLoading(
    action: suspend () -> T
) {
    postValue(AsyncState.Loading)
    try {
        postValue(AsyncState.Success(action()))
    } catch (e: Throwable) {
        postValue(AsyncState.Failure(ErrorEvent(
            cause = e,
            recoverAction = {
                // TODO
            }
        )))
    }
}

suspend fun <T : Any> MutableLiveData<AsyncListState<T>>.updateListWithLoading(
    action: suspend () -> List<T>
) {
    val loadingState = value?.toLoading() ?: AsyncListState.Loading(emptyList())
    postValue(loadingState)
    try {
        postValue(loadingState.toSuccess(action()))
    } catch (e: Throwable) {
        postValue(loadingState.toFailure(ErrorEvent(
            cause = e,
            recoverAction = {
                // TODO
            }
        )))
    }
}

fun <T : Any> ViewModel.updateListStateWithLoading(
    liveData: MutableLiveData<AsyncListState<T>>,
    action: suspend () -> List<T>
) {
    viewModelScope.launch {
        val loadingState = liveData.value?.toLoading() ?: AsyncListState.Loading(emptyList())
        liveData.postValue(loadingState)
        try {
            liveData.postValue(loadingState.toSuccess(action()))
        } catch (e: Throwable) {
            liveData.postValue(loadingState.toFailure(ErrorEvent(
                cause = e,
                recoverAction = {
                    updateListStateWithLoading(liveData, action)
                }
            )))
        }
    }
}
