package com.example.messenger

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainRecyclerAdapter(private val listOfLessons: MutableList<LessonInfo>) :
    RecyclerView.Adapter<MainRecyclerAdapter.LessonsViewHolder>() {

    var onNoteClickListener: OnNoteClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_lessons_item, parent, false)
        return LessonsViewHolder(view)
    }

    override fun onBindViewHolder(holder: LessonsViewHolder, position: Int) {
        val lesson: LessonInfo = listOfLessons[position]
        with(holder) {
            textViewLessonNumber?.text = lesson.lessonNumber
            textViewLessonDescription?.text = lesson.lessonDescription

            itemView.setOnClickListener {
                onNoteClickListener?.onLessonClick(adapterPosition)
            }
        }
    }

    override fun getItemCount(): Int = listOfLessons.size

    class LessonsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewLessonNumber: TextView? = itemView.findViewById(R.id.textViewLessonNumber)
        val textViewLessonDescription: TextView? =
            itemView.findViewById(R.id.textViewLessonDescription)
    }

    interface OnNoteClickListener {
        fun onLessonClick(position: Int)
    }
}