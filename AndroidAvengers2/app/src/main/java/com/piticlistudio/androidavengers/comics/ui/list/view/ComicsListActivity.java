package com.piticlistudio.androidavengers.comics.ui.list.view;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.piticlistudio.androidavengers.R;
import com.piticlistudio.androidavengers.base.activity.BaseMvpActivity;
import com.piticlistudio.androidavengers.comics.model.entity.Comic;
import com.piticlistudio.androidavengers.comics.ui.list.presenter.ComicsListPresenter;

import java.util.List;

public class ComicsListActivity extends BaseMvpActivity<IComicsListView, ComicsListPresenter> implements IComicsListView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);

        loadData(false);
    }

    @NonNull
    @Override
    public MvpPresenter createPresenter() {
        return getComicComponent().listPresenter();
    }

    @Override
    public void showLoading(boolean pullToRefresh) {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {

    }

    @Override
    public void setData(List<Comic> data) {

    }

    @Override
    public void loadData(boolean pullToRefresh) {
        ((ComicsListPresenter) getPresenter()).loadData("1009215", pullToRefresh);
    }
}
