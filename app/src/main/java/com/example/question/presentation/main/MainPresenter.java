package com.example.question.presentation.main;

import android.widget.Toast;

import com.example.question.data.model.QuestionData;
import com.example.question.presentation.adapter.IsCurrent;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {
    private int finishCount;
    private MainContract.View view;
    private MainContract.Model model;
    private List<QuestionData> questionList;
    private int selectIndex=-1;
    private int currentPos=0;
    private int correctCount=0;
    private IsCurrent isCurrentListener;


    public MainPresenter(MainContract.View view) {
        this.view = view;
        model=new MainModel();
        loadQuestionData();
        loadQuestionBy();
    }
    private void loadQuestionData(){
        questionList=model.getQuestionByCategory();
    }
    private void loadQuestionBy(){
        view.describeQuestion(questionList.get(currentPos));
    }
    @Override
    public void selectAnswer(int pos) {
        if(pos==selectIndex)return;
        if(selectIndex>-1)view.clearOldStates(selectIndex);
        selectIndex=pos;
        view.nextButtonState(true);
        view.showSelectIndex(pos);
    }

    @Override
    public void clickNextButton() {
        view.clearOldStates(selectIndex);
        selectIndex=-1;
        view.nextButtonState(false);
        currentPos++;
        if(currentPos==questionList.size()){
            model.setResult(correctCount);
            view.openResultActivity();
        }
        else view.describeQuestion(questionList.get(currentPos));
    }

    @Override
    public void openResultActivity() {
        A a=new A();
        a.start();
        finishCount++;
        if(finishCount>1){
            model.setResult(correctCount);
            view.openResultActivity();
        }else view.displayToast();

    }

    @Override
    public void isCurrentListener(IsCurrent isCurrentListener) {
        this.isCurrentListener=isCurrentListener;
    }

    @Override
    public boolean isCurrentAnswer() {
        QuestionData currentQuestion=questionList.get(currentPos);
        if(currentQuestion.getCorrect()==selectIndex){
            isCurrentListener.isCurrent(true);
            correctCount++;
            return true;
        }else {
            isCurrentListener.isCurrent(false);
            return false;
        }
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
