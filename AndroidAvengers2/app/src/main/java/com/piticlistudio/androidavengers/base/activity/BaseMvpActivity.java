package com.piticlistudio.androidavengers.base.activity;


import android.os.Bundle;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.piticlistudio.androidavengers.AndroidApplication;
import com.piticlistudio.androidavengers.comics.MarvelComicComponent;
import com.piticlistudio.androidavengers.dependencies.ApplicationComponent;

public abstract class BaseMvpActivity<V extends MvpView, P extends MvpPresenter<V>> extends MvpActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    protected MarvelComicComponent getComicComponent() {
        return ((AndroidApplication) getApplication()).getComicComponent();
    }
}
