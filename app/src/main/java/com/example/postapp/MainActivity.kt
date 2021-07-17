package com.example.postapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postapp.database.AppDatabase
import com.example.postapp.database.PostApp
import kotlinx.android.synthetic.main.activity_insert_update.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val postAppAdapter: PostAppAdapter by lazy { PostAppAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setAdapter()
        loadData()
        setListener()


    }

    private fun setAdapter() {

        postAppAdapter.setOnClickListener {
            navigate(it.id)
        }
        postAppAdapter.setOnDeleteListener {
            AlertDialog.Builder(this )
                .setTitle("Delete")
                .setMessage("are you sure delete?")
                .setPositiveButton("OK"){
                    dialog, _ ->
                        AppDatabase.getInstance(this).DaoPostApp().delete(it)
                        Toast.makeText(this, "Delete Success", Toast.LENGTH_SHORT).show()
                        loadData()
                        dialog.dismiss()
                }
                .setNegativeButton("NO"){
                    dialog, _ -> dialog.dismiss()
                }
                .show()
        }
        rvRecyclerView.layoutManager = LinearLayoutManager(this)
        rvRecyclerView.adapter = postAppAdapter

    }
    
    private fun loadData() {
        val postList = AppDatabase.getInstance(this).DaoPostApp().get()
        postAppAdapter.updateData(postList)
    }

    private fun setListener() {
        fabAdd.setOnClickListener {
            navigate(0 )
        }
    }
    
    private fun navigate(id: Long){
        val intent = Intent(this, InsertUpdateActivity::class.java)
        intent.putExtra("ID", id )
        startActivity(intent)
    }
}