package com.example.uiassign

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.uiassign.databinding.FragmentForgotPasswordBinding
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Pattern

class ForgotPassword : Fragment() {
    lateinit var bindingForgot: FragmentForgotPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingForgot = DataBindingUtil.inflate(layoutInflater, R.id.fragmentContainer, container, false)
        clickHandle()

        return bindingForgot.root
    }

    private fun clickHandle() {
        bindingForgot.otpBtn.setOnClickListener {
            if (checkAllFields()){
                if (validate(bindingForgot.email.text.toString().trim())){
                    Snackbar.make(bindingForgot.root,"OTP SENT", Snackbar.LENGTH_SHORT).show()
                }
                else
                    Snackbar.make(bindingForgot.root,"Enter A Valid Email",Snackbar.LENGTH_LONG).show()
            }
        }
        bindingForgot.loginButton.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,LoginFragment()).addToBackStack("").commit()
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
        return if (bindingForgot.email.text.isNullOrEmpty()) {
            bindingForgot.email.error = "Required Field"
            false
        } else
            true

    }
}
