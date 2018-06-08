package com.dxt2.mvpdagger2.di;

import com.dxt2.mvpdagger2.MainActivity;
import com.dxt2.mvpdagger2.ui.activity.LoginActivity;
import com.dxt2.mvpdagger2.ui.activity.RegisterActivity;
import com.dxt2.mvpdagger2.ui.activity.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Administrator on 2018/6/8 0008.
 */
@Module
public abstract class ActivityBindModule {

    //Android编译时会创建SplashActivity对应的Component,即SplashActivity的注入器，来完成SplashActivity的注入
    //虽然SplashActivity中没有注入任何对象，但是还需要这样配置。
    //事实上，由于BaseActivity继承DaggerAppCompatActivity,所有继承BaseActivity的类都必须配置生成对应的Component
    @ActivitySoped
    @ContributesAndroidInjector
    abstract SplashActivity splashActivity();


    @ActivitySoped
    @ContributesAndroidInjector
    abstract RegisterActivity registerActivity();


    @ActivitySoped
    @ContributesAndroidInjector
    abstract LoginActivity loginActivity();

//    @ActivitySoped
//    @ContributesAndroidInjector(modules = MainModule.class)
//    abstract MainActivity mainActivity();
}