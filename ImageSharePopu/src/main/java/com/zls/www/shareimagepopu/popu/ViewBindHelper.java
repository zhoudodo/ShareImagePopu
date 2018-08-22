package com.zls.www.shareimagepopu.popu;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 控件查找 赋值 by joeYu on 17/3/8.
 */

public class ViewBindHelper {

    @SuppressWarnings("unchecked")
    public static <T extends View> T findViews(View rootView, int id) {
        T views = rootView.findViewById(id);
        return views;
    }

    public static void setImgs(View rootView, @IdRes int viewId, @DrawableRes int imageResId) {
        ImageView view = findViews(rootView, viewId);
        view.setImageResource(imageResId);
    }

    public static void setImgs(View rootView, @IdRes int viewId, Drawable imgRes) {
        ImageView view = findViews(rootView, viewId);
        view.setImageDrawable(imgRes);
    }


    //提取方法
    public static void setText(View rootView, @IdRes int viewId, CharSequence text) {
        TextView view = findViews(rootView, viewId);
        view.setText(text);
    }

    public static ImageView getImageView(View rootView, @IdRes int viewId) {
        return (ImageView) findViews(rootView, viewId);
    }

    public static TextView getTextView(View rootView, @IdRes int viewId) {
        return (TextView) findViews(rootView, viewId);
    }

}
