package ua.dev.vk.photo.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiPhoto;

import java.util.ArrayList;
import java.util.List;

import ua.dev.vk.photo.app.ui.photos.PhotoListFragment;


public class AlbumPhotoActivity extends ActionBarActivity {
    private VKRequest mVkRequest;
    private List<VKApiPhoto> photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_photos);
        int id = getIntent().getIntExtra("album_id", 0);
        mVkRequest = new VKRequest("photos.get", VKParameters.from(VKApiConst.ALBUM_ID, id), VKRequest.HttpMethod.GET);
        mVkRequest.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                VKApiPhoto vkApiPhoto = new VKApiPhoto();
                photos = vkApiPhoto.parseResponse(response.json);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("bundles", (ArrayList<? extends Parcelable>) photos);
                PhotoListFragment photoListFragment = new PhotoListFragment();
                photoListFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().add(R.id.container, photoListFragment).commit();
            }

            @Override
            public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
                Log.e("fail", "attempt_failed");
            }

            @Override
            public void onError(VKError error) {
                Log.e("error", error.errorReason);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AlbumPhotoActivity.this, MainActivity.class));
    }

    public List<VKApiPhoto> getPhotos() {
        return photos;
    }

    public static class ReceivedPhotoListEvent {
        private Bundle report;

        public ReceivedPhotoListEvent(Bundle bundle) {
            report = bundle;
        }

        public Bundle getReport() {
            return report;
        }
    }
}
