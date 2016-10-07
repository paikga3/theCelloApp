package the.cello;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by topclass on 2016-09-24.
 */

public class DeviceReceiver extends BroadcastReceiver {

    private String smsAction = "android.provider.Telephony.SMS_RECEIVED";
    private String bootCompleteAction = "android.intent.action.BOOT_COMPLETED";
    private SmsMessage[] msgs = null;
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(smsAction)) {
            Bundle bundle = intent.getExtras();
            String message = "";
            String sender = "";
            if(bundle != null) {
                String format = bundle.getString("format");
                Object[] pdus = (Object[])bundle.get("pdus");
                msgs = new SmsMessage[pdus.length];
                Log.d("msg", msgs.length + "");
                for(int i=0; i<msgs.length; i++) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i], format);
                    } else {
                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                    }
                    message = msgs[i].getMessageBody();
                    sender = msgs[i].getOriginatingAddress();

                    if(sender.equals("16449999")) {
                        QueryString params = new QueryString();
                        params.add("msg", message);
                        HttpRunnable httpRunnable = new HttpRunnable(params, "/receiveSms");
                        new Thread(httpRunnable).start();
                    }
                }
            }
        } else if(intent.getAction().equals(bootCompleteAction)) {
            Intent i = new Intent(context, IntroActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}
