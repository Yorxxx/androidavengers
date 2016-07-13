
package com.piticlistudio.androidavengers.comics.model.entity;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Result {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("digitalId")
    @Expose
    public Integer digitalId;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("issueNumber")
    @Expose
    public Integer issueNumber;
    @SerializedName("variantDescription")
    @Expose
    public String variantDescription;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("modified")
    @Expose
    public String modified;
    @SerializedName("isbn")
    @Expose
    public String isbn;
    @SerializedName("upc")
    @Expose
    public String upc;
    @SerializedName("diamondCode")
    @Expose
    public String diamondCode;
    @SerializedName("ean")
    @Expose
    public String ean;
    @SerializedName("issn")
    @Expose
    public String issn;
    @SerializedName("format")
    @Expose
    public String format;
    @SerializedName("pageCount")
    @Expose
    public Integer pageCount;
    @SerializedName("textObjects")
    @Expose
    public List<TextObject> textObjects = new ArrayList<TextObject>();
    @SerializedName("resourceURI")
    @Expose
    public String resourceURI;
    @SerializedName("urls")
    @Expose
    public List<Url> urls = new ArrayList<Url>();
    @SerializedName("series")
    @Expose
    public Series series;
    @SerializedName("variants")
    @Expose
    public List<Object> variants = new ArrayList<Object>();
    @SerializedName("collections")
    @Expose
    public List<Object> collections = new ArrayList<Object>();
    @SerializedName("collectedIssues")
    @Expose
    public List<Object> collectedIssues = new ArrayList<Object>();
    @SerializedName("dates")
    @Expose
    public List<Date> dates = new ArrayList<Date>();
    @SerializedName("prices")
    @Expose
    public List<Price> prices = new ArrayList<Price>();
    @SerializedName("thumbnail")
    @Expose
    public Thumbnail thumbnail;
    @SerializedName("images")
    @Expose
    public List<Image> images = new ArrayList<Image>();
    @SerializedName("creators")
    @Expose
    public Creators creators;
    @SerializedName("characters")
    @Expose
    public Characters characters;
    @SerializedName("stories")
    @Expose
    public Stories stories;
    @SerializedName("events")
    @Expose
    public Events events;

}
