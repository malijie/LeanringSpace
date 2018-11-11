package com.math.exam.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.lib.util.Utils;
import com.dl7.player.activity.IjkFullscreenActivity;
import com.dl7.player.entity.VideoInfo;
import com.math.exam.R;
import com.math.exam.base.Data;
import com.pay.lib.pay.PayManager;

/**
 * Created by malijie on 2018/10/25.
 */

public class VideoLearningFragment extends Fragment{

    private ExpandableListView eLv = null;
    private ExpandableListAdapter eAdapter = null;
    private TextView mTextVideoName = null;
    private Button mBtnLearn  = null;

    private static final String[] VIDEO_GROUP = Data.VIDEO_GROUP;
    private static final String[][] VIDEO_ITEM = Data.VIDEO_ITEM;
    private static final int[][] VIDEO_LENGTH = Data.VIDEO_LENGTH;
    private static final String[][] VIDEO_URL = Data.VIDEO_URL;
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
        eLv = (ExpandableListView) v.findViewById(R.id.id_video_learning_lv);
        mBtnLearn = (Button) v.findViewById(R.id.id_video_btn_start_learn);
        mTextVideoName = (TextView) v.findViewById(R.id.id_video_text_start_learn);

        eLv.setGroupIndicator(null);

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


        eAdapter = new BaseExpandableListAdapter() {


            @Override
            public void registerDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public int getGroupCount() {
                return VIDEO_GROUP.length;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return VIDEO_ITEM[groupPosition].length;
            }

            @Override
            public String getGroup(int groupPosition) {
                return VIDEO_GROUP[groupPosition];
            }

            @Override
            public String getChild(int groupPosition, int childPosition) {
                return VIDEO_ITEM[groupPosition][childPosition];
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                View mGroupView = Utils.getView(R.layout.video_expand_group_view);
                TextView mTextTitle = (TextView) mGroupView.findViewById(R.id.id_video_expand_group_text_title);
                TextView mTextLesson = (TextView) mGroupView.findViewById(R.id.id_video_expand_group_text_lesson);
                mTextTitle.setText(VIDEO_GROUP[groupPosition]);
                mTextLesson.setText(VIDEO_ITEM[groupPosition].length + "课");
                return mGroupView;
            }

            @Override
            public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                View mItemView = Utils.getView(R.layout.video_expand_item_view);
                TextView mTextTitle = (TextView) mItemView.findViewById(R.id.id_video_expand_item_text_title);
                TextView mTextLength = (TextView) mItemView.findViewById(R.id.id_video_expand_item_text_length);

                mTextTitle.setText(VIDEO_ITEM[groupPosition][childPosition]);

                int min = VIDEO_LENGTH[groupPosition][childPosition]/60;
                int sec = VIDEO_LENGTH[groupPosition][childPosition]%60;
                String strMin = min<10? "0" + min :"" + min;
                String strSec = sec<10? "0" + sec :"" + sec;

                mTextLength.setText(strMin + ":" + strSec);

                RelativeLayout itemLayout = (RelativeLayout) mItemView.findViewById(R.id.id_video_expand_item_layout);




//                if(groupPosition <VIDEO_GROUP.length -1){
                    itemLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

//                            if(childPosition != 0 && !SharedPreferenceUtil.loadPayedVideoStatus()){
//                                showPayTip();
//                                return;
//                            }

                            VideoInfo videoInfo = new VideoInfo();
                            videoInfo.setName(VIDEO_ITEM[groupPosition][childPosition]);
                            videoInfo.setUrl(VIDEO_URL[groupPosition][childPosition]);

//                            VideoManager.saveCurrentInfo(videoInfo);
                            startVideoPlayActivity(videoInfo);

                        }
                    });
//                }

                return mItemView;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }

            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

        };

        eLv.setAdapter(eAdapter);
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
