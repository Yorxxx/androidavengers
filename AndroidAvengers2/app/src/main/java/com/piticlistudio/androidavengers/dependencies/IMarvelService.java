package com.piticlistudio.androidavengers.dependencies;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Definition of Marvel API calls
 * TODO we should create a common interface for returned objects. All responses are identical except the results array.
 * Created by jorge.garcia on 13/07/2016.
 */
public interface IMarvelService {

    @GET("/v1/public/characters/{characterId}/comics?apikey=bc0e334ed74f618c4e2fcf20daa74ef4")
    MarvelComicsResponse getComicsForCharacter(@Path("characterId") String characterId);

    class MarvelResponse {
        int code;
        String response;
    }

    class MarvelComicsResponse extends MarvelResponse {
        MarvelComicDataResponse data;
    }

    class MarvelComicDataResponse {
        int offset;
        int limit;
        int total;
        int count;
        List<MarvelComicResponse> results;
    }

    class MarvelComicResponse extends MarvelResponse {

        long id;
        int digitalId;
        String title;
        int issueNumber;
        String description;
        int pageCount;
        String resourceURI;
        List<MarvelPriceResponse> prices;
        MarvelImageResponse thumbnail;
        List<MarvelImageResponse> images;
    }

    class MarvelPriceResponse {
        String type;
        double price;
    }

    class MarvelImageResponse {
        String path;
        String extension;
    }

    class MarvelCreatorsResponse {
        int available;
        List<MarvelCreatorResponse> items;

        class MarvelCreatorResponse {
            String resourceURI;
            String name;
            String role;
        }
    }
}
