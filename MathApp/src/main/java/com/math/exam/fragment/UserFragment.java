package com.math.exam.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.common.lib.util.IntentManager;
import com.math.exam.R;
import com.pay.lib.wap.WapManager;


/**
 * Created by malijie on 2016/12/8.
 */

public class UserFragment extends Fragment {
    private Button mButtonFeedback;
    private Button mButtonAbout;
    private Button mButtonUpdate;
    private Button mButtonWrongQuestion;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View messageLayout = inflater.inflate(R.layout.user_fragment, container, false);
        mButtonFeedback = (Button) messageLayout.findViewById(R.id.id_user_fragment_btn_feedback);
        mButtonAbout = (Button) messageLayout.findViewById(R.id.id_user_fragment_btn_about);
        mButtonUpdate = (Button) messageLayout.findViewById(R.id.id_user_fragment_btn_update);
        mButtonWrongQuestion = (Button) messageLayout.findViewById(R.id.id_user_fragment_btn_questions);
        final WapManager wapManager = WapManager.getInstance(getActivity());


        mButtonFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wapManager.feedback(getActivity());
            }
        });

//        mButtonAbout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                wapManager.about(getActivity(),AboutActivity.class);
//            }
//        });

        mButtonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wapManager.update(getActivity());
            }
        });

        return messageLayout;
    }

}