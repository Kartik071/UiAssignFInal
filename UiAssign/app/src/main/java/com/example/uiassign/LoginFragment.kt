package com.example.uiassign

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.uiassign.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Pattern

class LoginFragment : Fragment() {
    private lateinit var bindingLogin: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingLogin =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_login, container, false)

        clickHandling()
        return bindingLogin.root
    }

    private fun clickHandling() {
        bindingLogin.createTxt.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, RegisterFragment()).addToBackStack("").commit()

        }
//        bindingLogin.loginButton.setOnClickListener {
//
//
//        }
        bindingLogin.forgotPass.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ForgotPassword()).addToBackStack("").commit()

        }
        bindingLogin.loginButton.setOnClickListener {
            if (checkAllFields()) {
                if (validate(bindingLogin.email.text.toString().trim())) {
                    Toast.makeText(requireContext(), "Successful Login", Toast.LENGTH_SHORT).show()
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, HomeFragment()).addToBackStack("").commit()
                } else
                    Snackbar.make(bindingLogin.root, "Enter A Valid Email ", Snackbar.LENGTH_SHORT)
                        .show()

            }
        }

    }

    private fun validate(email: String): Boolean {

        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()

    }

    private fun checkAllFields(): Boolean {
        if (bindingLogin.email.text.isNullOrEmpty()) {
            bindingLogin.email.error = "Required Field"
            return false
        }
        if (bindingLogin.password.text.isNullOrEmpty()) {
            bindingLogin.password.error = "Required Field"
            return false
        }
        return true
    }


}