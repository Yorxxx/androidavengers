package com.piticlistudio.androidavengers.comics;

import com.piticlistudio.androidavengers.comics.model.entity.Comic;

/**
 * Factory for Comics entities
 * Created by jorge.garcia on 13/07/2016.
 */
public class ComicsFactory {

    public static Comic generate(String title) {
        Comic comic = new Comic();
        comic.setTitle(title);
        comic.setThumbnailUri("http://i.annihil.us/u/prod/marvel/i/mg/9/b0/5783bca7b2f93.jpg");
        comic.setId(1);
        return comic;
    }
}
