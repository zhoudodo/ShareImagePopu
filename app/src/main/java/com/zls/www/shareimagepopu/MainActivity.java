package com.zls.www.shareimagepopu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.zls.www.shareimagepopu.popu.ShareImagePopu;

/**
 * Created by dodo on 2018/8/21.
 */

public class MainActivity extends FragmentActivity {
    ShareImagePopu mShareImagePopu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null == mShareImagePopu){
                    mShareImagePopu = new ShareImagePopu(MainActivity.this);
                }
                mShareImagePopu.delayMillionsShowBottom(200);
            }
        });
    }
}
