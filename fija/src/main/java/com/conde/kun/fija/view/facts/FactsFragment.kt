package com.conde.kun.fija.view.facts

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.conde.kun.core.view.BaseMVVMFragment
import com.conde.kun.fija.R
import com.conde.kun.fija.domain.model.Fact
import kotlinx.android.synthetic.main.fragment_facts.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class FactsFragment : BaseMVVMFragment<FactsViewState, FactsViewModel>(),
    FactsAdapter.FactListener {
    override fun initViewModel(): FactsViewModel {
        return getViewModel()
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_facts
    }

    override fun onViewStateUpdated(viewState: FactsViewState) {
        loadingView.visibility = if (viewState.loading) View.VISIBLE else View.GONE
        loadingPorcTV.text = String.format("Loading... %d%%", viewState.progress)
        errorView.visibility = if (viewState.showError) View.VISIBLE else View.GONE
        viewState.factsList?.let {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = FactsAdapter(it, this)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onInitView()
    }

    override fun onFavoriteFactClicked(fact: Fact) {
        viewModel.onFavoriteFactClicked(fact)
    }

    fun onGetFavsClicked() {
        viewModel.onGetUserByFactsClicked()
    }
}