package com.dxt2.mvpdagger2.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Administrator on 2018/6/8 0008.
 */
//创建application对应的AppComponent
   /* AppComponent有几个模块
    ApplicationModule  提供application上下文的注入
    ActivityBindingModule 用于生成各个Activity对应的Component,生成的这些Component将都是AppComponent的subComponent
    ActivitySupportInjectonModule  提供Activity Fragment 注入器工厂集合的注入，由dagger.android提供
    DataRepository 提供数据仓库的注入
*/
@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,ActivityBindModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication>{
     @Override
     void inject(DaggerApplication instance);
}
























