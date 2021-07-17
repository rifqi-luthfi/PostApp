package com.example.postapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.postapp.database.AppDatabase
import com.example.postapp.database.PostApp
import kotlinx.android.synthetic.main.activity_insert_update.*

class InsertUpdateActivity : AppCompatActivity() {
    private var id: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_update)

        id = intent.getLongExtra("ID", 0 )

        if(id == 0L){
            tvInsertUpdateTitle.text = "Insert Post"
        }
        else{
            tvInsertUpdateTitle.text = "Update Post"

            val postApp = AppDatabase.getInstance(this).DaoPostApp().getById(id)
            etTitle.setText(postApp.title)
            etDescription.setText(postApp.description)
        }

        setListener()
    }

    private fun setListener() {
        fabSave.setOnClickListener{
            val message = Validate()
            if(message == ""){
                val title = etTitle.text.toString()
                val description = etDescription.text.toString()
                if (id == 0L){
                    insert(title, description)

                }
                else{
                    update(id, title, description)
                }
            }
            else{
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun insert(title: String, description: String) {
        val postApp = PostApp( 0, title, description)
        val id = AppDatabase.getInstance(this).DaoPostApp().insert(postApp)

        if(id>0) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            finish()
        }
        else {
            Toast.makeText(this, "Sorry, Please Try again", Toast.LENGTH_SHORT).show()
        }
    }

    private fun update(id: Long, title: String, description: String) {
        val postApp = PostApp(id, title, description)
        val line = AppDatabase.getInstance(this).DaoPostApp().update(postApp)
        if(line>0) {
            Toast.makeText(this, "Updated Success", Toast.LENGTH_SHORT).show()
            finish()
        }
        else {
            Toast.makeText(this, "Sorry, Please Try again", Toast.LENGTH_SHORT).show()
        }
    }

    private fun Validate(): String {
        val title = etTitle.text.toString()
        val description = etDescription.text.toString()

        return when{
            (title.isEmpty()) -> " Please input title !"
            (description.isEmpty()) -> " Please input description"
            else -> ""
        }

    }
}