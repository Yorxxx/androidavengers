package com.piticlistudio.androidavengers.comics.model.repository;

import com.piticlistudio.androidavengers.comics.MarvelComicComponent;
import com.piticlistudio.androidavengers.comics.MarvelComicModule;
import com.piticlistudio.androidavengers.dependencies.ApplicationComponent;
import com.piticlistudio.androidavengers.dependencies.ApplicationModule;

import it.cosenonjaviste.daggermock.DaggerMockRule;

public class JUnitComicsMockRule extends DaggerMockRule<MarvelComicComponent> {

    JUnitComicsMockRule() {
        super(MarvelComicComponent.class, new MarvelComicModule());
        addComponentDependency(ApplicationComponent.class, new ApplicationModule(null));
    }
}
