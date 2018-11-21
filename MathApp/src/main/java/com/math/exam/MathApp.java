package com.math.exam;

import android.app.Application;
import android.content.Context;

import com.common.lib.base.CommonBase;
import com.pay.lib.pay.PayManager;

/**
 * Created by malijie on 2018/11/6.
 */

public class MathApp extends Application{
    public static Context sContext = null;
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        CommonBase.init(this);
    }
}
