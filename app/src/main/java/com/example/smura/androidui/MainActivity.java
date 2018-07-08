package com.example.smura.androidui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends Activity {

    private int realScreenWidth = 0;
    private int realScreenHeight = 0;
    private TextView signal_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signal_num = findViewById(R.id.signal_num);

        // SeekBar
        SeekBar signal = findViewById(R.id.signal);
        // 初期値
        signal.setProgress(0);
        // 最大値
        signal.setMax(100);

        //背景画像の設定
        WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
        Display disp = wm.getDefaultDisplay();
        Point realSize = new Point();
        disp.getRealSize(realSize);
        Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.downlight),realSize.x,realSize.y,true);
        ImageView iv_background = (ImageView) findViewById(R.id.Downlight);
        iv_background.setImageBitmap(bmp);

        signal.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    //ツマミがドラッグされると呼ばれる
                    @Override
                    public void onProgressChanged(
                            SeekBar seekBar, int progress, boolean fromUser) {
                        // 68 % のようにフォーマト、
                        // この場合、Locale.USが汎用的に推奨される
                        String str = String.format(Locale.US, "%d %%",progress);
                        signal_num.setText(str);
                    }

                    //ツマミがタッチされた時に呼ばれる
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    //ツマミがリリースされた時に呼ばれる
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }

                });
    }

    //Windowにフォーカスをあてる
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 22) {
            hideSystemUI();
        }
    }

    //ナビゲートバー・タイトルバーなどを非表示にする
    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
        );
    }

    private void OnClick(){

    }
}
