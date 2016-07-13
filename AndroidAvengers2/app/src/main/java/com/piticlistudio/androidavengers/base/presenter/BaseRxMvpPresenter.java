package com.piticlistudio.androidavengers.base.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Base presenter with RxJava desubscription.
 * Created by jorge.garcia on 13/07/2016.
 */
public class BaseRxMvpPresenter<V extends MvpView> extends MvpBasePresenter<V> {

    protected CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    protected void addSubscription(Subscription s) {
        mCompositeSubscription.add(s);
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (!retainInstance)
            mCompositeSubscription.unsubscribe();
    }
}
