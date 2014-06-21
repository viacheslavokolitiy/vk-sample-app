package ua.dev.vk.photo.app.ui;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vk.sdk.api.model.VKApiPhotoAlbum;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import ua.dev.vk.photo.app.AlbumActivity;
import ua.dev.vk.photo.app.R;
import ua.dev.vk.photo.app.adapter.AlbumAdapter;
import ua.dev.vk.photo.app.model.PhotoAlbum;

public class AlbumListFragment extends ListFragment {
    private ArrayList<VKApiPhotoAlbum> albums = new ArrayList<VKApiPhotoAlbum>();
    private ArrayList<PhotoAlbum> receivedAlbums = new ArrayList<PhotoAlbum>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        for(VKApiPhotoAlbum album : albums){
            PhotoAlbum photoAlbum = new PhotoAlbum();
            photoAlbum.setTitle(album.title);
            photoAlbum.setThumbnailUrl(album.thumb_src);
            receivedAlbums.add(photoAlbum);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album_list, container, false);
        EventBus.getDefault().register(this);

        setListAdapter(new AlbumAdapter(getActivity(), R.layout.layout_album_items, receivedAlbums));

        return view;
    }

    public void onEvent(AlbumActivity.ReceivedPhotoAlbumsEvent event){
        albums.addAll(event.getAlbums());
    }
}
