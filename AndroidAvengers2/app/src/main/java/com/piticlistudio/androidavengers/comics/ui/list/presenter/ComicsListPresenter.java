package com.piticlistudio.androidavengers.comics.ui.list.presenter;

import com.piticlistudio.androidavengers.base.presenter.BaseRxMvpPresenter;
import com.piticlistudio.androidavengers.comics.model.entity.Comic;
import com.piticlistudio.androidavengers.comics.model.repository.ComicRepository;
import com.piticlistudio.androidavengers.comics.ui.list.view.IComicsListView;

import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Presenter for IComicsListView.
 * Created by jorge.garcia on 13/07/2016.
 */
public class ComicsListPresenter extends BaseRxMvpPresenter<IComicsListView> {

    private final ComicRepository repository;

    @Inject
    ComicsListPresenter(ComicRepository repository) {
        this.repository = repository;
    }

    public void loadData(String characterId, boolean pullToRefresh) {
        if (isViewAttached() && getView() != null) {
            getView().showLoading(pullToRefresh);
            repository.fetchForCharacter(characterId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::showData, error -> showError(error, pullToRefresh));
        }
    }

    private void showData(List<Comic> data) {
        if (isViewAttached() && getView() != null) {
            getView().setData(data);
            getView().showContent();
        }
    }

    private void showError(Throwable error, boolean pullToRefresh) {
        if (isViewAttached() && getView() != null) {
            getView().showError(error, pullToRefresh);
        }
    }
}
