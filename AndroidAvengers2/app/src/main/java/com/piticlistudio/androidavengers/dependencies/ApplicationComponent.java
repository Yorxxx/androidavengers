package com.piticlistudio.androidavengers.dependencies;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.piticlistudio.androidavengers.utils.IntentStarter;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(AppCompatActivity baseActivity);

    //Exposed to sub-graphs.
    Context context();

    Picasso picasso();

    IntentStarter intentStarter();

    IMarvelService marvelService();
}
