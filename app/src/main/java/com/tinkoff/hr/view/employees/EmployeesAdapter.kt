package com.tinkoff.hr.view.employees

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tinkoff.hr.data.Employee
import com.tinkoff.hr.databinding.ItemEmployeeBinding

class EmployeesAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<EmployeesAdapter.ViewHolder>() {

    private val differ = AsyncListDiffer(this, EmployeeDiffUtilCallback())
    var data: List<Employee>
        get() = differ.currentList
        set(value) = differ.submitList(value)


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
            listener.onItemClick(employee)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private class EmployeeDiffUtilCallback : DiffUtil.ItemCallback<Employee>() {
        override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem.email == newItem.email
        }

        override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onItemClick(employee: Employee)
    }
}