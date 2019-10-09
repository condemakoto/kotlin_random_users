package com.conde.kun.fija

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import com.conde.kun.fija.view.login.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.fragmentContainer, LoginFragment())
        ft.commit()
        */
    }

}
