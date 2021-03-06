package com.example.postapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postapp.database.PostResponse
import kotlinx.android.synthetic.main.list_postapp_item.view.*

class PostAppAdapter(private val list: ArrayList<PostResponse>): RecyclerView.Adapter<PostAppAdapter.PostViewHolder>() {
    private var listener: ((PostResponse) -> Unit)? = null

    inner class  PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(postResponse: PostResponse) {
            itemView.apply {
                tvPostAppTitle.text = postResponse.title
                tvPostAppDesc.text = postResponse.body
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_postapp_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size


    fun updateData(newList: List<PostResponse>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }


    fun setOnClickListener(listener: (PostResponse) -> Unit){
        this.listener = listener
    }

}