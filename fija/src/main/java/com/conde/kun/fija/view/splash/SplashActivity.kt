package com.conde.kun.fija.view.splash

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.conde.kun.core.view.BaseActivity
import com.conde.kun.fija.MainActivity
import com.conde.kun.fija.R
import com.conde.kun.fija.view.dashboard.DashboardActivity
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SplashActivity : BaseActivity<SplashViewState, SplashViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        viewModel.onInitView()
    }

    override fun initViewModel(): SplashViewModel {
        return getViewModel()
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_splash
    }

    override fun onViewStateUpdated(viewState: SplashViewState) {
        if (viewState.showLogin) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        if (viewState.showDashboard) {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }
    }
}