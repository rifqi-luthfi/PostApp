package com.example.postapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postapp.database.PostResponse
import com.example.postapp.database.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val list = ArrayList<PostResponse>()

    private val postAdapter: PostAppAdapter by lazy { PostAppAdapter(list) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setAdapter()
        showPosts()
        setListener()
    }

    private fun setAdapter(){
        postAdapter.setOnClickListener {
            navigate(it.id)
        }

    }

    private fun showPosts(){
        rvRecyclerView.setHasFixedSize(true)
        rvRecyclerView.layoutManager =  LinearLayoutManager( this)

        RetrofitClient.instance.getPosts().enqueue(object: Callback<ArrayList<PostResponse>> {
            override fun onResponse(
                call: Call<ArrayList<PostResponse>>,
                response: Response<ArrayList<PostResponse>>
            ) {

                response.body()?.let { list.addAll(it)}
                val adapter =PostAppAdapter(list)
                rvRecyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<PostResponse>>, t: Throwable) {

            }
        })
    }

    private fun navigate (id: String?){
        println(id)
        val intent = Intent(this, InsertUpdateActivity::class.java)
        println("checked")
        //intent.putExtra("id",id)
        startActivity(intent)
    }

    private fun setListener(){
        fabAdd.setOnClickListener{
            navigate("0")
            println("checked")
        }
    }

}