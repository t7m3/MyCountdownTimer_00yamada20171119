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
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //インスタンス変数(メンバ変数)の宣言
    TextView mTimerText;
    MyCountDownTimer mTimer;
    FloatingActionButton mFab;
    ImageViewEnemy imageViewEnemy;
    ImageViewPlayer imageViewPlayer;
    TextView textView;

    //インスタンス変数(メンバ変数)の宣言
    SoundPool mSoundPool;
    int mSoundResId;

    //画面の幅、高さを取得する
    DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
    int screenWidth = dm.widthPixels;
    int screenHeight = dm.heightPixels;

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

            //敵が左右に移動する。
            imageViewEnemy.Move(10);
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
        mTimerText.setText("1:00"); //TextViewの表示の初期値の設定
        mTimer = new MyCountDownTimer(1* 60 * 1000, 100); //タイマのインスタンスの生成

        //textViewの参照値を取得する。findViewById()メソッドで。
        textView = (TextView)findViewById(R.id.textView);

        //imageViewEnemyの参照値を取得する。findViewById()メソッドで
        ImageView R_imageViewEnemy = (ImageView)findViewById(R.id.imageViewEnemy);

        //敵のインスタンスの生成
        int x = 0;
        int y = screenHeight * 5/100;
        imageViewEnemy = new ImageViewEnemy(R_imageViewEnemy, x, y);

        //imageViewPlayerの参照値を取得する。findViewById()メソッドで
        ImageView R_imageViewPlayer = (ImageView)findViewById(R.id.imageViewPlayer);

        //プレイヤーのインスタンスの生成
        x = 0;
        y = screenHeight * 60/100;
        imageViewPlayer = new ImageViewPlayer(R_imageViewPlayer, x, y);
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

    @Override
    //画面タッチのメソッドの定義
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();                //タッチした場所のＸ座標
        int y = (int) event.getY();                //タッチした場所のＹ座標

        textView.setText("X座標："+x+"　Y座標：" + y);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                textView.append("　ACTION_DOWN");
                imageViewPlayer.setX(x);
                break;
            case MotionEvent.ACTION_UP:
                textView.append("　ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                textView.append("　ACTION_MOVE");
                imageViewPlayer.setX(x);
                break;
            case MotionEvent.ACTION_CANCEL:
                textView.append("　ACTION_CANCEL");
                break;
        }

        return true;

    }


}

