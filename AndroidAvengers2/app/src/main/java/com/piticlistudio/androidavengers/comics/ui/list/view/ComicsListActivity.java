package com.piticlistudio.androidavengers.comics.ui.list.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.piticlistudio.androidavengers.R;
import com.piticlistudio.androidavengers.base.activity.BaseMvpActivity;
import com.piticlistudio.androidavengers.comics.model.entity.Comic;
import com.piticlistudio.androidavengers.comics.ui.list.adapter.ComicListAdapter;
import com.piticlistudio.androidavengers.comics.ui.list.presenter.ComicsListPresenter;
import com.piticlistudio.androidavengers.utils.SpacesItemDecoration;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ComicsListActivity extends BaseMvpActivity<IComicsListView, ComicsListPresenter> implements IComicsListView, SwipeRefreshLayout.OnRefreshListener {

    ComicListAdapter adapter;
    @Bind(R.id.loadingView)
    LinearLayout loading;
    @Bind(R.id.listview)
    RecyclerView listview;
    @Bind(R.id.swipeview)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);
        ButterKnife.bind(this);

        adapter = getComicComponent().listAdapter();
        listview.setLayoutManager(new GridLayoutManager(getApplicationContext(), getResources().getInteger(R.integer.comics_list_column_count)));
        listview.addItemDecoration(new SpacesItemDecoration(16));
        listview.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);


        loadData(false);
    }

    @NonNull
    @Override
    public MvpPresenter createPresenter() {
        return getComicComponent().listPresenter();
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        if (pullToRefresh)
            swipeRefreshLayout.setRefreshing(true);
        else
            loading.animate().alpha(1f).setDuration(300).start();
    }

    @Override
    public void showContent() {
        swipeRefreshLayout.setRefreshing(false);
        loading.animate().alpha(0f).setDuration(300).start();
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {

    }

    @Override
    public void setData(List<Comic> data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        ((ComicsListPresenter) getPresenter()).loadData("1009215", pullToRefresh);
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }
}
