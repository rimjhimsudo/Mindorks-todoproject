package com.example.todoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.todoapp.R
import com.example.todoapp.clicklisteners.ItemClickListener
import com.example.todoapp.model.Data

class BlogAdapter (val list: List<Data>) : RecyclerView.Adapter<BlogAdapter.ViewHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogAdapter.ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.blog_layout_item,parent,false)

        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BlogAdapter.ViewHolder, position: Int) {
        val blog=list[position]
        /*val blog_title= blog.title
        val blog_desc=blog.description
        val blog_image_url=blog.imag_url
        val blog_url=blog.blog_url*/
        Glide.with(holder.itemView).load(blog.img_url).into(holder.imageView)
        holder.tv_blogtitle.text=blog.title
        holder.tv_blogdesc.text=blog.description

    }
    inner class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val tv_blogtitle: TextView =itemView.findViewById(R.id.tv_blogtitle)
        val tv_blogdesc: TextView=itemView.findViewById(R.id.tv_blogdesc)
        val  imageView : ImageView =itemView.findViewById(R.id.blogimgvw)
    }
}