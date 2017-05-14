package com.chashurinevgeny.pictureservice;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import java.net.URL;

public class PictureService extends Service {

    private final String URL_LOCATION_PICTURE = "https://pp.userapi.com/c627727/v627727575/1d88/lJfajUfhU-w.jpg";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            PendingIntent pendingIntent = intent.getParcelableExtra(MainActivity.PARAM_PINTENT);
            URL newUrl = new URL(URL_LOCATION_PICTURE);
            Bitmap bitmap = BitmapFactory.decodeStream(newUrl.openConnection().getInputStream());
            Intent intent1 = new Intent().putExtra(MainActivity.PARAM_RESULT, bitmap);
            pendingIntent.send(PictureService.this, MainActivity.CODE, intent1);
            stopSelf();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }
}
