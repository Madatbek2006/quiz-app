package com.example.question.presentation.main;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.question.R;
import com.example.question.data.model.MyShar;
import com.example.question.data.model.QuestionData;
import com.example.question.presentation.adapter.IsCurrent;
import com.example.question.presentation.result.ResultActivity;
import com.example.question.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private MainContract.Presenter presenter;
    private TextView textQuestion;
    private List<ViewGroup> groupsVariant;
    private List<ImageView> images;
    private List<TextView> testVariants;
    private Button checkBtn;
    private TextView counter;
    private int count=1;
    private int finishCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_main);
        MyShar.init(this);
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            // Получаем размер статус бара
            int statusBarHeight = getResources().getDimensionPixelSize(resourceId);

            // Устанавливаем отступ сверху для корневого элемента макета
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, // ширина в пикселях
                    statusBarHeight  // высота в пикселях
            );
            findViewById(R.id.spase).setLayoutParams(params);
        }








        StatusBarUtil.makeStatusBarTransparent(this);
        loadViews();
        presenter=new MainPresenter(this);
        initButton();
    }

    private void initButton() {
        findViewById(R.id.back).setOnClickListener(v1->{
            Dialog dialog=new Dialog(this,R.style.TransparentDialog);
            dialog.setContentView(R.layout.dialog_back);
            dialog.findViewById(R.id.continue_btn).setOnClickListener(v->{
                dialog.dismiss();
            });
            dialog.findViewById(R.id.finish_btn).setOnClickListener(v->{
                dialog.dismiss();
                super.onBackPressed();
            });
            dialog.show();
            Objects.requireNonNull(dialog.getWindow()).setGravity(Gravity.CENTER);
            dialog.setCancelable(false);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        });


    }

    private void loadViews(){
        counter=findViewById(R.id.counter);
        textQuestion=findViewById(R.id.text_question);
//        nextBtn=findViewById(R.id.btn_next);
        checkBtn=findViewById(R.id.btn_check);
        images=new ArrayList<>(4);
        images.add(findViewById(R.id.image_variant_1));
        images.add(findViewById(R.id.image_variant_2));
        images.add(findViewById(R.id.image_variant_3));
        images.add(findViewById(R.id.image_variant_4));
        testVariants=new ArrayList<>(4);
        testVariants.add(findViewById(R.id.text_variant_1));
        testVariants.add(findViewById(R.id.text_variant_2));
        testVariants.add(findViewById(R.id.text_variant_3));
        testVariants.add(findViewById(R.id.text_variant_4));
        groupsVariant=new ArrayList<>(4);
        groupsVariant.add(findViewById(R.id.variant1));
        groupsVariant.add(findViewById(R.id.variant2));
        groupsVariant.add(findViewById(R.id.variant3));
        groupsVariant.add(findViewById(R.id.variant4));
        for (byte i = 0; i < groupsVariant.size(); i++) {
            byte index=i;
            groupsVariant.get(i).setOnClickListener(v->{
                presenter.selectAnswer(index);
            });
        }
//        nextBtn.setOnClickListener(v -> {
//
//        }
//        );
//        findViewById(R.id.btn_finish).setOnClickListener(v->{
//            presenter.openResultActivity();
//        });
        counter.setText("1/10");
        checkBtn.setOnClickListener(v->{

            Dialog dialog= new Dialog(this,R.style.TransparentDialog);
            IsCurrent is= bool -> {

                if (bool){
                    dialog.setContentView(R.layout.dialog_answer);
                }else {
                    dialog.setContentView(R.layout.dialog_vroin_answer);
                }
                dialog.findViewById(R.id.next).setOnClickListener(v1->{
                    count++;
                    if (count!=11){
                        counter.setText(count+"/10");
                    }
                    presenter.clickNextButton();
                    dialog.dismiss();
                });

                dialog.show();
                Objects.requireNonNull(dialog.getWindow()).setGravity(Gravity.BOTTOM);
                dialog.setCancelable(false);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            };
            presenter.isCurrentListener(is);
            presenter.isCurrentAnswer();

        });
    }

    @Override
    public void clearOldStates(int pos) {
        images.get(pos).setBackgroundResource(R.drawable.ic_unchecked);
    }

    @Override
    public void nextButtonState(Boolean bool) {
//        nextBtn.setEnabled(bool);
//        if (bool){
//            nextBtn.setBackgroundResource(R.drawable.bg_button);
//        }else {
//            nextBtn.setBackgroundResource(R.drawable.bg_button2);
//        }
        checkBtn.setEnabled(bool);
        if (bool){
            checkBtn.setBackgroundResource(R.drawable.bg_button);
        }else {
            checkBtn.setBackgroundResource(R.drawable.bg_button2);
        }
    }

    @Override
    public void describeQuestion(QuestionData data) {
        textQuestion.setText(data.getQuestionTest());
        for (int i = 0; i < testVariants.size(); i++) {
            testVariants.get(i).setText(data.getVariants()[i]);
        }
    }

    @Override
    public void showSelectIndex(int index) {
        images.get(index).setBackgroundResource(R.drawable.ic_checked);
    }
    public void displayToast(){


        Toast.makeText(this,"Testni yakunlash uchun ikki marotaba bosing!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openResultActivity() {
        Intent intent=new Intent(this, ResultActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Dialog dialog=new Dialog(this,R.style.TransparentDialog);
        dialog.setContentView(R.layout.dialog_back);
        dialog.findViewById(R.id.continue_btn).setOnClickListener(v->{
            dialog.dismiss();
        });
        dialog.findViewById(R.id.finish_btn).setOnClickListener(v->{
            dialog.dismiss();
            super.onBackPressed();
        });
        dialog.show();
        Objects.requireNonNull(dialog.getWindow()).setGravity(Gravity.CENTER);
        dialog.setCancelable(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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