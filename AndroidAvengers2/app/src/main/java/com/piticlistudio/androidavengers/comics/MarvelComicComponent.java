package com.piticlistudio.androidavengers.comics;

import com.piticlistudio.androidavengers.comics.ui.list.adapter.ComicListAdapter;
import com.piticlistudio.androidavengers.comics.ui.list.presenter.ComicsListPresenter;
import com.piticlistudio.androidavengers.dependencies.ApplicationComponent;
import com.piticlistudio.androidavengers.dependencies.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {MarvelComicModule.class})
public interface MarvelComicComponent {

    ComicsListPresenter listPresenter();

    ComicListAdapter listAdapter();
}
