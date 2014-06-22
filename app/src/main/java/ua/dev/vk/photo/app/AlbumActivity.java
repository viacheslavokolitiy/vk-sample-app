package ua.dev.vk.photo.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.vk.sdk.api.model.VKApiPhotoAlbum;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

public class AlbumActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);
        ArrayList<VKApiPhotoAlbum> albums = getIntent().getParcelableArrayListExtra("parsed_data");
        EventBus.getDefault().post(new ReceivedPhotoAlbumsEvent(albums));
    }

    public class ReceivedPhotoAlbumsEvent {
        private ArrayList<VKApiPhotoAlbum> albums;

        public ReceivedPhotoAlbumsEvent(ArrayList<VKApiPhotoAlbum> albums) {
            this.albums = albums;
        }

        public ArrayList<VKApiPhotoAlbum> getAlbums() {
            return albums;
        }
    }
}
