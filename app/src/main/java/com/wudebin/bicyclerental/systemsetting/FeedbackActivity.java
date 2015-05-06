package com.wudebin.bicyclerental.systemsetting;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.wudebin.bicyclerental.R;
import com.wudebin.bicyclerental.activity.BaseActivity;

public class FeedbackActivity extends BaseActivity implements View.OnClickListener{
    private Toolbar mToolbar;
    private ImageView mImageViewPhoto,mImageViewGallery,mImageViewCommit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        initView();
    }
    public void initView()
    {
        mToolbar=(Toolbar)findViewById(R.id.toolbar);
        mImageViewGallery=(ImageView)findViewById(R.id.from_gallery);
        mImageViewPhoto=(ImageView)findViewById(R.id.take_photo);
        mImageViewCommit=(ImageView)findViewById(R.id.commit);
        mToolbar.setTitle(R.string.feedback);
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.from_gallery:

                break;
            case R.id.take_photo:

                break;
            case R.id.commit:

                break;
        }
    }
}
