package com.tinkoff.hr.view.employees

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.tinkoff.hr.R
import com.tinkoff.hr.data.Employee
import com.tinkoff.hr.databinding.FragmentEmployeeDescBinding
import java.text.SimpleDateFormat
import java.util.*

class EmployeeDescFragment : Fragment() {

    private val args: EmployeeDescFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEmployeeDescBinding.inflate(inflater, container, false)
        setEmployee(binding)
        setEditableListeners(binding)


        return binding.root
    }

    private fun setEditableListeners(binding: FragmentEmployeeDescBinding) {
        setEditableOnClick(binding.tilProject, binding.etProject)
        setEditableOnClick(binding.tilAchievement, binding.etAchievement)
        setEditableOnClick(binding.tilCompanyPosition, binding.etCompanyPosition)
        setEditableOnClick(binding.tilStatus, binding.etStatus)
        setEditableOnClick(binding.tilTableNum, binding.etTableNum)
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

    private fun setEmployee(binding: FragmentEmployeeDescBinding) {
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        binding.employee = Employee(
            args.employeeEmail,
            "Иванов Александр Александрович",
            null,
            "Мне совершенно не важно, что думают обо мне люди. Все мои поступки, будь они плохие или хорошие, обдуманные или спонтанные и есть моя жизнь. Я рискую, прощаю, люблю, ненавижу, радуюсь, огорчаюсь, иногда забегаю вперед, иногда боюсь и не решаюсь. Жизнь дана мне один раз. И я живу так, как я хочу!",
            "Tinkoff HR",
            "Разработчик",
            currentDate,
            currentDate,
            "1024",
            "Работаю над проектом",
            "Спикер"
        )
    }
}