package com.dxt2.mvpdagger2.contract;

/**
 * Created by Administrator on 2018/6/8 0008.
 */
//在RegisterContract中定义View层和Presenter层接口
    //注册界面View层负责处理注册结果的更新
    //presenter层主要是注册的业务逻辑
public interface RegisterContract {

     interface View extends BaseView {
        void onRegisterSuccess();
        void onRegisterFailed();
        void onUserNameTaken();
    }

    interface Presenter extends BasePresenter<View> {
        void register(String email, String password);
    }


}
