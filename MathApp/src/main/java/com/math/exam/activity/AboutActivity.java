package com.math.exam.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.common.lib.base.BaseActivity;
import com.math.exam.R;


/**
 * Created by malijie on 2017/8/30.
 */

public class AboutActivity extends BaseActivity {
    private TextView mTextAbout = null;
    private ImageButton mButtonBack = null;
    private TextView mTextTitle = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);
        initData();
        initViews();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initViews() {
        String content =
                "<p>1. 本app包含了2019考研数学专家组名师在线课程，助力考研学子数学高分</p>" +
                        "<p>2. 高等数学-张宇，启航考研数学老师，从事高等数学教学和考研辅导多年，在全国核心期刊发表论文多篇</p>" +
                        "<p>3. 线性代数-李永乐，全国最著名的考研数学线性代数辅导专家，有线代王之称。</p>" +
                        "<p>4. 概率与数理统计-汤家凤，连续20年从事考研数学教学和命题研究工作，辅导出个多个满分学员</p>" +
                        "<p>5. 联系邮箱：190223629@qq.com</p>";
        mTextAbout = (TextView) findViewById(R.id.id_about_text_content);
        mButtonBack = (ImageButton) findViewById(R.id.id_title_bar_button_back);
        mTextTitle = (TextView) findViewById(R.id.id_title_bar_text_title);

        mTextAbout.setText(Html.fromHtml(content));
        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(AboutActivity.this);
            }
        });
        mTextTitle.setText("关于我们");
    }
}
