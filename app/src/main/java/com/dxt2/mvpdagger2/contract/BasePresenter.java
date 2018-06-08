package com.dxt2.mvpdagger2.contract;

/**
 * Created by Administrator on 2018/6/8 0008.
 */

public interface BasePresenter<T> {
    void takeView(T view);//用来给Presenter层传一个view层对象，因为presenter层需要通知view层进行更新
    void dropView(); //view层销毁后也需要通过dropView来告知presenter层，
}
