package com.example.question.presentation.main;

import com.example.question.data.model.QuestionData;
import com.example.question.domain.AppController;

import java.util.List;

public class MainModel implements MainContract.Model{
    private AppController controller=AppController.getAppController();
    @Override
    public List<QuestionData> getQuestionByCategory() {
        return controller.getQuestionListBuCategory();
    }

    @Override
    public void setResult(int result) {
        controller.setResult(result);
    }

}
