package com.example.question.presentation.category;

import com.example.question.data.model.CategoryEnum;

public interface CategoryContract {
    interface View{
        void openQuestionActivity();
    }
    interface Model{
        void setCategory(CategoryEnum category);
    }
    interface Presenter{
        void setCategory(CategoryEnum category);
    }
}
