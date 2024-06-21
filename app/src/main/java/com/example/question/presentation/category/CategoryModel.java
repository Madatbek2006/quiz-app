package com.example.question.presentation.category;

import com.example.question.data.model.CategoryEnum;
import com.example.question.domain.AppController;

public class CategoryModel implements CategoryContract.Model {
    private AppController controller=AppController.getAppController();

    @Override
    public void setCategory(CategoryEnum category) {
        controller.setCategoryEnum(category);
    }
}
