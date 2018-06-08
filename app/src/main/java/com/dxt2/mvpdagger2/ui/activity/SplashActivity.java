package com.dxt2.mvpdagger2.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.avos.avoscloud.AVUser;
import com.dxt2.mvpdagger2.MainActivity;
import com.dxt2.mvpdagger2.R;
import com.dxt2.mvpdagger2.data.model.User;

public class SplashActivity extends BaseActivity {
    private static final int DELAY_TIME = 2000;
    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        super.init();
        //设置状态栏颜色为透明色
        setStatusBarTransparent();
        //获取当前用户
         User currentUser = AVUser.getCurrentUser(User.class);
         if(currentUser == null){//如果为null,说明用户没有登录
             //延时跳转到登陆界面
             navigateToLoginActivity();
         }else{
             //延时跳转到主界面
             navigateToMainActivity();
         }
        //Activity 转场动画，淡入淡出
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }
    /**
     * 延时跳转到主界面
     */
    private void navigateToMainActivity() {
        postDelay(new Runnable() {
            @Override
            public void run() {
                //跳转到主界面
                navigateTo(MainActivity.class);
            }
        }, DELAY_TIME);
    }


    /**
     * 延时跳转到登陆界面
     */
    private void navigateToLoginActivity() {
        postDelay(new Runnable() {
            @Override
            public void run() {
                //跳转到登陆界面
                navigateTo(LoginActivity.class);
            }
        }, DELAY_TIME);
    }


    /**
     * 将状态栏背景颜色设置为透明
     */
    protected void setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
