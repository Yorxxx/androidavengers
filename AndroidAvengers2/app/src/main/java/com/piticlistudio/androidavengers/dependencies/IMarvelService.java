package com.piticlistudio.androidavengers.dependencies;

import com.piticlistudio.androidavengers.comics.model.entity.MarvelComicsAPIResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Definition of Marvel API calls
 * TODO we should create a common interface for returned objects. All responses are identical except the results array.
 * Created by jorge.garcia on 13/07/2016.
 */
public interface IMarvelService {

    @GET("/v1/public/characters/{characterId}/comics")
    Observable<MarvelComicsAPIResponse> getComicsForCharacter(@Path("characterId") String characterId,
                                                              @Query("apikey") String key,
                                                              @Query("ts") long timestamp,
                                                              @Query("hash") String hash);

    class MarvelResponse {
        int code;
        String response;
    }

    class MarvelComicsResponse extends MarvelResponse {
        MarvelComicDataResponse data;

        public MarvelComicDataResponse getData() {
            return data;
        }
    }

    class MarvelComicDataResponse {
        int offset;
        int limit;
        int total;
        int count;
        List<MarvelComicResponse> results;

        public List<MarvelComicResponse> getResults() {
            return results;
        }
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

        public long getId() {
            return id;
        }

        public int getDigitalId() {
            return digitalId;
        }

        public String getTitle() {
            return title;
        }

        public int getIssueNumber() {
            return issueNumber;
        }

        public String getDescription() {
            return description;
        }

        public int getPageCount() {
            return pageCount;
        }

        public String getResourceURI() {
            return resourceURI;
        }

        public List<MarvelPriceResponse> getPrices() {
            return prices;
        }

        public MarvelImageResponse getThumbnail() {
            return thumbnail;
        }

        public List<MarvelImageResponse> getImages() {
            return images;
        }
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
