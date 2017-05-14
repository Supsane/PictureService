package com.chashurinevgeny.pictureservice;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public final static String PARAM_PINTENT = "pendingIntent";
    public final static int RECUEST_CODE = 1;
    public final static String PARAM_RESULT = "result";
    public final static int CODE = 200;

    private Button mDownloadButton;
    private ImageView mPictureImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mDownloadButtonBehavior();
    }

    private void initView() {
        mDownloadButton = (Button) findViewById(R.id.downloadButton);
        mPictureImageView = (ImageView) findViewById(R.id.pictureImageView);
    }

    private void mDownloadButtonBehavior() {
        mDownloadButton.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("ConstantConditions")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PictureService.class);
                PendingIntent pendingIntent = createPendingResult(RECUEST_CODE, intent, 0);
                intent.putExtra(PARAM_PINTENT, pendingIntent);
                startService(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap picture = data.getParcelableExtra(PARAM_RESULT);
        mPictureImageView.setImageBitmap(picture);
    }
}
