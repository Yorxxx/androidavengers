package com.piticlistudio.androidavengers;

import android.app.Application;

import com.piticlistudio.androidavengers.dependencies.ApplicationComponent;
import com.piticlistudio.androidavengers.dependencies.ApplicationModule;
import com.piticlistudio.androidavengers.dependencies.DaggerApplicationComponent;

/**
 * Android Main Application
 * Created by jorge.garcia on 13/07/2016.
 */
public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
