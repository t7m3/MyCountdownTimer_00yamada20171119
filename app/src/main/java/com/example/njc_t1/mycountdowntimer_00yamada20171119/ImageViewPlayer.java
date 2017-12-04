package com.example.njc_t1.mycountdowntimer_00yamada20171119;

import android.widget.ImageView;

/**
 * Created by NJC-T1 on 2017/12/04.
 */

public class ImageViewPlayer {

    //このクラスのグローバル変数の宣言
    ImageView imageViewPlayer;

    //このクラスのコンストラクタ
    public ImageViewPlayer(ImageView R_imageViewPlayer, int x, int y){
        imageViewPlayer = R_imageViewPlayer;
        imageViewPlayer.setX(x);
        imageViewPlayer.setY(y);
    }

    //ｘ座標を設定する
    public void setX(int x){
        imageViewPlayer.setX(x);
    }
}
