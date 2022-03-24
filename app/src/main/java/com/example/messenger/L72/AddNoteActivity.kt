package com.example.messenger.L72

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.messenger.L72.RoomDB.Note
import com.example.messenger.L72.RoomDB.NotesDatabase
import com.example.messenger.R

/**
 * Класс (активность) для добавления новой заметки.
 */
class AddNoteActivity : AppCompatActivity() {


    lateinit var editTextTitle: EditText
    lateinit var editTextDescription: EditText
    lateinit var spinnerDaysOfWeek: Spinner
    lateinit var radioGroupPriority: RadioGroup
    lateinit var database: NotesDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_l71_add_note)

        editTextTitle = findViewById(R.id.editTextTitle)
        editTextDescription = findViewById(R.id.editTextDescription)
        spinnerDaysOfWeek = findViewById(R.id.spinnerDaysOfWeek)
        radioGroupPriority = findViewById(R.id.radioGroupPriority)

        database = NotesDatabase.getInstance(this)!!

    }

    fun onClickSaveNote(view: View) {
        var title = editTextTitle.text.toString().trim()
        var description = editTextDescription.text.toString().trim()
        var dayOfWeek = spinnerDaysOfWeek.selectedItem.toString()
        var radioButtonId = radioGroupPriority.checkedRadioButtonId
        var radioButton = findViewById<RadioButton>(radioButtonId)
        var priority = radioButton.text.toString().toInt()

            //ViewsMainActivity.Notes.listOfNotes.add(newNote)
            startActivity(Intent(this, ViewsMainActivity::class.java))
        if (isFilled(title,description)) {
            var note = Note(title= title, description = description, dayOfWeek = dayOfWeek, priority = priority)
            database.notesDao().insertNote(note)
            startActivity(Intent(this,ViewsMainActivity::class.java))
        } else Toast.makeText(this,"Заполни поля",Toast.LENGTH_LONG).show()
    }

    private fun isFilled(title: String, description: String): Boolean =
        title.isNotEmpty() && description.isNotEmpty()
}