package com.example.question.presentation.main;

import com.example.question.data.model.QuestionData;
import com.example.question.presentation.adapter.IsCurrent;

import java.util.List;

public interface MainContract {
    interface View{
        void clearOldStates(int pos);
        void nextButtonState(Boolean bool);
        void describeQuestion(QuestionData data);
        void showSelectIndex(int index);
        void openResultActivity();
        void displayToast();
    }
    interface Model{
        List<QuestionData> getQuestionByCategory();
        void setResult(int result);
    }
    interface Presenter{
        void selectAnswer(int pos);
        void clickNextButton();
        void openResultActivity();
        void isCurrentListener(IsCurrent isCurrentListener);

        boolean isCurrentAnswer();
    }
}
