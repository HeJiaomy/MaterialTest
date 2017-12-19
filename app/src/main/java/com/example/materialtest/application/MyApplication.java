package com.example.materialtest.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by hejiao on 2017/12/19.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context= getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
