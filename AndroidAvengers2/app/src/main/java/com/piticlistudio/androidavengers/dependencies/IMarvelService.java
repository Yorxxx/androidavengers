package com.piticlistudio.androidavengers.dependencies;

import com.piticlistudio.androidavengers.comics.model.entity.MarvelComicsAPIResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Definition of Marvel API calls
 * TODO Response models are generated with http://www.jsonschema2pojo.org/. We could generate our own entities (keeping same structure).
 * Created by jorge.garcia on 13/07/2016.
 */
public interface IMarvelService {

    @GET("/v1/public/characters/{characterId}/comics")
    Observable<MarvelComicsAPIResponse> getComicsForCharacter(@Path("characterId") String characterId,
                                                              @Query("apikey") String key,
                                                              @Query("ts") long timestamp,
                                                              @Query("hash") String hash);
}
