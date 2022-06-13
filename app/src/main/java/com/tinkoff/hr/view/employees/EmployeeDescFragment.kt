package com.tinkoff.hr.view.employees

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.tinkoff.hr.R
import com.tinkoff.hr.databinding.FragmentEmployeeDescBinding
import com.tinkoff.hr.utils.showToast
import com.tinkoff.hr.viewmodels.EmployeesViewModel

class EmployeeDescFragment : Fragment() {

    private val employeesViewModel: EmployeesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEmployeeDescBinding.inflate(inflater, container, false)

        employeesViewModel.getCurrentEmployee().observe(viewLifecycleOwner) {
            binding.employee = it
        }

        setEditableListeners(binding)

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.ivAvatar.setOnLongClickListener {
            AvatarActionDialog().show(childFragmentManager, AvatarActionDialog.TAG)
            return@setOnLongClickListener true
        }

        return binding.root
    }

    private fun setEditableListeners(binding: FragmentEmployeeDescBinding) {
        setEditableOnClick(binding.tilStatus, binding.etStatus, onUpdateStatus)
        setEditableOnClick(binding.tilAboutMe, binding.etAboutMe, onUpdateBio)
    }

    private val onUpdateStatus: (String) -> Unit = { status ->
        employeesViewModel.setStatus(status)
            .observe(viewLifecycleOwner) { state ->
                state.on(
                    error = {
                        showToast(getString(R.string.oops_something_went_wrong))
                    }
                )
            }
    }

    private val onUpdateBio: (String) -> Unit = { bio ->
        employeesViewModel.setBio(bio)
            .observe(viewLifecycleOwner) { state ->
                state.on(
                    error = {
                        showToast(getString(R.string.oops_something_went_wrong))
                    }
                )
            }
    }

    private fun setEditableOnClick(
        til: TextInputLayout,
        et: TextInputEditText,
        onEdited: (text: String) -> Unit
    ) {
        var isEdit = false
        til.setEndIconOnClickListener {
            isEdit = !isEdit

            if (isEdit) {
                et.isCursorVisible = true
                et.isFocusableInTouchMode = true
                et.isFocusable = true
                et.requestFocus()
                til.setEndIconDrawable(R.drawable.ic_check)
            } else {
                et.isCursorVisible = false
                et.isFocusable = false
                til.setEndIconDrawable(R.drawable.ic_create)

                onEdited(et.text.toString())
            }
        }
    }
}


