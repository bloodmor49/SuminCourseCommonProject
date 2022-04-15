package com.example.morozovhints.main

import android.annotation.SuppressLint
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.morozovhints.R

class MainRecyclerAdapter(private val listOfLessons: MutableList<LessonInfo>) :
    RecyclerView.Adapter<MainRecyclerAdapter.LessonsViewHolder>() {

    var onLessonClickListener: OnLessonClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_lessons_item, parent, false)
        return LessonsViewHolder(view)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: LessonsViewHolder, position: Int) {
        val lesson: LessonInfo = listOfLessons[position]
        with(holder) {
            textViewLessonNumber?.text = lesson.lessonNumber
            textViewLessonDescription?.text = lesson.lessonDescription

            itemView.setOnClickListener {
                onLessonClickListener?.onLessonClick(position)
            }
        }
    }



override fun getItemCount(): Int = listOfLessons.size

class LessonsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textViewLessonNumber: TextView? = itemView.findViewById(R.id.textViewLessonNumber)
    val textViewLessonDescription: TextView? =
        itemView.findViewById(R.id.textViewLessonDescription)
}

interface OnLessonClickListener {
    fun onLessonClick(position: Int)
}
}