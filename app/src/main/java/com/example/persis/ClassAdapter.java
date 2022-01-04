package com.example.persis;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {
    Context context;
    ArrayList id, title;
    ClassAdapter(Context context,ArrayList id,ArrayList title){
        this.context = context;
        this.id = id;
        this.title = title;
    }

    @NonNull
    @Override
    public ClassAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.class_row,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id_txt.setText(String.valueOf(id.get(position)));
        holder.title_txt.setText(String.valueOf(title.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),EditClassActivity.class);
                intent.putExtra("id",String.valueOf(id.get(holder.getBindingAdapterPosition())));
                intent.putExtra("title",String.valueOf(title.get(holder.getBindingAdapterPosition())));
                v.getContext().startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return id.size();
    }

    public static  class ViewHolder extends  RecyclerView.ViewHolder{
        TextView id_txt,title_txt;
        LinearLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.class_id);
            title_txt = itemView.findViewById(R.id.class_title);
            mainLayout=itemView.findViewById(R.id.mainLayout);

        }
    }
}
