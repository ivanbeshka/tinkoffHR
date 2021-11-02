package com.tinkoff.hr.view.faq

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tinkoff.hr.R
import com.tinkoff.hr.data.FAQ

class FAQFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_faq, container, false)
        initViews(view)

        val faq = FAQ(
            "Клиники по ДМС", "Номер  полиса должен быть у тебя на почте (отправитель allianz);\n" +
                    "Регистрация на сайте обязательна: https://allianz.ru/, потому что в личном кабинете будут все условия программы +клиники; \n" +
                    "Если есть клиника, в которую ты ходишь, но ее нет в переченье, то пиши сюда tcr_dms@tinkoff.ru;"
        )

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = FAQAdapter(
            listOf(faq, faq, faq, faq, faq, faq, faq, faq, faq, faq, faq)
        )

        return view
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.rv_faq)
    }
}