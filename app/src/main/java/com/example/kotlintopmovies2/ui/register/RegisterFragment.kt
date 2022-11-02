package com.example.kotlintopmovies2.ui.register

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.example.kotlintopmovies2.databinding.FragmentRegisterBinding
import com.example.kotlintopmovies2.data.model.register.BodyRegister
import com.example.kotlintopmovies2.utils.StoreUserData
import com.example.kotlintopmovies2.utils.showInvisible
import com.example.kotlintopmovies2.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    //Binding
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    //DataStore
    @Inject
    lateinit var userDataStore: StoreUserData

    //BodyRegister
    @Inject
    lateinit var body: BodyRegister

    //ViewModel
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Click Submit
        clickSubmit()
    }

    private fun clickSubmit() {
        binding.apply {
            submitBtn.setOnClickListener {
                val name = nameEdt.text.toString()
                val email = emailEdt.text.toString()
                val password = passwordEdt.text.toString()
                if (name.isEmpty()) {
                    nameInput.error = "IsEmpty"
                    emailInput.isErrorEnabled = false
                    passwordInput.isErrorEnabled = false
                } else if (email.isEmpty()) {
                    nameInput.isErrorEnabled = false
                    emailInput.error = "IsEmpty"
                    passwordInput.isErrorEnabled = false
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    nameInput.isErrorEnabled = false
                    emailInput.error = "!Patterns.EMAIL_ADDRESS"
                    passwordInput.isErrorEnabled = false
                } else if (password.isEmpty()) {
                    nameInput.isErrorEnabled = false
                    emailInput.isErrorEnabled = false
                    passwordInput.error = "IsEmpty"
                } else if (password.length <= 6) {
                    nameInput.isErrorEnabled = false
                    emailInput.isErrorEnabled = false
                    passwordInput.error = "Password length must be greater than 6"
                } else {
                    nameInput.isErrorEnabled = false
                    emailInput.isErrorEnabled = false
                    passwordInput.isErrorEnabled = false
                    body.name = name
                    body.email = email
                    body.password = password
                }
                //Send Data
                viewModel.sendRegisterUser(body)
                //Loading
                loading()
                //Register
                register()
            }
        }
    }

    private fun loading() {
        binding.apply {
            viewModel.loading.observe(viewLifecycleOwner) { isShow ->
                if (isShow) {
                    submitLoading.showInvisible(true)
                    submitBtn.showInvisible(false)
                } else {
                    submitLoading.showInvisible(false)
                    submitBtn.showInvisible(true)
                }
            }
        }
    }

    private fun register() {
        viewModel.registerUser.observe(viewLifecycleOwner) { response ->
            lifecycle.coroutineScope.launchWhenCreated {
                userDataStore.saveUserToken(response.name.toString())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}