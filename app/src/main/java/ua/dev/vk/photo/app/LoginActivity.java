package ua.dev.vk.photo.app;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.Button;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCaptchaDialog;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.VKSdkListener;
import com.vk.sdk.VKUIHelper;
import com.vk.sdk.api.VKError;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class LoginActivity extends ActionBarActivity {

    @InjectView(R.id.btn_login)
    Button mLoginButton;
    @InjectView(R.id.btn_log_out)
    Button mLogoutButton;

    private static final String[] scopes = new String[]{
            VKScope.PHOTOS,
            VKScope.NOHTTPS
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        VKUIHelper.onCreate(this);
        VKSdk.initialize(mVkSdkListener, getString(R.string.text_app_id));
        if(VKSdk.wakeUpSession()){
            startMainActivity();
            return;
        }
    }

    private void startMainActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        VKUIHelper.onResume(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VKUIHelper.onDestroy(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        VKUIHelper.onActivityResult(this, requestCode, resultCode, data);
    }

    private final VKSdkListener mVkSdkListener = new VKSdkListener() {
        @Override
        public void onCaptchaError(VKError captchaError) {
            new VKCaptchaDialog(captchaError).show();
        }

        @Override
        public void onTokenExpired(VKAccessToken expiredToken) {
            VKSdk.authorize(scopes);
        }

        @Override
        public void onAccessDenied(VKError authorizationError) {
            new AlertDialog.Builder(VKUIHelper.getTopActivity())
                    .setMessage(authorizationError.toString())
                    .show();
        }

        @Override
        public void onAcceptUserToken(VKAccessToken token) {
            startMainActivity();
        }

        @Override
        public void onReceiveNewToken(VKAccessToken newToken) {
            startMainActivity();
        }
    };

    @OnClick(R.id.btn_login)
    public void signIn(){
        VKSdk.authorize(scopes, true, false);
    }
}
