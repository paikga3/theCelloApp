package the.cello;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class IntroActivity extends AppCompatActivity {

    Handler handler;
    Runnable mrun = new Runnable() {
        @Override
        public void run() {
            Intent startApp = new Intent(IntroActivity.this, MainActivity.class);
            startActivity(startApp);
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_intro);

        handler = new Handler();
        handler.postDelayed(mrun, 2000);
    }


}
