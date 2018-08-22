package com.zls.www.shareimagepopu.popu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.NestedScrollView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zls.www.shareimagepopu.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by dodo on 2018/8/20.
 */

public class ShareImagePopu extends BasePopu {

    public ShareImagePopu(FragmentActivity act) {
        super(act, R.layout.layout_share_pop, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    private NestedScrollView mNsv;
    private ImageView mImageView;
    private LinearLayout mAllLinear, mSvLinear;
    private RelativeLayout mSvRelative;
    private LinearLayout weixin, pengyouquan, qq, sina;
    private TextView mBtnCancel;

    @Override
    protected void init() {
        mNsv = findViewsId(R.id.nest_scrollview, false);
        mImageView = findViewsId(R.id.share_img, false);
        mSvLinear = findViewsId(R.id.sv_linear, false);
        mSvRelative = findViewsId(R.id.sv_relative, false);


        weixin = findViewsId(R.id.btn_weixin, true);
        pengyouquan = findViewsId(R.id.btn_pengyouquan, true);
        qq = findViewsId(R.id.btn_qq, true);
        sina = findViewsId(R.id.btn_sina, true);
        mBtnCancel = findViewsId(R.id.btn_cancel, true);


    }





    Handler mHandler = new Handler(Looper.getMainLooper());

    public void delayMillionsShowBottom(long millions) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showBottom();
            }
        }, millions);
    }


    @Override
    public void showBottom() {
        super.showBottom();

        DisplayMetrics metric = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）



        View rootView = ShareImageHelper.setContentView(mActivity,R.layout.layout_share_img);
        View rootView2 = ShareImageHelper.setContentView(mActivity,R.layout.layout_share_img);
        ShareImageHelper.layoutView(rootView,width,height);//为什么呢 因为需要给布局添加父布局并且重新计算
        ShareImageHelper.layoutView(rootView2,width,height);
        Bitmap bitmap = ShareImageHelper.loadBitmapFromView(rootView);//这里主要是为了显示
        Bitmap shareBit = ShareImageHelper.loadBitmapFromView(rootView2);//这边是为了分享
        mImageView.setImageBitmap(bitmap);//显示bitmap
        saveBitmap(shareBit);//分享bitmap

    }

    public String mBitmapLoaclPath;

    public final static String PICTURE_CACHE = "picture_cache_path";


    private void saveBitmap(final Bitmap bitmap) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String picCachePath = getDiskCacheDirect(mActivity, PICTURE_CACHE);
               final File filePic;
                filePic = new File(picCachePath, System.currentTimeMillis() + ".jpg");
                try {
                    if (!filePic.exists()) {
                        filePic.getParentFile().mkdirs();
                        filePic.createNewFile();
                    }
                    FileOutputStream fos = new FileOutputStream(filePic);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                mBitmapLoaclPath =  filePic.getAbsolutePath();
            }
        }).start();
        return;
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_weixin) {//微信分享
            share2weixin(0);

        } else if (i == R.id.btn_pengyouquan) {//朋友圈分享
            share2weixin(1);

        } else if (i == R.id.btn_qq) {//qq分享
            shareToshareToQQ();

        } else if (i == R.id.btn_sina) {//新浪微博分享
            shareToSina();

        } else if (i == R.id.btn_cancel) {
            dismiss();

        }
    }

    /**
     * @param context
     * @param uniqueName
     * @return
     */
    public static String getDiskCacheDirect(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return cachePath + File.separator + uniqueName;
    }

    private void shareToshareToQQ() {
        //分享 QQ
    }

    private void share2weixin(int flag) {
     //分享微信 和 微信朋友圈
    }

    private void shareToSina() {
    //分享至新浪微博
    }
}
