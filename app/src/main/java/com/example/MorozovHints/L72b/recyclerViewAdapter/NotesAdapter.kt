package com.example.MorozovHints.L72b.recyclerViewAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.MorozovHints.L72b.Note
import com.example.MorozovHints.R

/**
 * Адаптер для перевода note в recyclerView.
 */
class NotesAdapter(private val listOfNotes: MutableList<Note>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    var onNoteClickListener: OnNoteClickListener? = null

    /* 2. Создает новый объект ViewHolder всякий раз, когда RecyclerView нуждается в этом.
     Это тот момент, когда создаётся layout строки списка, передается объекту ViewHolder,
     и каждый дочерний view-компонент может быть найден и сохранен.     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.note_item_l71, parent, false)
        return NotesViewHolder(view)
    }

    //3. Принимает объект ViewHolder и устанавливает необходимые данные для соответствующей строки во view-компоненте
    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        var note: Note = listOfNotes[position]

        with(holder) {
            textViewTitle.text = note.title
            textViewDescription.text = note.description
            textViewDayOfWeek.text = note.dayOfWeek

            itemView.setOnClickListener {
                onNoteClickListener?.onNoteClick(adapterPosition)
            }

            itemView.setOnLongClickListener {
                onNoteClickListener?.onNoteLongClick(adapterPosition)
                true
            }
        }

        var colorId: Int
        var priority = note.priority
        colorId = when (priority) {
            1 -> holder.itemView.resources.getColor(android.R.color.holo_red_light)
            2 -> holder.itemView.resources.getColor(android.R.color.holo_orange_light)
            else -> holder.itemView.resources.getColor(android.R.color.holo_green_light)
        }
        holder.textViewTitle.setBackgroundColor(colorId)

    }

    //4. Определяет количество элементов в массиве.
    override fun getItemCount(): Int = listOfNotes.size

    /*  1. Создаем ViewHolder - содержит все объекты View
          ViewHolder предотвращает ненужные вызовы findViewById().
          Вызывается метод onCreateViewHolder() и объекты ViewHolder создаются для нескольких первых
          view-компонентов, а затем они переиспользуются, и адаптер просто привязывает данные
          при помощи метода onBindViewHolder(). Благодаря этому список очень эффективен,
          и пользователь может прокручивать список плавно, потому что наиболее тяжёлые операции
          (создание и поиск элементов view-компонентов) происходят внутри метода onCreateViewHolder()
        */
    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle = itemView.findViewById<TextView>(R.id.textViewTitle)
        val textViewDescription = itemView.findViewById<TextView>(R.id.textViewDescription)
        val textViewDayOfWeek = itemView.findViewById<TextView>(R.id.textViewDayOfWeek)
    }

    interface OnNoteClickListener {
        fun onNoteClick(position: Int)
        fun onNoteLongClick(position: Int)
    }
}
