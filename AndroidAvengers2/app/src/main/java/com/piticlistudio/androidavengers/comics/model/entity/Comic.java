package com.piticlistudio.androidavengers.comics.model.entity;

import java.util.List;

/**
 * Entity that defines a Comic.
 * Created by jorge.garcia on 13/07/2016.
 */
public class Comic {

    long id;
    String title;
    int issueNumber;
    String description;
    long updatedAt;
    int pageCount;
    String resourceUri;
    String thumbnailUri;
    List<String> imagesUri;

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

    public List<String> getImagesUri() {
        return imagesUri;
    }

    public void setImagesUri(List<String> imagesUri) {
        this.imagesUri = imagesUri;
    }
}
