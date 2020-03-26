package com.t.imglistdemo.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Hit implements Serializable {
    /**
     * "id": 195893,
     * "pageURL": "https://pixabay.com/en/blossom-bloom-flower-195893/",
     * "type": "photo",
     * "tags": "blossom, bloom, flower",
     * "previewURL": "https://cdn.pixabay.com/photo/2013/10/15/09/12/flower-195893_150.jpg"
     * "previewWidth": 150,
     * "previewHeight": 84,
     * "webformatURL": "https://pixabay.com/get/35bbf209e13e39d2_640.jpg",
     * "webformatWidth": 640,
     * "webformatHeight": 360,
     * "largeImageURL": "https://pixabay.com/get/ed6a99fd0a76647_1280.jpg",
     * "fullHDURL": "https://pixabay.com/get/ed6a9369fd0a76647_1920.jpg",
     * "imageURL": "https://pixabay.com/get/ed6a9364a9fd0a76647.jpg",
     * "imageWidth": 4000,
     * "imageHeight": 2250,
     * "imageSize": 4731420,
     * "views": 7671,
     * "downloads": 6439,
     * "favorites": 1,
     * "likes": 5,
     * "comments": 2,
     * "user_id": 48777,
     * "user": "Josch13",
     * "userImageURL": "https://cdn.pixabay.com/user/2013/11/05/02-10-23-764_250x250.jpg",
     */
    private int id;
    private String pageURL;
    private String type;
    private String tags;
    private String previewURL;
    private int previewWidth;
    private int previewHeight;
    private String webformatURL;
    private int webformatWidth;
    private int webformatHeight;
    private String largeImageURL;
    private String fullHDURL;
    private String imageURL;
    private int imageWidth;
    private int imageHeight;
    private int imageSize;
    private int views;
    private int downloads;
    private int favorites;
    private int likes;
    private int comments;
    private int user_id;
    private String user;
    private String userImageURL;

    private float scale;

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPageURL() {
        return pageURL;
    }

    public void setPageURL(String pageURL) {
        this.pageURL = pageURL;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public int getPreviewWidth() {
        return previewWidth;
    }

    public void setPreviewWidth(int previewWidth) {
        this.previewWidth = previewWidth;
    }

    public int getPreviewHeight() {
        return previewHeight;
    }

    public void setPreviewHeight(int previewHeight) {
        this.previewHeight = previewHeight;
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public void setWebformatURL(String webformatURL) {
        this.webformatURL = webformatURL;
    }

    public int getWebformatWidth() {
        return webformatWidth;
    }

    public void setWebformatWidth(int webformatWidth) {
        this.webformatWidth = webformatWidth;
    }

    public int getWebformatHeight() {
        return webformatHeight;
    }

    public void setWebformatHeight(int webformatHeight) {
        this.webformatHeight = webformatHeight;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }

    public String getFullHDURL() {
        return fullHDURL;
    }

    public void setFullHDURL(String fullHDURL) {
        this.fullHDURL = fullHDURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getImageSize() {
        return imageSize;
    }

    public void setImageSize(int imageSize) {
        this.imageSize = imageSize;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserImageURL() {
        return userImageURL;
    }

    public void setUserImageURL(String userImageURL) {
        this.userImageURL = userImageURL;
    }

    @Override
    public String toString() {
        return "Hit{" +
                "id=" + id +
                ", pageURL='" + pageURL + '\'' +
                ", type='" + type + '\'' +
                ", tags=" + tags +
                ", previewURL='" + previewURL + '\'' +
                ", previewWidth=" + previewWidth +
                ", previewHeight=" + previewHeight +
                ", webformatURL='" + webformatURL + '\'' +
                ", webformatWidth=" + webformatWidth +
                ", webformatHeight=" + webformatHeight +
                ", largeImageURL='" + largeImageURL + '\'' +
                ", fullHDURL='" + fullHDURL + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", imageWidth=" + imageWidth +
                ", imageHeight=" + imageHeight +
                ", imageSize=" + imageSize +
                ", views=" + views +
                ", downloads=" + downloads +
                ", favorites=" + favorites +
                ", likes=" + likes +
                ", comments=" + comments +
                ", user_id=" + user_id +
                ", user='" + user + '\'' +
                ", userImageURL='" + userImageURL + '\'' +
                '}';
    }
}
