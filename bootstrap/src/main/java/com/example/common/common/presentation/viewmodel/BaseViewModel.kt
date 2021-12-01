package com.example.common.common.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.common.common.presentation.*
import com.example.common.common.presentation.schedulers.SchedulersProvider
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class BaseViewModel<S : BaseState>(
    initialState: S,
    val schedulersProvider: SchedulersProvider
) : ViewModel() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val _stateFlow: MutableStateFlow<S> = MutableStateFlow(initialState)
    val stateFlow: StateFlow<S> = _stateFlow

    fun <T> Single<T>.execute(
        retainValue: Async<T>? = null,
        reducer: S.(Async<T>) -> S
    ) {
        this.toObservable().execute(retainValue, reducer)
    }

    fun <T> Observable<T>.execute(
        retainValue: Async<T>? = null,
        reducer: S.(Async<T>) -> S
    ) {
        this.subscribeOn(schedulersProvider.io())
            .doOnSubscribe {
                setState(
                    getState().reducer(Loading(value = retainValue?.invoke()))
                )
            }
            .subscribe({
                setState(
                    getState().reducer(Success(it))
                )
            }, {
                setState(
                    getState().reducer(Fail(it, value = retainValue?.invoke()))
                )
            })
            .addTo(compositeDisposable)
    }

    protected fun setState(state: S) {
        _stateFlow.value = state
    }

    fun getState(): S = _stateFlow.value

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }
}

interface BaseState {

}