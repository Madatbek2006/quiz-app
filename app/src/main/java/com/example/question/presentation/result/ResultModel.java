package com.example.question.presentation.result;

import com.example.question.data.model.CategoryEnum;
import com.example.question.domain.AppController;

public class ResultModel implements ResultContract.Model {
    private AppController controller=AppController.getAppController();

    @Override
    public int getResult() {
        return controller.getResult();
    }
}
