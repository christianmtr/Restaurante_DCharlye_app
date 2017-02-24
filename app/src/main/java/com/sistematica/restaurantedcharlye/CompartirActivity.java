package com.sistematica.restaurantedcharlye;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.LikeView;
import com.facebook.share.widget.ShareDialog;

public class CompartirActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compartir);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());

        /*    Like    */
        LikeView likeView = (LikeView) findViewById(R.id.like_view);
        likeView.setLikeViewStyle(LikeView.Style.BOX_COUNT);
        likeView.setObjectIdAndType(
                "https://www.facebook.com/dcharlyesac/",
                LikeView.ObjectType.PAGE);
        likeView.setAuxiliaryViewPosition(LikeView.AuxiliaryViewPosition.BOTTOM);
        likeView.setHorizontalAlignment(LikeView.HorizontalAlignment.CENTER);

        /*    Compartir    */
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://www.facebook.com/dcharlyesac/"))
                .setContentTitle("Buenísimo!")
                .setContentDescription(
                        "Lo mejor en pollos a la brasa, parrilas y chifa."
                )
                .setPlaceId("https://www.facebook.com/dcharlyesac/")
                .build();

        shareDialog = new ShareDialog(this);
        shareDialog.show(content);

    }

    @Override
    protected void onPause() {
        super.onPause();
        startActivity(getSupportParentActivityIntent());
    }

}
