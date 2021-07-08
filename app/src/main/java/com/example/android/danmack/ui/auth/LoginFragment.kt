package com.example.android.danmack.ui.auth

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.android.danmack.MainActivity
import com.example.android.danmack.R
import com.example.android.danmack.databinding.FragmentLoginBinding
import com.example.android.danmack.utils.Constants.USEREMAIL
import com.example.android.danmack.utils.SessionManager
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {

    private lateinit var ui : FragmentLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var userEmail : String
    private lateinit var userPassword : String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_login, container, false)

        ui = FragmentLoginBinding.inflate(inflater)

        auth = FirebaseAuth.getInstance()


        gotoSignUpScreen()
        ui.loginBtn.setOnClickListener {

            userEmail = ui.fragmentLoginEmailEt.text.toString().trim()
            userPassword = ui.fragmentLoginPasswordEt.text.toString().trim()

            if (userEmail.isEmpty()) {
                ui.fragmentLoginEmailTl.error = "Email Required"
                ui.fragmentLoginEmailTl.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                ui.fragmentLoginEmailTl.error = "Valid Email Required"
                ui.fragmentLoginEmailTl.requestFocus()
                return@setOnClickListener
            }


            if (userPassword.isEmpty() || userPassword.length < 6) {
                ui.fragmentLoginPasswordTl.error = "6 char password required"
                ui.fragmentLoginPasswordTl.requestFocus()
                return@setOnClickListener
            }

            SessionManager.save(requireContext(), USEREMAIL, userEmail)
            loginUser(userEmail, userPassword)
        }

        return ui.root
    }

    private fun loginUser (email : String, password : String) {

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {

            if (it.isSuccessful) {
                login()

            } else {
                it.exception?.message.let {
                    Toast.makeText(requireContext(), "$it", Toast.LENGTH_LONG).show()
                }
            }
        }

    }


    private fun gotoSignUpScreen() {
       ui.doNotHaveAccountTv.setOnClickListener {
           findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
       }
    }


    private fun login () {
        findNavController().navigate(R.id.exploreFragment)
    }

}