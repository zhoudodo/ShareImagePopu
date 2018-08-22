package com.zls.www.shareimagepopu.popu;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zls.www.shareimagepopu.R;


@SuppressLint("ViewConstructor")
public abstract class BasePopu extends PopupWindow implements OnClickListener {
    protected FragmentActivity mActivity;

    protected OnPupClickListener listener;

    protected int heightNavigationBar = 0;


    public BasePopu(FragmentActivity act, int idRes, int width, int height) {
        this(act,LayoutInflater.from(act).inflate(idRes, null), width, height);
    }

    public BasePopu(FragmentActivity act, View contentView, int width, int height) {
        super(contentView, width, height, true);
        mActivity = act;
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.setOnDismissListener(new OnDismissListener() {
            public void onDismiss() {
                WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
                if (lp.alpha != 1f) {
                    lp.alpha = 1f;
                    mActivity.getWindow().setAttributes(lp);
                }
                afterDismiss();
            }
        });
        init();
    }




    //底部暂时时 .需要处理 虚拟导航键的高度
    public void setHeightNavigationBar(int heightNavigationBar) {
        this.heightNavigationBar = heightNavigationBar;
    }




    public void showBottom() {
        if (mActivity.isFinishing())
            return;
        setAnimationStyle(R.style.Popup_Animation_UpDown);
        beforeshow();
        showAtLocation(mActivity.getWindow().getDecorView(), Gravity.BOTTOM, 0, heightNavigationBar);

        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        lp.alpha = 0.7f; //屏幕变暗 0.0-1.0
        mActivity.getWindow().setAttributes(lp);
            update();
    }




    protected void beforeshow() {
    }

    protected void afterDismiss() {
    }

    protected abstract void init();

    //点击回调
    public interface OnPupClickListener {
        void onPupClick(int position);
    }

    public OnPupClickListener getListener() {
        return listener;
    }

    public void setOnPupClicListener(OnPupClickListener listener) {
        this.listener = listener;
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T findViewsId(int id, boolean clickAble) {
        T views = ViewBindHelper.findViews(getContentView(), id);
        if (clickAble)
            views.setOnClickListener(this);
        return views;
    }

    //提取方法
    public void setText(@IdRes int viewId, CharSequence text) {
        TextView view = this.findViewsId(viewId, false);
        view.setText(text);
    }

    @Override
    public void update() {
        if (android.os.Build.VERSION.SDK_INT == 24) {
            //update 方法有问题 gravity 被重置
        }else {
            super.update();
        }
        }
}