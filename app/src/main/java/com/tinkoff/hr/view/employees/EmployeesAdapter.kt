package com.tinkoff.hr.view.employees

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tinkoff.hr.R
import com.tinkoff.hr.data.Employee
import com.tinkoff.hr.databinding.ItemEmployeeBinding

class EmployeesAdapter(private val data: List<Employee>, private val listener: OnItemClickListener) : RecyclerView.Adapter<EmployeesAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemEmployeeBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(employee: Employee){
            binding.employee = employee
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemEmployeeBinding = DataBindingUtil.inflate(inflater, R.layout.item_employee, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val employee = data[position]
        holder.bind(employee)

        holder.binding.itemEmployeeLayout.setOnClickListener {
            listener.onItemClick(holder.itemView, employee.email)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface OnItemClickListener{
        fun onItemClick(v: View, employeeEmail: String)
    }
}