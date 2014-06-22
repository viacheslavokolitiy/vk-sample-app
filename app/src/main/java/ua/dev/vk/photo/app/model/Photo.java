package ua.dev.vk.photo.app.model;

import java.io.Serializable;

public class Photo implements Serializable{
    private String url;
    private String largePhotoUrl;

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getLargePhotoUrl() {
        return largePhotoUrl;
    }

    public void setLargePhotoUrl(String largePhotoUrl) {
        this.largePhotoUrl = largePhotoUrl;
    }
}
