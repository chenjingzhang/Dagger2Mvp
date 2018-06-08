package com.dxt2.mvpdagger2.app;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.dxt2.mvpdagger2.BuildConfig;
import com.dxt2.mvpdagger2.data.model.User;
import com.dxt2.mvpdagger2.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

import static android.provider.UserDictionary.Words.APP_ID;

/**
 * Created by Administrator on 2018/6/8 0008.
 */

public class AndroidPlusApp extends DaggerApplication{
    private static final String APP_ID = "gCNRh1fJ63GUTHAfU5m1g5Wu-gzGzoHsz";
    private static final String APP_KEY = "s8A3es1QVmIkNjfkD8VUU5An";


    //实现父类抽象方法applicationInjector(),返回DaggerAppComponent对象
    //另外本项目绝大部分的Activity Fragment 都需要Dagger注入，所以让它们的基类分别继承DaggerAppComponent和DaggerFragment
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.create();
    }

    //创建完User后，还需在Application类初始化LeanCloud之前配置使用User
    @Override
    public void onCreate() {
        super.onCreate();
        initSubClasses();//初始化LeanCloud实体类
        AVOSCloud.initialize(this, APP_ID, APP_KEY);//初始化LeanCloud
        AVOSCloud.setDebugLogEnabled(BuildConfig.DEBUG);//设置Debug模式
    }

    private void initSubClasses() {
        AVObject.registerSubclass(User.class);
        AVUser.alwaysUseSubUserClass(User.class);
    }

}
