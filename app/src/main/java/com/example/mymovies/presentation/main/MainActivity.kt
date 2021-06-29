package com.example.mymovies.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mymovies.R
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any?>

    @Inject
    lateinit var holder: NavigatorHolder

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val model: MainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model.toSearch()
    }

    override fun onResume() {
        super.onResume()
        holder.setNavigator(navigator)
    }

    private val navigator = object : SupportAppNavigator(this, R.id.main_container) {
        override fun createFragment(screen: SupportAppScreen): Fragment = screen.fragment
    }

    override fun androidInjector() = dispatchingAndroidInjector
}