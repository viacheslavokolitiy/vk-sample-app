package ua.dev.vk.photo.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ua.dev.vk.photo.app.model.Photo;

public class FullscreenPhotoActivity extends FragmentActivity {
    @InjectView(R.id.full_screen_image)
    ImageView fullScreen;
    @InjectView(R.id.progress_full_screen)
    ProgressBar fullScreenProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_fullscreen);
        ButterKnife.inject(this);
        Photo photo = (Photo) getIntent().getSerializableExtra("selected_photo");
        Picasso.with(this).load(photo.getUrl())
                .fit()
                .centerCrop()
                .into(fullScreen, new Callback() {
                    @Override
                    public void onSuccess() {
                        fullScreen.setVisibility(View.VISIBLE);
                        fullScreenProgress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        fullScreenProgress.setVisibility(View.VISIBLE);
                        fullScreen.setVisibility(View.GONE);
                    }
                });

    }
}
