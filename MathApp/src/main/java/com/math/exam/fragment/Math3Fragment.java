package com.math.exam.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.lib.ui.CustomDialog;
import com.common.lib.util.PermissionController;
import com.common.lib.util.ToastManager;
import com.common.lib.util.Utils;
import com.dl7.player.activity.IjkFullscreenActivity;
import com.dl7.player.entity.VideoInfo;
import com.math.exam.R;
import com.math.exam.base.Data;
import com.pay.lib.pay.PayBaseInfo;
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

    private void initData(){
        mPayManager = PayManager.getInstance(getActivity());
    }

    protected class MathAdapter extends BaseAdapter {

        private String[] videoItem = null;
        private String[] videoUrl = null;
        private int[] videoLength = null;

        MathAdapter(String[] videoItem,String[] videoUrl,int[] videoLength){
            this.videoItem = videoItem;
            this.videoUrl = videoUrl;
            this.videoLength = videoLength;
        }

        @Override
        public int getCount() {
            return videoItem.length;
        }

        @Override
        public Object getItem(int i) {
            return videoItem[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
             ViewHolder holder = null;
            if(view == null){
                holder = new ViewHolder();
                view = Utils.getView(R.layout.video_expand_item_view);
                holder.textName = view.findViewById(R.id.id_video_expand_item_text_title);
                holder.textTime = view.findViewById(R.id.id_video_expand_item_text_length);
                holder.mLayout = view.findViewById(R.id.id_video_expand_item_layout);
                view.setTag(holder);
            }else{
                holder = (ViewHolder) view.getTag();
            }


            holder.textName.setText(videoItem[i]);
            int min = videoLength[i]/60;
            int sec = videoLength[i]%60;
            String strMin = min<10 ? "0" + min : min + "";
            String strSec = sec<10 ? "0" + sec : sec + "";
            holder.textTime.setText(strMin + ":" + strSec);


            holder.mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (i >= 1) {
                        if (!mPayManager.hasPayedMath3Video()) {
                            showPayTip();
                        } else {
                            if (PermissionController.checkPermission(getActivity())) {
                                startGoPlay(i);
                            }

                        }

                    }else{
                        startGoPlay(i);
                    }
                }
                    
            });


            return view;
        }

        private void startGoPlay(int index) {
            VideoInfo videoInfo = new VideoInfo();
            videoInfo.setName(videoItem[index]);
            videoInfo.setUrl(videoUrl[index]);
            Intent intent = new Intent(getActivity(), IjkFullscreenActivity.class);
            intent.putExtra("videoInfo", videoInfo);
            startActivity(intent);
        }


        public class ViewHolder{
            public TextView textName;
            public TextView textTime;
            public RelativeLayout mLayout;

        }
    }


    private void showPayTip() {
        final CustomDialog dialog = new CustomDialog(getActivity(), R.layout.dialog_layout, PayBaseInfo.ITEM_MATH3_VIDEO,PayBaseInfo.ITEM_MATH3_VIDEO_DESCR);
        dialog.setButtonClickListener(new CustomDialog.DialogButtonListener() {
            @Override
            public void onConfirm() {
                if(PermissionController.checkPermission(getActivity())){
                    mPayManager.payForMathVideo(PayBaseInfo.MATH3);
                    dialog.dissmiss();
                }else{
                    ToastManager.showLongMsg("未打开权限，请到设置-应用中打开相关权限后完成支付");
                    dialog.dissmiss();
                }

            }

            @Override
            public void onCancel() {
                dialog.dissmiss();
            }
        });
        dialog.show();
    }


}
