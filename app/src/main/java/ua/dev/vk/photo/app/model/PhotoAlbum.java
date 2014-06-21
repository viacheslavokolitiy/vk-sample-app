package ua.dev.vk.photo.app.model;

public class PhotoAlbum {
    private String title;
    private String mThumbnailUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }

    public void setThumbnailUrl(String mThumbnailUrl) {
        this.mThumbnailUrl = mThumbnailUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhotoAlbum)) return false;

        PhotoAlbum that = (PhotoAlbum) o;

        if (mThumbnailUrl != null ? !mThumbnailUrl.equals(that.mThumbnailUrl) : that.mThumbnailUrl != null)
            return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (mThumbnailUrl != null ? mThumbnailUrl.hashCode() : 0);
        return result;
    }
}
