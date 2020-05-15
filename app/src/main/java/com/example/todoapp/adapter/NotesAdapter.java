package com.example.todoapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.clicklisteners.ItemClickLstener;
import com.example.todoapp.model.Notes;

import org.w3c.dom.Text;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    private List<Notes> listnotes;
    private ItemClickLstener itemClickLstener;
    public NotesAdapter(List<Notes> list , ItemClickLstener itemClickLstener){
        this.listnotes=list;
        this.itemClickLstener=itemClickLstener;
   }

   /*public void setListnotes(List<Notes> list){
       this.listnotes=list;
   }*/

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_adapter_layout,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        //we set data from list to layout here
        final Notes notes= listnotes.get(position);
        String ttle= notes.getTitle();
        String desc=notes.getDesc();
        holder.textView_ttle.setText(ttle);
        Log.d("TAGVAL","ttle"+ttle);
        Log.d("TAGVAL","ttle"+desc);
        holder.textView_desc.setText(desc);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickLstener.onClick(notes);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (listnotes!=null)
            return listnotes.size();
        else
            return 0;
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView_ttle, textView_desc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_ttle=itemView.findViewById(R.id.tv_vttle);
            textView_desc=itemView.findViewById(R.id.tv_vdesc);
        }
    }
}
