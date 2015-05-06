package com.wudebin.bicyclerental.systemsetting;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.wudebin.bicyclerental.R;
import com.wudebin.bicyclerental.activity.BaseActivity;

public class ContactUsActivity extends BaseActivity {
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        initView();
    }
    public void initView()
    {
        mToolbar=(Toolbar)findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.contactus);
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


}
