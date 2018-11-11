package com.math.exam.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.lib.util.PermissionController;
import com.common.lib.util.ToastManager;
import com.math.exam.R;
import com.math.exam.fragment.Math2Fragment;
import com.math.exam.fragment.Math3Fragment;
import com.math.exam.fragment.UserFragment;
import com.math.exam.fragment.Math1Fragment;
import com.pay.lib.wap.WapManager;


/**
 * Created by malijie on 2016/12/8.
 */

public class HomePageActivity extends BaseActivity implements View.OnClickListener{
    private Math1Fragment mMath1Fragment;
    private Math2Fragment mMath2Fragment;
    private Math3Fragment mMath3Fragment;
    private UserFragment mUserFragment;

    private View mMath1Layout;
    private View mMath2Layout;
    private View mMath3Layout;
    private View mUserLayout;

    private ImageView mMath1Image;
    private ImageView mMath2Image;
    private ImageView mMath3Image;
    private ImageView mUserImage;

    private TextView mTextTitle;

    private TextView mMath1Text;
    private TextView mMath2Text;
    private TextView mMath3Text;
    private TextView mUserText;

    private ImageButton mButtonBack;
    private ImageButton mButtonRevert;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.home_page);
        // 初始化布局元素
        initData();
        initViews();
        fragmentManager = getFragmentManager();
        // 第一次启动时选中第0个tab
        setTabSelection(0);
    }

    @Override
    public void initData() {
        if(PermissionController.checkPermission(this)){
            WapManager.getInstance(this);
//            PayConnect.getInstance(this);
        }
    }

    @Override
    public void initViews() {
        mTextTitle = (TextView) findViewById(R.id.id_title_bar_text_title);

        mMath1Layout = findViewById(R.id.math1_layout);
        mMath2Layout = findViewById(R.id.math2_layout);
        mMath3Layout = findViewById(R.id.math3_ayout);
        mUserLayout = findViewById(R.id.user_layout);


        mMath1Image = (ImageView) findViewById(R.id.math1_image);
        mMath2Image = (ImageView) findViewById(R.id.math2_image);
        mMath3Image = (ImageView) findViewById(R.id.math3_image);
        mUserImage = (ImageView) findViewById(R.id.user_image);

        mMath1Text = (TextView) findViewById(R.id.math1_text);
        mMath2Text = (TextView) findViewById(R.id.math2_text);
        mMath3Text = (TextView) findViewById(R.id.math3_text);
        mUserText = (TextView) findViewById(R.id.user_text);

        mButtonBack = (ImageButton) findViewById(R.id.id_title_bar_button_back);
        mButtonRevert = (ImageButton) findViewById(R.id.id_title_bar_button_revert);

        mMath1Layout.setOnClickListener(this);
        mMath2Layout.setOnClickListener(this);
        mMath3Layout.setOnClickListener(this);
        mUserLayout.setOnClickListener(this);

        mButtonRevert.setVisibility(View.GONE);
        mButtonBack.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.math1_layout:
                setTabSelection(0);
                break;
            case R.id.math2_layout:
                setTabSelection(1);
                break;
            case R.id.math3_ayout:
                // 当点击了联系人tab时，选中第3个tab
                setTabSelection(2);
                break;
            case R.id.user_layout:
                // 当点击了动态tab时，选中第4个tab
                setTabSelection(3);

                break;
            default:
                break;
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index
     *            每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。
     */
    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                mMath1Image.setImageResource(R.mipmap.play);
                mMath1Text.setTextColor(getResColor(R.color.font_green));
                mTextTitle.setText("高等数学");
                if (mMath1Fragment == null) {
                    // 如果ContactsFragment为空，则创建一个并添加到界面上
                    mMath1Fragment = new Math1Fragment();
                    transaction.add(R.id.content, mMath1Fragment);
                } else {
                    // 如果ContactsFragment不为空，则直接将它显示出来
                    transaction.show(mMath1Fragment);
                }
                break;
            case 1:
                // 当点击了考试tab时，改变控件的图片和文字颜色
                mMath2Image.setImageResource(R.mipmap.exam_normal);
                mMath2Text.setTextColor(getResColor(R.color.font_green));
                mTextTitle.setText("线性代数");
                if (mMath2Fragment == null) {
                    // 如果ContactsFragment为空，则创建一个并添加到界面上
                    mMath2Fragment = new Math2Fragment();
                    transaction.add(R.id.content, mMath2Fragment);
                } else {
                    // 如果ContactsFragment不为空，则直接将它显示出来
                    transaction.show(mMath2Fragment);
                }
                break;
            case 2:
                // 当点击了用户tab时，改变控件的图片和文字颜色
                mMath3Image.setImageResource(R.mipmap.user_unselected);
                mMath3Text.setTextColor(getResColor(R.color.font_green));
                mTextTitle.setText("概率与数理统计");
                if (mMath3Fragment == null) {
                    // 如果NewsFragment为空，则创建一个并添加到界面上
                    mMath3Fragment = new Math3Fragment();
                    transaction.add(R.id.content, mMath3Fragment);
                } else {
                    // 如果NewsFragment不为空，则直接将它显示出来
                    transaction.show(mMath3Fragment);
                }
                break;
            case 3:
                // 当点击了用户tab时，改变控件的图片和文字颜色
                mUserImage.setImageResource(R.mipmap.user_unselected);
                mUserText.setTextColor(getResColor(R.color.font_green));
                mTextTitle.setText("概率与数理统计");
                if (mUserFragment == null) {
                    // 如果NewsFragment为空，则创建一个并添加到界面上
                    mUserFragment = new UserFragment();
                    transaction.add(R.id.content, mUserFragment);
                } else {
                    // 如果NewsFragment不为空，则直接将它显示出来
                    transaction.show(mUserFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        mMath1Image.setImageResource(R.mipmap.play_select);
        mMath1Text.setTextColor(getResColor(R.color.font_green_selected));

        mMath2Image.setImageResource(R.mipmap.exam_select);
        mMath2Text.setTextColor(getResColor(R.color.font_green_selected));

        mMath3Image.setImageResource(R.mipmap.exam_select);
        mMath3Text.setTextColor(getResColor(R.color.font_green_selected));

        mUserImage.setImageResource(R.mipmap.user_select);
        mUserText.setTextColor(getResColor(R.color.font_green_selected));
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction
     *            用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (mMath1Fragment != null) {
            transaction.hide(mMath1Fragment);
        }
        if (mMath2Fragment != null) {
            transaction.hide(mMath2Fragment);
        }
        if (mMath3Fragment != null) {
            transaction.hide(mMath3Fragment);
        }
        if (mUserFragment != null) {
            transaction.hide(mUserFragment);
        }
    }

    private long waitTime = 2000;
    private long touchTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode) {
            long currentTime = System.currentTimeMillis();
            if((currentTime-touchTime)>=waitTime) {
                ToastManager.showExitTipMsg();
                touchTime = currentTime;
            }else {
                WapManager.getInstance(this).close();
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
