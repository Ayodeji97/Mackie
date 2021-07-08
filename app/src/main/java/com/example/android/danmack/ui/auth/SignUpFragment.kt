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
import com.example.android.danmack.databinding.FragmentSignUpBinding
import com.example.android.danmack.utils.Constants
import com.example.android.danmack.utils.SessionManager
import com.google.firebase.auth.FirebaseAuth


class SignUpFragment : Fragment() {

    private lateinit var ui : FragmentSignUpBinding

    private lateinit var auth: FirebaseAuth

    private lateinit var userEmail : String
    private lateinit var userPassword : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_sign_up, container, false)
        ui = FragmentSignUpBinding.inflate(inflater)


        auth = FirebaseAuth.getInstance()

        gotoLoginScreen()

        ui.registerBtn.setOnClickListener {

            userEmail = ui.fragmentRegisterEmailEt.text.toString().trim()
            userPassword = ui.fragmentRegisterPasswordEt.text.toString().trim()

            if (userEmail.isEmpty()) {
                ui.fragmentRegisterRegisterTl.error = "Email Required"
                ui.fragmentRegisterRegisterTl.requestFocus()
                return@setOnClickListener
            }


            if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                ui.fragmentRegisterRegisterTl.error = "Valid Email Required"
                ui.fragmentRegisterRegisterTl.requestFocus()
                return@setOnClickListener
            }


            if (userPassword.isEmpty() || userPassword.length < 6) {
                ui.fragmentRegisterPasswordTl.error = "6 char password required"
                ui.fragmentRegisterPasswordTl.requestFocus()
                return@setOnClickListener
            }

            SessionManager.save(requireContext(), Constants.USEREMAIL, userEmail)
            registerUser(userEmail, userPassword)
        }

        return ui.root
    }


    private fun registerUser(email: String, password: String) {
        //progressbar.visibility = View.VISIBLE
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
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

    private fun gotoLoginScreen() {
       ui.alreadyRegisterTv.setOnClickListener {
           findNavController().navigate(R.id.loginFragment)
       }
    }

    override fun onStart() {
        super.onStart()
        auth.currentUser?.let {
            login()
        }
    }

}