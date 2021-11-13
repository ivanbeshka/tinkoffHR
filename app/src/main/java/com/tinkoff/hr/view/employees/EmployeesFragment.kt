package com.tinkoff.hr.view.employees

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tinkoff.hr.data.Employee
import com.tinkoff.hr.databinding.FragmentEmployeesBinding

class EmployeesFragment : Fragment(), EmployeesAdapter.OnItemClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEmployeesBinding.inflate(inflater, container, false)

        val employee = Employee("this_is_employee@gmail.com", "Иванов Александр Александрович")

        binding.rvEmployees.layoutManager = LinearLayoutManager(requireContext())
        binding.rvEmployees.adapter = EmployeesAdapter(
            listOf(
                employee,
                employee,
                employee,
                employee,
                employee,
                employee,
                employee,
                employee,
                employee,
                employee,
                employee
            ),
            this
        )

        return binding.root
    }

    override fun onItemClick(v: View, employeeEmail: String) {
        findNavController().navigate(EmployeesFragmentDirections.actionEmployeesToEmployeeDescFragment(employeeEmail))
    }
}