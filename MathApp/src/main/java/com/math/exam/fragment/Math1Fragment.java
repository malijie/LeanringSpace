package com.math.exam.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.lib.util.Utils;
import com.dl7.player.activity.IjkFullscreenActivity;
import com.dl7.player.entity.VideoInfo;
import com.math.exam.R;
import com.math.exam.base.Data;
import com.pay.lib.pay.PayManager;

import java.util.List;

/**
 * Created by malijie on 2018/10/25.
 */

public class Math1Fragment extends BaseFragment{

    private ListView mListView = null;
    private BaseFragment.MathAdapter mAdapter = null;
    private TextView mTextVideoName = null;
    private Button mBtnLearn  = null;

    private static final String[]VIDEO_ITEM = Data.VIDEO_ITEM1;
    private static final String[] VIDEO_URL = Data.VIDEO_URL1;
    private static final int[]VIDEO_LENGTH = Data.VIDEO_LENGTH1;
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

    private void initData(){
        mPayManager = PayManager.getInstace(getActivity());
    }


    private void startVideoPlayActivity(VideoInfo videoInfo){
        Intent i = new Intent(getActivity(),IjkFullscreenActivity.class);
        i.putExtra("videoInfo",videoInfo);
        startActivity(i);
    }



}
