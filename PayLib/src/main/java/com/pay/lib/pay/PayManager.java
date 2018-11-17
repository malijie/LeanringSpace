package com.pay.lib.pay;

import android.content.Context;

import com.common.lib.util.SharedPreferenceUtil;
import com.common.lib.util.ToastManager;
import com.pay.lib.wap.WapManager;
import com.wanpu.pay.PayConnect;
import com.wanpu.pay.PayResultListener;

import cn.waps.AppConnect;

/**
 * Created by malijie on 2018/11/3.
 */

public class PayManager extends PayBaseInfo implements IPayType {
    private static PayManager payManager = null;
    private static Object sObject = new Object();
    private Context ctx = null;
    private WapManager wapManager = null;
    private AppConnect mAppConnect = null;
    private PayConnect mPayConnect = null;

    private PayManager(Context context) {
        this.ctx = context;
        wapManager = WapManager.getInstance(ctx);
        mAppConnect = wapManager.getAppConnect();
        mPayConnect = PayConnect.getInstance(ctx);
    }

    public static PayManager getInstance(Context context) {
        if (payManager == null) {
            synchronized (sObject) {
                payManager = new PayManager(context);
            }
        }
        return payManager;
    }


    @Override
    public void payForPoliticsVideo() {
        String userId = mPayConnect.getDeviceId(ctx);
        String orderId = String.valueOf(System.currentTimeMillis());
        mPayConnect.pay(ctx, orderId, userId, POLITICS_VIDEO_PRICE,
                ITEM_POLITICS_VIDEO, ITEM_POLITICS_VIDEO_DESCR, "", new MyPayResultListener(POLITICS_VIDEO));

    }

    @Override
    public void payForPoliticsQuestions() {
        String userId = mPayConnect.getDeviceId(ctx);
        String orderId = String.valueOf(System.currentTimeMillis());
        mPayConnect.pay(ctx, orderId, userId, POLITICS_QUESTION_PRICE,
                ITEM_POLITICS_QUESTION, ITEM_POLITICS_QUESTION_DESCR, "", new MyPayResultListener(POLITICS_QUESTION));

    }

    @Override
    public void payForMathVideo(int type) {
        String userId = PayConnect.getInstance(ctx).getDeviceId(ctx);
        String orderId = String.valueOf(System.currentTimeMillis());
        switch (type) {
            case PayBaseInfo.MATH1:
                mPayConnect.pay(ctx, orderId, userId, MATH1_VIDEO_PRICE,
                        ITEM_MATH1_VIDEO, ITEM_MATH1_VIDEO_DESCR, "", new MyPayResultListener(MATH_VIDEO));

                break;
            case PayBaseInfo.MATH2:
                mPayConnect.pay(ctx, orderId, userId, MATH2_VIDEO_PRICE,
                        ITEM_MATH2_VIDEO, ITEM_MATH2_VIDEO_DESCR, "", new MyPayResultListener(MATH_VIDEO));

                break;
            case PayBaseInfo.MATH3:
                mPayConnect.pay(ctx, orderId, userId, MATH3_VIDEO_PRICE,
                        ITEM_MATH3_VIDEO, ITEM_MATH3_VIDEO_DESCR, "", new MyPayResultListener(MATH_VIDEO));

                break;
        }

    }

    /**
     * 是否购买过视频服务
     *
     * @return
     */
    public boolean hasPayedMathVideo() {
        return SharedPreferenceUtil.loadPayedVideoStatus();
    }

    private class MyPayResultListener implements PayResultListener {
        private int payGoods = 1;

        MyPayResultListener(int payGoods) {
            this.payGoods = payGoods;
        }

        @Override
        public void onPayFinish(Context payViewContext, String orderId,
                                int resultCode, String resultString, int payType, float amount,
                                String goodsName) {
            // 可根据resultCode自行判断
            if (resultCode == 0) {
                ToastManager.showShortMsg("购买成功");
                // 支付成功时关闭当前支付界面
                PayConnect.getInstance(ctx).closePayView(payViewContext);

                // 未指定notifyUrl的情况下，交易成功后，必须发送回执
                PayConnect.getInstance(ctx).confirm(orderId, payType);
                if (payGoods == POLITICS_QUESTION) {
                    //政治题库
                    SharedPreferenceUtil.savePayedQuestionStatus(true);
                } else if (payGoods == POLITICS_VIDEO) {
                    //政治视频
                    SharedPreferenceUtil.savePayedVideoStatus(true);
                } else if (payGoods == MATH_VIDEO) {
                    //数学视频
                    SharedPreferenceUtil.savePayedVideoStatus(true);

                }
            } else {
                ToastManager.showShortMsg("购买失败");
            }
        }
    }
}
