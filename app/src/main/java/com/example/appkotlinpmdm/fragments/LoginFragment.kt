package com.example.appkotlinpmdm.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.appkotlinpmdm.MainActivity
import com.example.appkotlinpmdm.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController
    lateinit var auth: FirebaseAuth
    lateinit var edTxtUser: EditText
    lateinit var edTxtPassword: EditText
    lateinit var buttonRegister: Button
    lateinit var buttonLogin: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = findNavController()
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)

        auth = Firebase.auth

        buttonRegister = v.findViewById(R.id.buttonRegister_login)
        buttonLogin = v.findViewById(R.id.buttonLogin)
        edTxtUser = v.findViewById(R.id.etxtUser)
        edTxtPassword = v.findViewById(R.id.etxtPassword)

        buttonRegister.setOnClickListener(this)
        buttonLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonRegister_login -> {
                navController.navigate(R.id.action_loginFragment_to_registerFragment)
            }
            R.id.buttonLogin -> {
                val email: String = edTxtUser.text.toString()
                val password: String = edTxtPassword.text.toString()

                if (email.isNotEmpty() && password.isNotEmpty()) {
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(requireActivity()) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("OnBoarding", "signInWithEmail:success")
                                val user = auth.currentUser
                                val mainActivity: Intent =
                                    Intent(requireActivity(), MainActivity::class.java)
                                startActivity(mainActivity)
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("OnBoarding", "signInWithEmail:failure", task.exception)
                                Snackbar.make(
                                    requireView(),  // Use requireView() to get the root view of the fragment
                                    "El usuario no existe.",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    Snackbar.make(
                        requireView(),
                        "Introduce usuario y contraseña.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}