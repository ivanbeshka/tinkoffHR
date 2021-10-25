package com.tinkoff.hr

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class RegisterFragment : Fragment(){

    private lateinit var etEmail: TextInputEditText
    private lateinit var btnStartWork: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        initViews(view)

        btnStartWork.setOnClickListener {
            val email = etEmail.text.toString()
            if (email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                findNavController().navigate(R.id.registerCodeFragment)
            } else {
                Toast.makeText(context, "Неправильный E-mail!", Toast.LENGTH_LONG).show()
            }
        }

        return view
    }

    private fun initViews(view: View){
        etEmail = view.findViewById(R.id.et_email)
        btnStartWork = view.findViewById(R.id.btn_start_work)
    }
}