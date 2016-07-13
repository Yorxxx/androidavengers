package com.piticlistudio.androidavengers.comics.model.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity that defines a Comic.
 * Created by jorge.garcia on 13/07/2016.
 */
@ParcelablePlease
public class Comic implements Parcelable {

    public static final Creator<Comic> CREATOR = new Creator<Comic>() {
        public Comic createFromParcel(Parcel source) {
            Comic target = new Comic();
            ComicParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public Comic[] newArray(int size) {
            return new Comic[size];
        }
    };
    long id;
    String title;
    int issueNumber;
    String description;
    long updatedAt;
    int pageCount;
    String resourceUri;
    String thumbnailUri;
    ArrayList<String> imagesUri;
    String purchaseUrl;
    String detailUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    public String getThumbnailUri() {
        return thumbnailUri;
    }

    public void setThumbnailUri(String thumbnailUri) {
        this.thumbnailUri = thumbnailUri;
    }

    public String getPurchaseUrl() {
        return purchaseUrl;
    }

    public void setPurchaseUrl(String purchaseUrl) {
        this.purchaseUrl = purchaseUrl;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    @NonNull
    public List<String> getImagesUri() {
        if (imagesUri == null)
            imagesUri = new ArrayList<>();
        return imagesUri;
    }

    public void setImagesUri(List<String> imagesUri) {
        this.imagesUri = new ArrayList<>();
        this.imagesUri.addAll(imagesUri);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ComicParcelablePlease.writeToParcel(this, dest, flags);
    }
}
