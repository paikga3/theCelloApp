package cello.sms;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.util.Log;

/**
 * Created by topclass on 2016-09-26.
 */

public class SmsService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("order", 0 + "");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("order", 1 + "");
        String number = intent.getStringExtra("number");
        String message = intent.getStringExtra("message");

        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(number, null, message, null, null);
        stopSelf();
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
}
