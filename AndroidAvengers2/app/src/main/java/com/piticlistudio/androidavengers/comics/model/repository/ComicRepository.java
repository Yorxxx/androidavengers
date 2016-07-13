package com.piticlistudio.androidavengers.comics.model.repository;

import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.piticlistudio.androidavengers.comics.model.entity.Comic;
import com.piticlistudio.androidavengers.comics.model.entity.Image;
import com.piticlistudio.androidavengers.comics.model.entity.MarvelComicsAPIResponse;
import com.piticlistudio.androidavengers.comics.model.entity.Result;
import com.piticlistudio.androidavengers.dependencies.IMarvelService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * Repository for Comics.
 * Created by jorge.garcia on 13/07/2016.
 */
public class ComicRepository {

    public final static String PUBLIC_KEY = "6a7ed890b4b941a925202a5630d5b162";
    public final static String PRIVATE_KEY = "0f1d0fdf46a0bf32f962b0b9997233c0395cdf8e";
    private final IMarvelService service;

    @Inject
    public ComicRepository(IMarvelService service) {
        this.service = service;
    }

    public Observable<List<Comic>> fetchForCharacter(String characterId) {

        long ts = System.currentTimeMillis();
        return service.getComicsForCharacter(characterId, PUBLIC_KEY, ts, md5(String.valueOf(ts)+PRIVATE_KEY+PUBLIC_KEY))
                .flatMap(new Func1<MarvelComicsAPIResponse, Observable<List<Comic>>>() {
                    @Override
                    public Observable<List<Comic>> call(MarvelComicsAPIResponse resp) {
                        return Observable.from(resp.data.results)
                                .map(new Func1<Result, Comic>() {
                                    @Override
                                    public Comic call(Result result) {
                                        return fromResponseAPIToModel(result);
                                    }
                                })
                                .toList();
                    }
                });
    }

    /**
     * Calculates the md5 hash of a given string.
     * @param s the string to calculate its md5
     * @return the hash value.
     */
    String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Generates a comic entity from the response by the Marvel API.
     * We could still work with the data returned by Marvel, but I think it's better to isolate layers, so any changes
     * by Marvel would not affect our models.
     * @param apiModel the api model response.
     * @return a Comic entity with the data or null if apiModel is invalid.
     */
    Comic fromResponseAPIToModel(@Nullable Result apiModel) {
        if (apiModel == null || apiModel.id == null)
            return null;

        Comic comic = new Comic();
        comic.setId(apiModel.id);
        comic.setDescription(apiModel.description);
        List<String> images = new ArrayList<>();
        for (Image image : apiModel.images) {
            images.add(image.path+"."+image.extension);
        }
        comic.setImagesUri(images);

        if (apiModel.issueNumber != null)
            comic.setIssueNumber(apiModel.issueNumber);
        if (apiModel.pageCount != null)
            comic.setPageCount(apiModel.pageCount);
        comic.setTitle(apiModel.title);
        comic.setThumbnailUri(apiModel.thumbnail.path+"."+apiModel.thumbnail.extension);
        return comic;
    }

}
