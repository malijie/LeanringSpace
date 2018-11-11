package com.math.exam.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.common.lib.util.Utils;
import com.math.exam.R;

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
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder = null;
            if(view == null){
                holder = new ViewHolder();
                view = Utils.getView(R.layout.video_expand_item_view);
                holder.textName = view.findViewById(R.id.id_video_expand_item_text_title);
                holder.textTime = view.findViewById(R.id.id_video_expand_item_text_length);
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


            return view;
        }


        class ViewHolder{
            public TextView textName;
            public TextView textTime;

        }
    }
}
