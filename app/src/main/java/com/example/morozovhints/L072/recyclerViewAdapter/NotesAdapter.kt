package com.example.morozovhints.L072.recyclerViewAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.morozovhints.L072.RoomDB.Note
import com.example.morozovhints.R

/**
 * Адаптер для перевода note в recyclerView. В него забрасываем лист с записками, а на выходе
 * получаем адаптированные данные для recycler View.
 */
class NotesAdapter(private val listOfNotes: MutableList<Note>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    //Создаем экземпляр интерфейса onNoteClickListener
    var onNoteClickListener: OnNoteClickListener? = null

     /** 2. Создает новый объект ViewHolder всякий раз, когда RecyclerView нуждается в этом.
     Это тот момент, когда создаётся layout строки списка, передается объекту ViewHolder,
     и каждый дочерний view-компонент может быть найден и сохранен.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        //LayoutInflater – это класс, который умеет из содержимого layout-файла (XML) создать View-элемент.
        // 1) берем LI из контекста (окно где используется элемент) все XML ресурс.
        // 2) Делаем из XML ресурса элемент View с помощью inflate, где указываем куда все кидать.
        // 3) Добавляем полученный VIEW элемент в VIEWHOLDER.
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.note_item_l71, parent, false)
        return NotesViewHolder(view)
        // ИТОГИ - эта штука добавляет новую карточку каждый раз, когда это необходимо.
    }

    /** 3. Принимает объект ViewHolder и устанавливает необходимые данные для соответствующей
    строки во view-компоненте. Для работы заносим ViewHolder, а также позицию. Короче эта штука
    задает значения вновь созданной записке на экране от [onCreateViewHolder].
    */
    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        //Берем конкретную записку.
        var note: Note = listOfNotes[position]

        //Задаем ей данные.
        with(holder) {
            textViewTitle.text = note.title
            textViewDescription.text = note.description
            textViewDayOfWeek.text = note.dayOfWeek

            //задаем ей функцию нажатий. В данном случае мы смотри на один конкретный объект.
            itemView.setOnClickListener {
                //фактически переопределяем метод setOnClickListener на onNoteClick.
                onNoteClickListener?.onNoteClick(adapterPosition)
            }

            itemView.setOnLongClickListener {
                onNoteClickListener?.onNoteLongClick(adapterPosition)
                true
            }
        }

        //Работа с цветом для приоритета.
        var colorId: Int
        var priority = note.priority
        colorId = when (priority) {
            1 -> holder.itemView.resources.getColor(android.R.color.holo_red_light)
            2 -> holder.itemView.resources.getColor(android.R.color.holo_orange_light)
            else -> holder.itemView.resources.getColor(android.R.color.holo_green_light)
        }
        holder.textViewTitle.setBackgroundColor(colorId)

    }

    /** 4. Определяет количество элементов в массиве.
     */
    override fun getItemCount(): Int = listOfNotes.size

    /** 1. Создаем ViewHolder - содержит все объекты View .
        ViewHolder предотвращает ненужные вызовы findViewById() = лучше оптимизация.
    */
    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //определяем все видимые элементы на XML модели.
        val textViewTitle = itemView.findViewById<TextView>(R.id.textViewTitle)
        val textViewDescription = itemView.findViewById<TextView>(R.id.textViewDescription)
        val textViewDayOfWeek = itemView.findViewById<TextView>(R.id.textViewDayOfWeek)
    }

    /**
     * Интерфейс для определения что делать при нажатии на записку. Есть обычный клик и долгий.
     */
    interface OnNoteClickListener {
        fun onNoteClick(position: Int)
        fun onNoteLongClick(position: Int)
    }
}
