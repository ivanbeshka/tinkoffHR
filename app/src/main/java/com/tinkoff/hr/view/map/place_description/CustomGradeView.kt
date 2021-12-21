package com.tinkoff.hr.view.map.place_description

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.tinkoff.hr.R

class CustomGradeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var views = listOf<View>()
    var rating = 0
        private set

    init {
        val root = inflate(context, R.layout.view_grade, this)
        views = root.findViewById<ConstraintLayout>(R.id.layout).children.toList()
        views.forEachIndexed { index, view ->
            setGradeItemClickListener(view, index)
        }
    }

    private fun setGradeItemClickListener(view: View, index: Int) {
        view.setOnClickListener {
            rating = index + 1
            views.subList(0, index + 1).forEach {
                setViewSelected(it)
            }

            views.subList(index + 1, views.size).forEach {
                setViewUnselected(it)

            }
        }
    }

    private fun setViewSelected(view: View) {
        view.setBackgroundResource(R.drawable.shape_pressed_grade)
    }

    private fun setViewUnselected(view: View) {
        view.setBackgroundResource(R.drawable.shape_unpressed_grade)
    }
}