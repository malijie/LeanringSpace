package com.math.exam.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.common.lib.base.CommonBase;
import com.common.lib.util.IntentManager;
import com.common.lib.util.SharedPreferenceUtil;
import com.common.lib.util.Utils;

/**
 * Created by malijie on 2017/5/25.
 */

public abstract class BaseActivity extends Activity{
    private ImageButton mButtonBack = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public int getResColor(int resId){
        return Utils.getColor(resId);
    }

    public abstract void initData();
    public abstract void initViews();


    public void saveLastProgress(int groupId,int itemId,int position){
        SharedPreferenceUtil.saveProgress(groupId,itemId,position);
    }

    public void finish(Activity activity){
        IntentManager.finishActivity(activity);
    }


}
