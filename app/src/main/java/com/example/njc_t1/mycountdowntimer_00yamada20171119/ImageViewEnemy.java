package com.example.njc_t1.mycountdowntimer_00yamada20171119;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.widget.ImageView;

/**
 * Created by NJC-T1 on 2017/12/04.
 */

public class ImageViewEnemy {

    //このクラスのグローバル変数の宣言
    int dir = +1;
    ImageView imageViewEnemy;

    //画面の幅、高さを取得する
    DisplayMetrics dm = Resources.getSystem().getDisplayMetrics();
    int screenWidth = dm.widthPixels;
    int screenHeight = dm.heightPixels;

    //このクラスのコンストラクタ
    public ImageViewEnemy(ImageView R_imageViewEnemy, int x, int y){
        imageViewEnemy = R_imageViewEnemy;
        imageViewEnemy.setX(x);
        imageViewEnemy.setY(y);
    }

    //左または右に移動する。valueは移動量。
    public  void  Move(int value){

        float x = imageViewEnemy.getX(); //ｘ座標の取得
        x = x + value*dir;
        imageViewEnemy.setX(x); //x座標の更新

        if(x < 0 || x > screenWidth-imageViewEnemy.getWidth()){
            dir = -dir; //移動の左右の向きを反転する
        }
    }
}
