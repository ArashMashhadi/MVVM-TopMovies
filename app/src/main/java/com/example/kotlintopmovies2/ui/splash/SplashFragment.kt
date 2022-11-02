package com.example.kotlintopmovies2.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.example.kotlintopmovies2.R
import com.example.kotlintopmovies2.databinding.FragmentSplashBinding
import com.example.kotlintopmovies2.utils.StoreUserData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    //Binding
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    //DataStore
    @Inject
    lateinit var storeUserData: StoreUserData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Check User Token
        checkUserToken()
    }

    private fun checkUserToken() {
        lifecycle.coroutineScope.launchWhenCreated {
            delay(4000)
            storeUserData.getUserToken().collect {
                if (it.isEmpty()) {
                    findNavController().navigate(R.id.actionSplashToRegister)
                } else {
                    findNavController().navigate(R.id.actionToHome)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}