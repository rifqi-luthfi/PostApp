package com.example.postapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postapp.database.PostApp
import kotlinx.android.synthetic.main.list_postapp_item.view.*

class PostAppAdapter : RecyclerView.Adapter<PostAppAdapter.PostAppHolder>() {
    private var listener: ((PostApp) -> Unit)? = null
    private var deleteListener: ((PostApp) -> Unit)? = null
    private val postList = arrayListOf<PostApp>()
    inner class PostAppHolder(private val v: View) : RecyclerView.ViewHolder(v){
        fun bind(postApp: PostApp) {
            v.apply {
                tvPostAppTitle.text = postApp.title
                tvPostAppDesc.text = postApp.description
            }
            v.rootView.setOnClickListener {
                listener?.invoke(postApp)
            }
            v.icTrash.setOnClickListener{
                deleteListener?.invoke(postApp)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAppHolder {
        return PostAppHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_postapp_item, parent, false
        ))
    }

    override fun onBindViewHolder(holder: PostAppHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int = postList.size

    fun updateData(newList: List<PostApp>) {
        postList.clear()
        postList.addAll(newList)
        notifyDataSetChanged()
    }

    fun setOnClickListener(listener: (PostApp) -> Unit) {
        this.listener = listener
    }

    fun setOnDeleteListener(deleteListener: (PostApp) -> Unit){
        this.deleteListener = deleteListener
    }
}