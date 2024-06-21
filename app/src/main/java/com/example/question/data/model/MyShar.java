package com.example.question.data.model;

import android.content.Context;
import android.content.SharedPreferences;

public class MyShar {

    private static SharedPreferences sharedPreferences;

   public static void init(Context context){
        sharedPreferences=context.getSharedPreferences("Demo",Context.MODE_PRIVATE);
    }


    public static void setInfo(String name,int count){
        sharedPreferences.edit().putInt(name, count).apply();
    }

    public static int getInfo(String name){
        return sharedPreferences.getInt(name,0);
    }


    public static void setCategory(String name){
        sharedPreferences.edit().putString("category",name).apply();
    }

    public static String getCategory(){
        return sharedPreferences.getString("category","");
    }
}
