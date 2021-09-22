package tw.tcnr110a.cloud21a;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Q0101 extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout lay03;
    private MediaPlayer startmusic;
    private Intent intent = new Intent();
    // ----------------------定時更新------------------------
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.q0101);
        setupViewComponent();
        //設定隱藏標題
        getSupportActionBar().hide();
        //設定隱藏狀態
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void setupViewComponent() {
        lay03 = (LinearLayout)findViewById(R.id.q0101_lay03);
        lay03.setOnClickListener(this);

        // --開啟時片頭音樂-----
        startmusic = MediaPlayer.create(Q0101.this, R.raw.qmusic);
        startmusic.start();

        // ---開機動畫---
        LinearLayout lay03 = (LinearLayout) findViewById(R.id.q0101_lay03);

        lay03.setAnimation(AnimationUtils.loadAnimation(this, R.anim.q0101_anim_alpha_in_01));
        TextView t001 = (TextView)findViewById(R.id.q0101_t001);
        t001.setAnimation(AnimationUtils.loadAnimation(this, R.anim.q0101_anim_alpha_in_01));

        //====================設執行緒=======================
        handler.postDelayed(updateTimer, 27000);  // 設定Delay的時間
        //-------------------------
    }

    //==========================設定執行續========================
    private Runnable updateTimer = new Runnable() {
        @Override
        public void run() {

            if (startmusic.isPlaying()) { startmusic.stop(); }
            intent.putExtra("class_title", getString(R.string.q0400_home001));
            intent.setClass(Q0101.this, Q0101.class);
            startActivity(intent);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.q0101_lay03:
                if (startmusic.isPlaying()) { startmusic.stop(); }
                //-----------------
                handler.removeCallbacks(updateTimer);
                //-----------------
                intent.putExtra("class_title", getString(R.string.q0400_home001));
                intent.setClass(Q0101.this, Q0400.class);
                startActivity(intent);
                break;
         }
    };

    //===========================生命週期==========================
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();  //禁用返回鍵
    }
}