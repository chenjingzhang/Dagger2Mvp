package com.dxt2.mvpdagger2.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dxt2.mvpdagger2.R;
import com.dxt2.mvpdagger2.di.DaggerAppComponent;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_base);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        init();
    }

    //初始化Activity公共的功能
    protected  void init(){

    }

    //这样类无需重写onCreate(),只需实现getLayoutResId()返回布局id即可。
    public abstract int getLayoutId();

    protected void postDelay(Runnable runnable, long delayMillis) {
        if (mHandler == null) {
            mHandler = new Handler();
        }
        mHandler.postDelayed(runnable, delayMillis);
    }

    public void navigateTo(Class to) {
        Intent intent = new Intent(this, to);
        startActivity(intent);
        finish();
    }

    public void navigateForResultTo(Class to, int requestCode) {
        Intent intent = new Intent(this, to);
        startActivityForResult(intent, requestCode);
    }


}
