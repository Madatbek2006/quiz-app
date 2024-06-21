package com.example.question.presentation.category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.question.R;
import com.example.question.data.model.CategoryData;
import com.example.question.data.model.CategoryEnum;
import com.example.question.data.model.MyShar;
import com.example.question.presentation.adapter.MyAdapter;
import com.example.question.presentation.adapter.OnClickItem;
import com.example.question.presentation.main.MainActivity;
import com.example.question.utils.StatusBarUtil;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity implements CategoryContract.View{
    private CategoryContract.Presenter presenter;
    private ArrayList<CategoryData> data;
    private MyAdapter adapter;
    private RecyclerView recyclerView;
    private int finishCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        data=new ArrayList<>();
        adapter=new MyAdapter(data);
        Log.d("TTT","salom");
        MyShar.init(this);

        getWindow().setStatusBarColor(Color.BLACK);
        presenter=new CategoryPresenter(this);
        recyclerView=findViewById(R.id.recyclerView);
        initAdapter();
    }
    private void initAdapter(){
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        data.add(new CategoryData(R.drawable.math,"Math"));
        data.add(new CategoryData(R.drawable.java,"Program"));
        data.add(new CategoryData(R.drawable.history,"History"));
        data.add(new CategoryData(R.drawable.physics,"Physics"));
        data.add(new CategoryData(R.drawable.chemistry,"Chemistry"));
        data.add(new CategoryData(R.drawable.biology,"Biology"));
        data.add(new CategoryData(R.drawable.english,"English"));
        adapter.notifyDataSetChanged();
        adapter.onClickItem= name -> {
            MyShar.setCategory(name);
            if (name.equals("Math")){
                presenter.setCategory(CategoryEnum.Math);
            }else if (name.equals("Program")){
                presenter.setCategory(CategoryEnum.Program);
            }else if (name.equals("History")){
                presenter.setCategory(CategoryEnum.History);
            }else if (name.equals("Physics")){
                presenter.setCategory(CategoryEnum.Physics);
            }else if (name.equals("Chemistry")){
                presenter.setCategory(CategoryEnum.Chemistry);
            }else if (name.equals("Biology")){
                presenter.setCategory(CategoryEnum.Biology);
            }else if (name.equals("English")){
                presenter.setCategory(CategoryEnum.English);
            }
        };
    }

    @Override
    public void openQuestionActivity() {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        new A().start();
        finishCount++;
        if(finishCount>1){
            super.onBackPressed();
        }else Toast.makeText(this, "Apptan chikib kitish uchun ikki marotaba bosing", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }

    class A extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                finishCount=0;
            } catch (InterruptedException ignored) {
            }
        }
    }
}