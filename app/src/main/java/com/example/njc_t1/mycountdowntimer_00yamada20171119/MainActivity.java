package com.example.njc_t1.mycountdowntimer_00yamada20171119;

import android.content.res.Resources;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //インスタンス変数(メンバ変数)の宣言
    TextView mTimerText;
    MyCountDownTimer mTimer;
    FloatingActionButton mFab;

    //インスタンス変数(メンバ変数)の宣言
    SoundPool mSoundPool;
    int mSoundResId;

    //インスタンス変数(メンバ変数)の宣言
    Enemy1 enemy1;
    TextView textView;

    //MyCountDownTimerクラスの定義　CountDownTimerクラスを継承している
    public class MyCountDownTimer extends CountDownTimer {
        //ローカル変数の宣言
        public boolean isRunning = false;

        //コンストラクタの定義
        public MyCountDownTimer(long millisInFuture,
                                long countDownInterval) {
            super(millisInFuture, countDownInterval); //親クラスのコンストラクタを呼び出す
        }

        @Override
        //イベントが発生したときに実行するメソッドの定義
        /*（ CountDownTimerクラス(を継承したクラス)のインスタンスを生成するときに
           イベントのインターバル時間を、ミリ秒で引数に指定している。 ） */
        public void onTick(long millisUntilFinished) {
            long minute = millisUntilFinished / 1000 / 60; //残り時間の分を計算する。
            long second = millisUntilFinished / 1000 % 60; //残り時間の秒を計算する。
            mTimerText.setText(String.format("%1d:%2$02d", minute, second));
                            //残り時間の分と秒を、TextViewに表示する。
            //アニメーション
            enemy1.Move(10);
        }

        @Override
        // CountDownTimerクラス(を継承したクラス)のインスタンスが終了するときに実行するメソッド
        public void onFinish() {
            mTimerText.setText("0:00"); //残り時間の０分０秒を、TextViewに表示する。
            mSoundPool.play(mSoundResId, 1.0f, 1.0f, 0, 0, 1.0f); //音を鳴らす。

        }
    }

    @Override
    //起動時に自動実行されるメソッド
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //親クラスのコンストラクタを呼び出す
        setContentView(R.layout.activity_main); //activity_mainを表示する。

        mTimerText = (TextView) findViewById(R.id.timer_text); //TextViewの参照値を取得する。findViewById()メソッドで。
        mTimerText.setText("3:00"); //TextViewの表示の初期値の設定
        mTimer = new MyCountDownTimer(1* 60 * 1000, 100); //タイマのインスタンスの生成

        //インスタンスの生成
        enemy1 = new Enemy1();

        //imageButtonの参照値を取得する。findViewById()メソッドで。
        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);

        DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
        int height = dm.heightPixels;
        imageButton.setY(height*65/100);

    }

    public void onClick(View v) {

        mFab = (FloatingActionButton) findViewById(R.id.play_stop);
                        //FloatingActionButtonの参照値を取得する。findViewById()メソッドで。
        if (mTimer.isRunning) { //タイマが動作中なら
            mTimer.isRunning = false;
            mTimer.cancel();         //タイマを止める
            mFab.setImageResource(R.drawable.ic_play_arrow_black_24dp); //ボタンをplayに変える
        } else {                 //タイマが停止中なら
            mTimer.isRunning = true;
            mTimer.start();         //タイマをスタートさせる
            mFab.setImageResource(R.drawable.ic_stop_black_24dp); //ボタンをstopに変える
        }
    }

    @Override
    //アクティビティが画面に表示された時に実行されるメソッド
    protected void onResume() {
        super.onResume(); //親クラスのコンストラクタを呼び出す
        mSoundPool = new SoundPool(2, AudioManager.STREAM_ALARM, 0); //SoundPoolクラスのインスタンスの生成
        mSoundResId = mSoundPool.load(this,R.raw.bellsound, 1); //サウンドリソース(bellsound.ogg)の読み込み

    }

    @Override
    //アクティビティが非表示になったときに実行されるメソッド
    protected void onPause() {
        super.onPause(); //親クラスのコンストラクタを呼び出す
        mSoundPool.release(); //メモリの解放
    }

    public class  Enemy1 {
        int dir = +1;

        ImageView imageview1 = (ImageView)findViewById(R.id.imageEnemy1);

        public  void  Move(int vlue){

            DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
            int width = dm.widthPixels;
            //int height = dm.heightPixels

            width = width - imageview1.getWidth();

            float x = imageview1.getX();
            x = x + dir * vlue;
            imageview1.setX(x);

            if(x < 0 || x >= width)
                dir = -dir;
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //imageButtonの参照値を取得する。findViewById()メソッドで。
        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);

        //TextViewの参照値を取得する。findViewById()メソッドで。
        TextView textView = (TextView)findViewById(R.id.textView);

        int x = (int) event.getX();                //タッチしたＸ座標
        int y = (int) event.getY();                //タッチしたＹ座標

        textView.setText("X座標は "+x+"　Y座標は " + y);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                  //imageButton.setX(x);
                break;
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_MOVE:
                imageButton.setX(x);
                break;
            case MotionEvent.ACTION_CANCEL:

                break;
        }

        return true;

    }

}

