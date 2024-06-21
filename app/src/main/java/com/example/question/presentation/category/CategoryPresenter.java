package com.example.question.presentation.category;

import com.example.question.data.model.CategoryEnum;

public class CategoryPresenter implements CategoryContract.Presenter {
    private CategoryContract.View view;
    private CategoryContract.Model model;

    public CategoryPresenter(CategoryContract.View view) {
        this.view = view;
        model=new CategoryModel();
    }

    @Override
    public void setCategory(CategoryEnum category) {
        model.setCategory(category);
        view.openQuestionActivity();
    }

}
