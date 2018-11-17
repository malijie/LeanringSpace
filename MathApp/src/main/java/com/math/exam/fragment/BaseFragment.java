package com.math.exam.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.lib.ui.CustomDialog;
import com.common.lib.util.PermissionController;
import com.common.lib.util.ToastManager;
import com.common.lib.util.Utils;
import com.dl7.player.activity.IjkFullscreenActivity;
import com.dl7.player.entity.VideoInfo;
import com.math.exam.MathApp;
import com.math.exam.R;
import com.pay.lib.pay.PayBaseInfo;
import com.pay.lib.pay.PayManager;

/**
 * Created by malijie on 2018/11/11.
 */

public class BaseFragment extends Fragment{
    public PayManager mPayManager =  null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mPayManager = PayManager.getInstance(MathApp.sContext);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
