package com.piticlistudio.androidavengers.dependencies;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.piticlistudio.androidavengers.utils.IntentStarter;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    public Picasso providePicasso(Context context) {
        return Picasso.with(context);
    }

    @Provides
    public IntentStarter intentStarter() {
        return new IntentStarter();
    }
}
