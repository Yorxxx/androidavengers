package com.piticlistudio.androidavengers.comics.ui.list.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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

public class ComicsListActivity extends BaseMvpActivity<IComicsListView, ComicsListPresenter> implements IComicsListView, SwipeRefreshLayout.OnRefreshListener, ComicListAdapter.IComicListAdapterClickListener {

    @Bind(R.id.loadingView)
    LinearLayout loading;
    @Bind(R.id.listview)
    RecyclerView listview;
    @Bind(R.id.swipeview)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.emptyview)
    TextView emptyview;
    @Bind(R.id.errorview)
    TextView errorview;
    @Bind(R.id.activity_character_list)
    ConstraintLayout content;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private ComicListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        adapter = getComicComponent().listAdapter();
        listview.setLayoutManager(new GridLayoutManager(getApplicationContext(), getResources().getInteger(R.integer.comics_list_column_count)));
        listview.addItemDecoration(new SpacesItemDecoration(16));
        listview.setAdapter(adapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        adapter.setListener(this);

        setRetainInstance(true);

        // TODO restore viewstates
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
        swipeRefreshLayout.setRefreshing(false);
        loading.animate().alpha(0f).setDuration(300).start();
        if (pullToRefresh) {
            Snackbar.make(content, e.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
        } else {
            errorview.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setData(List<Comic> data) {
        emptyview.setVisibility(data.isEmpty() ? View.VISIBLE : View.GONE);
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

    @Override
    public void onItemClick(Comic item, int position, View v) {
        getApplicationComponent().intentStarter().showComicDetail(this, item, false);
    }
}
