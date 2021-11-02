package com.tinkoff.hr.view.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.chaos.view.PinView
import com.tinkoff.hr.R
import com.tinkoff.hr.viewmodels.RegisterViewModel

class RegisterCodeFragment : Fragment() {

    private val registerViewModel: RegisterViewModel by activityViewModels()

    private lateinit var etCode: PinView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register_code, container, false)
        initViews(view)

        etCode.addTextChangedListener {
            val code = it.toString()
            if (code.length == 4){
                registerViewModel.setCode(code)
            }
        }

        return view
    }

    private fun initViews(view: View) {
        etCode = view.findViewById(R.id.et_code)
    }
}