package com.piticlistudio.androidavengers.comics;

import com.piticlistudio.androidavengers.comics.model.repository.ComicRepository;
import com.piticlistudio.androidavengers.dependencies.IMarvelService;

import dagger.Module;
import dagger.Provides;

@Module
public class MarvelComicModule {

    @Provides
    public ComicRepository provideComicRepository(IMarvelService service) {
        return new ComicRepository(service);
    }
}
