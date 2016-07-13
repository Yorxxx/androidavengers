package com.piticlistudio.androidavengers.dependencies;

import android.app.Application;
import android.content.Context;

import com.piticlistudio.androidavengers.utils.IntentStarter;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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

    @Provides
    public IMarvelService marvelService() {

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://gateway.marvel.com:80")
                .build();


        return retrofit.create(IMarvelService.class);
    }
}
