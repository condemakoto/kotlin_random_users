package com.conde.kun.fija.view.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.conde.kun.core.view.BaseActivity
import com.conde.kun.fija.MainActivity
import com.conde.kun.fija.R
import com.conde.kun.fija.view.facts.FactsFragment
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class DashboardActivity : BaseActivity<DashboardViewState, DashboardViewModel>() {


    override fun initViewModel(): DashboardViewModel {
        return getViewModel()
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_dashboard
    }

    override fun onViewStateUpdated(viewState: DashboardViewState) {
        if (viewState.goToOnboarding) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "Facts"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.getFacts) {
            val navHostFragment = this.supportFragmentManager.fragments.first() as NavHostFragment

            navHostFragment.childFragmentManager.fragments.forEach {
                if (it is FactsFragment) {
                    it.onGetFavsClicked()
                }
            }

            return true
        }
        if (item?.itemId == R.id.logout) {
            viewModel.logout()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}