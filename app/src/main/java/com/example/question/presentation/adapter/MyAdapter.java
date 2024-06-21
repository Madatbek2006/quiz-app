package com.example.question.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.question.R;
import com.example.question.data.model.CategoryData;
import com.example.question.data.model.MyShar;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> {
    ArrayList<CategoryData> data;
    public OnClickItem onClickItem;
    public MyAdapter(ArrayList<CategoryData> data){
        this.data=data;
    }
    @NonNull
    @Override
    public MyAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_questions2,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.Holder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        View view;
        ImageView image;
        TextView score;
        TextView name;

        void  bind(CategoryData item){
            image.setBackgroundResource(item.image);
            score.setText(MyShar.getInfo(item.name)+"");
            name.setText(item.name);
            view.setOnClickListener(v->{
                onClickItem.category(item.name);
            });
        }

        public Holder(@NonNull View itemView) {
            super(itemView);
            view=itemView.findViewById(R.id.start);
           image=itemView.findViewById(R.id.image);
            score=itemView.findViewById(R.id.your_best_score);
           name=itemView.findViewById(R.id.name);
        }
    }
}
