package com.conde.kun.randomusers.view.splash

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.conde.kun.randomusers.R
import com.conde.kun.randomusers.view.user.UserActivity

import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

    val viewModel: SplashViewModel by lazy { ViewModelProviders.of(this).get(SplashViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()
        packageManager?.getPackageInfo(packageName, 0)?.versionName.let { versionTV.text = getString(R.string.txtVersion, it) }
        viewModel.viewState.observe(this, splashViewStateObserver)
        viewModel.onViewInit()
    }

    val splashViewStateObserver = Observer<SplashViewState> {
        viewState -> if (viewState.showSplash) startActivity(UserActivity.newIntent(this))
    }

}