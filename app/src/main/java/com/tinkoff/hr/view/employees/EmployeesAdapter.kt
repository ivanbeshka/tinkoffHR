package com.tinkoff.hr.view.employees

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tinkoff.hr.data.Employee
import com.tinkoff.hr.databinding.ItemEmployeeBinding

class EmployeesAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<EmployeesAdapter.ViewHolder>() {
    private var data: List<Employee> = listOf()

    class ViewHolder(val binding: ItemEmployeeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEmployeeBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val employee = data[position]
        holder.binding.employee = employee

        holder.binding.itemEmployeeLayout.setOnClickListener {
            listener.onItemClick(holder.itemView, employee.email)
        }
    }

    fun setData(employees: List<Employee>) {
        data = employees
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnItemClickListener {
        fun onItemClick(v: View, employeeEmail: String)
    }
}