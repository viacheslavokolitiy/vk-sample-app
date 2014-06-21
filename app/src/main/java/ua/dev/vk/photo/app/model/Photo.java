package ua.dev.vk.photo.app.model;

import java.io.Serializable;

/**
 * Created by 1 on 21.06.2014.
 */
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
