package com.example.question.presentation.result;

import com.example.question.data.model.CategoryEnum;
import com.example.question.data.model.ResultEnum;

public class ResultPresenter implements ResultContract.Presenter{
    private ResultContract.View view;
    private ResultContract.Model model;

    public ResultPresenter(ResultContract.View view) {
        this.view = view;
        model=new ResultModel();
    }

    @Override
    public void setResult() {
        view.setResults(model.getResult());
    }
}
