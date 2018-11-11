package com.math.exam;

import android.app.Application;

import com.common.lib.base.CommonBase;

/**
 * Created by malijie on 2018/11/6.
 */

public class MathApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        CommonBase.init(this);
    }
}
