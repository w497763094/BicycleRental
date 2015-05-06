package com.wudebin.bicyclerental.login;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.wudebin.bicyclerental.activity.BaseActivity;

import com.wudebin.bicyclerental.R;

public class RegisterActivity extends BaseActivity {

    private Toolbar mToolbar;
    private FragmentManager mFragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initToolbar();
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().replace(R.id.register_container, new RegisterPhoneFragment()).commit();
    }

    public void initToolbar(){
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.register));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(R.drawable.back_icon);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
