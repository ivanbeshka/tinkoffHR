package com.tinkoff.hr.view.employees

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.tinkoff.hr.R
import com.tinkoff.hr.data.Employee
import com.tinkoff.hr.databinding.FragmentEmployeeDescBinding
import com.tinkoff.hr.viewmodels.EmployeesViewModel
import java.text.SimpleDateFormat
import java.util.*

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
        setEditableOnClick(binding.tilStatus, binding.etStatus)
        setEditableOnClick(binding.tilAboutMe, binding.etAboutMe)
    }

    private fun setEditableOnClick(til: TextInputLayout, et: TextInputEditText) {
        var isEdit = false
        til.setEndIconOnClickListener {
            if (!isEdit) {
                et.isCursorVisible = true
                et.isFocusableInTouchMode = true
                et.isFocusable = true
                et.requestFocus()
                til.setEndIconDrawable(R.drawable.ic_check)
                isEdit = !isEdit
            } else {
                et.isCursorVisible = false
                et.isFocusable = false
                til.setEndIconDrawable(R.drawable.ic_create)
                isEdit = !isEdit
            }
        }
    }
}