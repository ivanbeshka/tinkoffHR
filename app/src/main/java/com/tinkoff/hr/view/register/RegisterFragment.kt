package com.tinkoff.hr.view.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tinkoff.hr.R
import com.tinkoff.hr.databinding.FragmentRegisterBinding
import com.tinkoff.hr.utils.MyUserSharedPref
import com.tinkoff.hr.utils.showToast
import com.tinkoff.hr.viewmodels.AuthViewModel

class RegisterFragment : Fragment() {

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentRegisterBinding.inflate(inflater, container, false)

        binding.btnStartWork.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            if (email.isNotEmpty()
                && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
            ) {
                authViewModel.sendAuthLink(email).observe(viewLifecycleOwner) { state ->
                    state.on(
                        success = {
                            MyUserSharedPref(this.requireContext()).email = email
                            showToast(getString(R.string.login_link_sent_to_email))
                        },
                        error = {
                            showToast(getString(R.string.oops_something_went_wrong))
                        }
                    )
                }
            } else {
                showToast(getString(R.string.wrong_email))
            }
        }

        return binding.root
    }
}