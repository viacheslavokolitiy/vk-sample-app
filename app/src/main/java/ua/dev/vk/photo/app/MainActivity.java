package ua.dev.vk.photo.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiPhoto;
import com.vk.sdk.api.model.VKApiPhotoAlbum;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {
    private VKApi api;
    private VKRequest vkRequest;
    private List<Integer> parameters = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vkRequest = new VKRequest("photos.getAlbums", VKParameters.from(VKApiConst.NEED_COVERS,1), VKRequest.HttpMethod.GET);
        vkRequest.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                VKApiPhotoAlbum vkApiPhotoAlbum = new VKApiPhotoAlbum();
                List<VKApiPhotoAlbum> albums = vkApiPhotoAlbum.parseResponse(response.json);
                Intent intent = new Intent(MainActivity.this, AlbumActivity.class);
                intent.putParcelableArrayListExtra("parsed_data", (ArrayList<? extends android.os.Parcelable>) albums);
                startActivity(intent);
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
}
