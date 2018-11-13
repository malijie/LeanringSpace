package com.math.exam.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.dl7.player.activity.IjkFullscreenActivity;
import com.dl7.player.entity.VideoInfo;
import com.math.exam.R;
import com.math.exam.base.Data;
import com.pay.lib.pay.PayManager;

/**
 * Created by malijie on 2018/10/25.
 */

public class Math3Fragment extends BaseFragment{

    private ListView mListView = null;
    private MathAdapter mAdapter = null;
    private TextView mTextVideoName = null;
    private Button mBtnLearn  = null;

    private static final String[]VIDEO_ITEM = Data.VIDEO_ITEM3;
    private static final String[] VIDEO_URL = Data.VIDEO_URL3;
    private static final int[]VIDEO_LENGTH = Data.VIDEO_LENGTH3;
//
    private PayManager mPayManager = null;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.video_learning_fragment,null);

        initView(v);
        initData();

        return v;
    }

    private void initView(View v) {
        mListView = (ListView) v.findViewById(R.id.id_video_learning_lv);
        mListView.setAdapter(new MathAdapter(VIDEO_ITEM,VIDEO_URL,VIDEO_LENGTH));

//        mBtnLearn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                VideoInfo curVideoInfo = new VideoInfo();
//                if(TextUtils.isEmpty(VideoManager.getCurrentName())){
//                    curVideoInfo.setName(VIDEO_ITEM[0][0]);
//                    curVideoInfo.setUrl(VIDEO_URL[0][0]);
//                }else{
//                    curVideoInfo.setName(VideoManager.getCurrentName());
//                    curVideoInfo.setUrl(VideoManager.getCurrentUrl());
//                }
//                startVideoPlayActivity(curVideoInfo);
//            }
//        });



    }

//    private void showPayTip() {
//        final CustomDialog dialog = new CustomDialog(getActivity(), PayBaseInfo.ITEM_POLITICS_VIDEO,PayBaseInfo.ITEM_POLITICS_VIDEO_DESCR);
//        dialog.setButtonClickListener(new CustomDialog.DialogButtonListener() {
//            @Override
//            public void onConfirm() {
//                if(PermissionController.checkPermission(getActivity())){
//                    mPayManager.payForPoliticsVideo();
//                    dialog.dissmiss();
//                }else{
//                    ToastManager.showLongMsg("未打开权限，请到设置-应用中打开相关权限后完成支付");
//                    dialog.dissmiss();
//                }
//
//            }
//
//            @Override
//            public void onCancel() {
//                dialog.dissmiss();
//            }
//        });
//        dialog.show();
//    }
//
    private void initData(){
        mPayManager = PayManager.getInstace(getActivity());
    }


    private void startVideoPlayActivity(VideoInfo videoInfo){
        Intent i = new Intent(getActivity(),IjkFullscreenActivity.class);
        i.putExtra("videoInfo",videoInfo);
        startActivity(i);
    }



}
