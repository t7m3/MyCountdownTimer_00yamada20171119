package com.example.njc_t1.mycountdowntimer_00yamada20171119;

import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //インスタンス変数(メンバ変数)の宣言
    TextView mTimerText;
    MyCountDownTimer mTimer;
    FloatingActionButton mFab;

    //インスタンス変数(メンバ変数)の宣言
    SoundPool mSoundPool;
    int mSoundResId;

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
        }

        @Override
        // CountDownTimerクラス(を継承したクラス)のインスタンスが終了するときに実行するメソッド
        public void onFinish() {
            mTimerText.setText("0:00"); //残り時間の０分０秒を、TextViewに表示する。
            mSoundPool.play(mSoundResId, 1.0f, 1.0f, 0, 0, 1.0f); //音を鳴らす。

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
