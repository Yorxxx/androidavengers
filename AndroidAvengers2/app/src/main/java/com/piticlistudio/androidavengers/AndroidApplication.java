package com.piticlistudio.androidavengers;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

import com.piticlistudio.androidavengers.comics.DaggerMarvelComicComponent;
import com.piticlistudio.androidavengers.comics.MarvelComicComponent;
import com.piticlistudio.androidavengers.dependencies.ApplicationComponent;
import com.piticlistudio.androidavengers.dependencies.ApplicationModule;
import com.piticlistudio.androidavengers.dependencies.DaggerApplicationComponent;

/**
 * Android Main Application
 * Created by jorge.garcia on 13/07/2016.
 */
public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;
    private MarvelComicComponent comicComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        this.comicComponent = DaggerMarvelComicComponent.builder()
                .applicationComponent(applicationComponent)
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    public MarvelComicComponent getComicComponent() {
        return comicComponent;
    }

    @VisibleForTesting
    public void setComicComponent(MarvelComicComponent comicComponent) {
        this.comicComponent = comicComponent;
    }
}
