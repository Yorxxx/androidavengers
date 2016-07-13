
package com.piticlistudio.androidavengers.comics.model.entity;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Image {

    @SerializedName("path")
    @Expose
    public String path;
    @SerializedName("extension")
    @Expose
    public String extension;

    public Image() {
    }

    public Image(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }
}
