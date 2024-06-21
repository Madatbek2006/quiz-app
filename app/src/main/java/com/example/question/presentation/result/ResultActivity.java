package com.example.question.presentation.result;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.question.R;
import com.example.question.data.model.MyShar;
import com.example.question.presentation.category.CategoryActivity;
import com.example.question.presentation.main.MainActivity;
import com.example.question.utils.StatusBarUtil;

public class ResultActivity extends AppCompatActivity implements ResultContract.View{
    ResultContract.Presenter presenter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.BLACK);
        setContentView(R.layout.screen_resuld);
        MyShar.init(this);
        findViewById(R.id.back).setOnClickListener(v->{
            openCategoryActivity();
        });
        findViewById(R.id.home).setOnClickListener(v->{
            openCategoryActivity();
        });
        findViewById(R.id.try_again).setOnClickListener(v->{
            refresh();
        });
        presenter=new ResultPresenter(this);
        presenter.setResult();
    }

    @Override
    public void openCategoryActivity() {
        finish();
    }

    @Override
    public void refresh() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void setResults(int result) {
        MyShar.setInfo(MyShar.getCategory(),result);
        ((TextView)findViewById(R.id.result)).setText(result+"/10");
    }
}