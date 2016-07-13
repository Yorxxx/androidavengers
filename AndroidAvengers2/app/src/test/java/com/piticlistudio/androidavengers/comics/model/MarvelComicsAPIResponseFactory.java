package com.piticlistudio.androidavengers.comics.model;

import com.piticlistudio.androidavengers.comics.model.entity.Data;
import com.piticlistudio.androidavengers.comics.model.entity.Image;
import com.piticlistudio.androidavengers.comics.model.entity.MarvelComicsAPIResponse;
import com.piticlistudio.androidavengers.comics.model.entity.Result;
import com.piticlistudio.androidavengers.comics.model.entity.Thumbnail;

import java.util.ArrayList;

/**
 * Factory for MarvelComicsAPIResponses
 * Created by jorge.garcia on 13/07/2016.
 */
public class MarvelComicsAPIResponseFactory {

    public static MarvelComicsAPIResponse generateResponse(int items) {
        MarvelComicsAPIResponse response = new MarvelComicsAPIResponse();
        response.code = 200;
        response.status = "OK";
        response.data = new Data();
        response.data.count = items;
        response.data.total = items;
        response.data.results = new ArrayList<>();
        for (int i = 0; i < items; i++) {
            response.data.results.add(generate(i));
        }
        return response;
    }

    public static Result generate(int id) {
        Result apiData = new Result();
        apiData.id = id;
        apiData.description = "description";
        apiData.pageCount = 14;
        apiData.images = new ArrayList<>();
        apiData.images.add(new Image("image1", "jpg"));
        apiData.images.add(new Image("image2", "jpg"));
        apiData.title = "title";
        apiData.thumbnail = new Thumbnail();
        apiData.thumbnail.path = "thumbnailpath";
        apiData.thumbnail.extension = "png";
        return apiData;
    }
}
