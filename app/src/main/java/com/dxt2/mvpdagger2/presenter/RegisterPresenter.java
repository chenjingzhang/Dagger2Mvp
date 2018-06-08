package com.dxt2.mvpdagger2.presenter;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SignUpCallback;
import com.dxt2.mvpdagger2.contract.RegisterContract;
import com.dxt2.mvpdagger2.data.model.User;
import com.dxt2.mvpdagger2.di.ActivitySoped;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/6/8 0008.
 */
//创建RegisterPresenter实现注册界面的Presenter层，使用自定义注解@ActivityScoped,
    //这样保证了RegisterActivity中注入的是同一个RegisterPresenter
@ActivitySoped
public class RegisterPresenter implements RegisterContract.Presenter{

    private RegisterContract.View mView;

    @Inject
    public RegisterPresenter() {

    }

    @Override
    public void takeView(RegisterContract.View view) {
        mView= view;
    }

    @Override
    public void dropView() {
        mView = null;
    }
  //调用User类来完成注册，根据注册结果通知View层进行UI更新
    @Override
    public void register(String username, String password) {
        final User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                //通知View层更新UI
                if (e == null) {
                    mView.onRegisterSuccess();
                } else {
                    if (e.getCode() == AVException.USERNAME_TAKEN) {
                        mView.onUserNameTaken();
                    } else {
                        mView.onRegisterFailed();
                    }
                }
            }
        });
    }
}
