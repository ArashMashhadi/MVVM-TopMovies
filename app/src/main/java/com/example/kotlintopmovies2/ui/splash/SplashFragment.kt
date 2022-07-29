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
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    //Binding
    private lateinit var binding : FragmentSplashBinding
    //DataStor
    @Inject
    lateinit var storeUserData : StoreUserData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSplashBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Check User Token
        lifecycle.coroutineScope.launchWhenCreated {
            delay(4000)
            storeUserData.getUserToken().collect{
                if(it.isEmpty()){
                    findNavController().navigate(R.id.actionSplashToRegister)
                }else{
                    findNavController().navigate(R.id.actionToHome)
                }
            }
        }
    }

}