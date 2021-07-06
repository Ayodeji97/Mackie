package com.example.android.danmack.ui.auth

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.android.danmack.R
import com.example.android.danmack.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {

    private lateinit var ui : FragmentLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_login, container, false)

        ui = FragmentLoginBinding.inflate(inflater)

        auth = FirebaseAuth.getInstance()


        ui.loginBtn.setOnClickListener {

            val email = ui.fragmentLoginEmailEt.text.toString().trim()
            val password = ui.fragmentLoginPasswordEt.text.toString().trim()

            if (email.isEmpty()) {
                ui.fragmentLoginEmailTl.error = "Email Required"
                ui.fragmentLoginEmailTl.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                ui.fragmentLoginEmailTl.error = "Valid Email Required"
                ui.fragmentLoginEmailTl.requestFocus()
                return@setOnClickListener
            }


            if (password.isEmpty() || password.length < 6) {
                ui.fragmentLoginPasswordTl.error = "6 char password required"
                ui.fragmentLoginPasswordTl.requestFocus()
                return@setOnClickListener
            }

            loginUser(email, password)
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


    private fun login () {
        findNavController().navigate(R.id.exploreFragment)
    }

}