package com.piticlistudio.androidavengers.utils;

import android.app.Activity;
import android.content.Intent;

import com.piticlistudio.androidavengers.comics.model.entity.Comic;
import com.piticlistudio.androidavengers.comics.ui.detail.view.ComicDetailActivity;

/**
 * Utility class for launching intents or displaying views.
 * Created by jorge.garcia on 13/07/2016.
 */
public class IntentStarter {

    public void showComicDetail(Activity launchActivity, Comic comic, boolean dualPane) {
        Intent intent = new Intent(launchActivity, ComicDetailActivity.class);
        intent.putExtra(ComicDetailActivity.EXTRA_COMIC, comic);
        launchActivity.startActivity(intent);
    }
}
