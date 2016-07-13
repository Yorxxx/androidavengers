package com.piticlistudio.androidavengers.base.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.piticlistudio.androidavengers.AndroidApplication;
import com.piticlistudio.androidavengers.comics.MarvelComicComponent;
import com.piticlistudio.androidavengers.dependencies.ApplicationComponent;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    protected MarvelComicComponent getUserComponent() {
        return ((AndroidApplication) getApplication()).getComicComponent();
    }
}
