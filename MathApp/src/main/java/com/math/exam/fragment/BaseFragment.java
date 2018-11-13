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
import com.math.exam.R;
import com.pay.lib.pay.PayBaseInfo;
import com.pay.lib.pay.PayManager;

/**
 * Created by malijie on 2018/11/11.
 */

public class BaseFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
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
                    if(){
                        showPayTip();
                    }else{
                        VideoInfo videoInfo = new VideoInfo();
                        videoInfo.setName(videoItem[i]);
                        videoInfo.setUrl(videoUrl[i]);
                        Intent i = new Intent(getActivity(), IjkFullscreenActivity.class);
                        i.putExtra("videoInfo",videoInfo);
                        startActivity(i);
                    }

                }
            });


            return view;
        }


        class ViewHolder{
            public TextView textName;
            public TextView textTime;
            public RelativeLayout mLayout;

        }
    }


    private void showPayTip() {
        final CustomDialog dialog = new CustomDialog(getActivity(), R.layout.dialog_layout,PayBaseInfo.ITEM_MATH_VIDEO,PayBaseInfo.ITEM_MATH_VIDEO_DESCR);
        dialog.setButtonClickListener(new CustomDialog.DialogButtonListener() {
            @Override
            public void onConfirm() {
                if(PermissionController.checkPermission(getActivity())){
                    PayManager.getInstace(getActivity()).payForPoliticsVideo();
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
