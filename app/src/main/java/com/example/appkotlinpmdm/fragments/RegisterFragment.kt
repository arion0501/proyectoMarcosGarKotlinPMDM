package com.example.appkotlinpmdm.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
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

class RegisterFragment : Fragment(), OnClickListener {

    lateinit var navController: NavController
    lateinit var auth: FirebaseAuth
    lateinit var edTxtUser: EditText
    lateinit var edTxtPassword: EditText
    lateinit var buttonRegister: Button
    lateinit var buttonCancelar: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = findNavController()
        return inflater.inflate(R.layout.register_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        buttonRegister = view.findViewById(R.id.buttonRegister)
        edTxtUser = view.findViewById(R.id.etxtUser)
        edTxtPassword = view.findViewById(R.id.etxtPassword)
        buttonCancelar = view.findViewById(R.id.buttonCancelar) // Agrega esta línea

        buttonRegister.setOnClickListener(this)
        buttonCancelar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonCancelar -> {
                navController.navigate(R.id.action_registerFragment_to_loginFragment)
            }
            R.id.buttonRegister -> {
                val email: String = edTxtUser.text.toString()
                val password: String = edTxtPassword.text.toString()

                if (email.isNotEmpty() && password.isNotEmpty()) {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(requireActivity()) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("OnBoarding", "createUserWithEmail:success")
                                navController.navigate(R.id.action_registerFragment_to_loginFragment)
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("OnBoarding", "createUserWithEmail:failure", task.exception)
                                Snackbar.make(
                                    v,
                                    "Autenticación fallida.",
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