package ua.dev.vk.photo.app.ui.photos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.vk.sdk.api.model.VKApiPhoto;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.greenrobot.event.EventBus;
import ua.dev.vk.photo.app.AlbumPhotoActivity;
import ua.dev.vk.photo.app.FullscreenPhotoActivity;
import ua.dev.vk.photo.app.R;
import ua.dev.vk.photo.app.adapter.PhotoAdapter;
import ua.dev.vk.photo.app.model.Photo;

public class PhotoListFragment extends ListFragment {
    private List<Photo> photoUrls = new ArrayList<Photo>();
    private ArrayList<VKApiPhoto> receivedPhotos = new ArrayList<VKApiPhoto>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public PhotoListFragment(){

    }

    public PhotoListFragment newInstance(ArrayList<VKApiPhoto> photos){
        PhotoListFragment photoListFragment = new PhotoListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("photos", photos);
        photoListFragment.setArguments(args);

        return photoListFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album_photos, container, false);
        Bundle extras = getArguments();
        List<VKApiPhoto> apiPhotos = extras.getParcelableArrayList("bundles");
        receivedPhotos.addAll(apiPhotos);
        for (VKApiPhoto receivedPhoto : receivedPhotos) {
            Photo photo = new Photo();
            photo.setUrl(receivedPhoto.photo_604);
            photoUrls.add(photo);
        }
        setListAdapter(new PhotoAdapter(getActivity(), R.layout.layout_photo_items, (ArrayList<Photo>) photoUrls));
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Photo photo = photoUrls.get(position);
        Intent intent = new Intent(getActivity(), FullscreenPhotoActivity.class);
        intent.putExtra("selected_photo", photo);
        startActivity(intent);
    }
}
