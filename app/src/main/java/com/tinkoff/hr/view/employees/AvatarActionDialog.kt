package com.tinkoff.hr.view.employees

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.tinkoff.hr.databinding.FragmentAvatarActionDialogBinding

class AvatarActionDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAvatarActionDialogBinding.inflate(inflater, container, false)

        return binding.root
    }

    companion object {
        const val TAG = "AvatarActionDialog"
    }
}