package com.example.todoapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.clicklisteners.ItemClickListener
import com.example.todoapp.model.Notes

class NotesAdapter(val list: List<Notes>, val itemClickListener: ItemClickListener) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    //private val listnotes: List<Notes>?
    //private val itemClickListener: ItemClickListener
    /*public void setListnotes(List<Notes> list){
       this.listnotes=list;
   }*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notes_adapter_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesAdapter.ViewHolder, position: Int) { //we set data from list to layout here
        val notes = list[position]
        val ttle = notes.title
        val desc = notes.desc
        holder.textView_ttle.text = ttle
        Log.d("TAGVAL", "ttle$ttle")
        Log.d("TAGVAL", "ttle$desc")
        holder.textView_desc.text = desc
        holder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                itemClickListener.OnClick(notes)
            }

        })
    }

    override fun getItemCount(): Int {
        return list.size ?:0
        //see elvs
    }

   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView_ttle: TextView=itemView.findViewById(R.id.tv_vttle)
        var textView_desc: TextView=itemView.findViewById(R.id.tv_vdesc)


    }

}