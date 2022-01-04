package com.example.persis;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.persis.models.Rule;

import java.util.ArrayList;


public class RuleAdapter extends  RecyclerView.Adapter<RuleAdapter.ViewHolder>{

    Context context;

    public RuleAdapter(Context context, ArrayList<Rule> rules) {
        this.context = context;
        this.rules = rules;
    }

    ArrayList<Rule> rules;

    @NonNull
    @Override
    public RuleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rule_list,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RuleAdapter.ViewHolder holder, int position) {
        Rule rule = rules.get(position);
        holder.title.setText(rule.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),EditRuleActivity.class);
                intent.putExtra("title",rule.getTitle());
                intent.putExtra("id",rule.getId());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rules.size();
    }

    public static  class ViewHolder extends  RecyclerView.ViewHolder{
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
        }
    }
}
