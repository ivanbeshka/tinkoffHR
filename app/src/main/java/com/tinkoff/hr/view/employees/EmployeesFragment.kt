package com.tinkoff.hr.view.employees

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tinkoff.hr.data.Employee
import com.tinkoff.hr.databinding.FragmentEmployeesBinding
import com.tinkoff.hr.utils.showToast
import com.tinkoff.hr.viewmodels.EmployeesViewModel

class EmployeesFragment : Fragment(), EmployeesAdapter.OnItemClickListener {

    private val employeesViewModel: EmployeesViewModel by activityViewModels()

    private val employeesAdapter = EmployeesAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEmployeesBinding.inflate(inflater, container, false)

        employeesViewModel.getEmployees().observe(viewLifecycleOwner) { state ->
            state.on(
                success = {
                    binding.employee = it.first()
                    employeesAdapter.data = it
                },
                error = { throwable ->
                    throwable.message?.let {
                        showToast(it)
                    }
                }
            )
        }

        binding.rvEmployees.adapter = employeesAdapter

        binding.containerMy.setOnClickListener {

            binding.employee?.let { employee ->
                onItemClick(employee)
            }
        }

        return binding.root
    }

    override fun onItemClick(employee: Employee) {
        findNavController().navigate(EmployeesFragmentDirections.actionEmployeesToEmployeeDescFragment())
        employeesViewModel.setCurrentEmployee(employee)
    }
}