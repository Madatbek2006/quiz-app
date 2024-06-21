package com.example.question.presentation.result;
public interface ResultContract {
    interface View{
        void openCategoryActivity();
        void refresh();
        void setResults(int result);
    }
    interface Presenter{
        void setResult();
    }
    interface Model{
        int getResult();
    }
}
