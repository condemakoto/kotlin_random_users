package com.conde.kun.fija.view.facts

import android.util.Log
import com.conde.kun.core.domain.BaseResourceObserver
import com.conde.kun.core.domain.BaseResourceStatusObserver
import com.conde.kun.core.domain.Status
import com.conde.kun.core.view.BaseViewModel
import com.conde.kun.fija.domain.model.Fact
import com.conde.kun.fija.domain.usecase.DeleteFactUseCase
import com.conde.kun.fija.domain.usecase.GetFactsUseCase
import com.conde.kun.fija.domain.usecase.GetUserFactsUseCase
import com.conde.kun.fija.domain.usecase.SaveFactUseCase

class FactsViewModel(
    val getFactsUseCase: GetFactsUseCase,
    val saveFactUseCase: SaveFactUseCase,
    val getUserFactsUseCase: GetUserFactsUseCase,
    val deleteFactUseCase: DeleteFactUseCase
) : BaseViewModel<FactsViewState>() {

    override fun getInitialViewState(): FactsViewState {
        return FactsViewState()
    }

    fun onInitView() {
        BaseResourceStatusObserver(viewState, getFactsUseCase.execute(null), {
            val value = viewState.value!!
            value.loading = false
            value.showError = false
            value.factsList = it
            viewState.value = value
        },
        {
            val value = viewState.value!!
            value.loading = false
            value.showError = true
            viewState.value = value
        },
        {
            val value = viewState.value!!
            value.loading = true
            value.progress = it
            viewState.value = value
        })
    }

    fun onFavoriteFactClicked(fact: Fact) {
        if (fact.favorited) {
            saveFactUseCase.execute(fact)
        } else {
            deleteFactUseCase.execute(fact)
        }

    }

    fun onGetUserByFactsClicked() {
        BaseResourceObserver(viewState, getUserFactsUseCase.execute(null))
        {
            if (it.status != Status.LOADING) {
                Log.i("fav facts", it.data.toString())
            }
        }
    }
}

class FactsViewState {
    var loading = false
    var progress = 0
    var factsList: List<Fact>? = null
    var showError = false
}