package com.example.messenger.L72b

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.messenger.L72b.db.NotesContact
import com.example.messenger.L72b.db.NotesDBHelper
import com.example.messenger.R
import com.example.messenger.L72b.ViewsMainActivity

/**
 * Класс (активность) для добавления новой заметки.
 */
class AddNoteActivity : AppCompatActivity() {


    lateinit var editTextTitle: EditText
    lateinit var editTextDescription: EditText
    lateinit var spinnerDaysOfWeek: Spinner
    lateinit var radioGroupPriority: RadioGroup

    lateinit var dbHelper: NotesDBHelper
    lateinit var sqLiteDatabase: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_l71_add_note)

        editTextTitle = findViewById(R.id.editTextTitle)
        editTextDescription = findViewById(R.id.editTextDescription)
        spinnerDaysOfWeek = findViewById(R.id.spinnerDaysOfWeek)
        radioGroupPriority = findViewById(R.id.radioGroupPriority)

        dbHelper = NotesDBHelper(this)
        sqLiteDatabase = dbHelper.writableDatabase
    }

    fun onClickSaveNote(view: View) {
        var title = editTextTitle.text.toString().trim()
        var description = editTextDescription.text.toString().trim()
        var dayOfWeek = spinnerDaysOfWeek.selectedItem.toString()
        var radioButtonId = radioGroupPriority.checkedRadioButtonId
        var radioButton = findViewById<RadioButton>(radioButtonId)
        var priority = radioButton.text.toString().toInt()
        //val newNote = Note(title,description,dayOfWeek,priority)

        if (isFilled(title, description)) {
            //заполняем контейнер данными
            var contentValues = ContentValues()
            contentValues.put(NotesContact.COLUMN_TITLE, title)
            contentValues.put(NotesContact.COLUMN_DESCRIPTION, description)
            contentValues.put(NotesContact.COLUMN_DAY_OF_WEEK, dayOfWeek)
            contentValues.put(NotesContact.COLUMN_PRIORITY, priority)
            //засовываем набитый контейнер в таблицу
            sqLiteDatabase.insert(NotesContact.TABLE_NAME, null, contentValues)

            //ViewsMainActivity.Notes.listOfNotes.add(newNote)
            startActivity(Intent(this, ViewsMainActivity::class.java))

        } else Toast.makeText(this, "Все нужно заполнить", Toast.LENGTH_LONG).show()
    }

    private fun isFilled(title: String, description: String): Boolean =
        title.isNotEmpty() && description.isNotEmpty()
}