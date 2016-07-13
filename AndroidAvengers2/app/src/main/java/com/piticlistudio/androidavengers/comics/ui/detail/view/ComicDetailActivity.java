package com.piticlistudio.androidavengers.comics.ui.detail.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.view.MenuItem;

import com.piticlistudio.androidavengers.base.activity.BaseActivity;
import com.piticlistudio.androidavengers.comics.model.entity.Comic;

/**
 * Activity for ComicDetailFragment.
 * It is a simple container.
 * Created by jorge.garcia on 13/07/2016.
 */
public class ComicDetailActivity extends BaseActivity {

    public final static String EXTRA_COMIC = "extra-comic";

    ComicDetailFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra(EXTRA_COMIC) && savedInstanceState == null) {
            Comic data = getIntent().getParcelableExtra(EXTRA_COMIC);
            fragment = ComicDetailFragment.newInstance(data);
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @VisibleForTesting
    public ComicDetailFragment getFragment() {
        return fragment;
    }
}
