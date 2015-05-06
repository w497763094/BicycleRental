package com.wudebin.bicyclerental.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.wudebin.bicyclerental.activity.BaseActivity;
import com.wudebin.bicyclerental.constant.ActivityAction;

import com.wudebin.bicyclerental.R;

public class ForgetAuthActivity extends BaseActivity implements View.OnClickListener{
    private Toolbar mToolbar;
    private static final int PHONE = 1;
    private static final int EMAIL = 2;
    private int mCurrentType = 1;
    private FloatingActionButton mFabNext;
    private RelativeLayout mPhoneLayout;
    private TextView mPhoneMsg;
    private Button mBtnGetCode;
    private Intent intent;
    private TimerCount mTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_auth);
        initToolbar();
        init();
    }

    public void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.auth));
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

    public void init() {
        mPhoneLayout = (RelativeLayout) findViewById(R.id.phone_auth_layout);
        intent = getIntent();
        mCurrentType = intent.getIntExtra("type", PHONE);
        if (mCurrentType == PHONE) {
            initPhone();
        } else if (mCurrentType == EMAIL) {
        }
        mFabNext = (FloatingActionButton) findViewById(R.id.forget_auth_next);
        mFabNext.setOnClickListener(this);
    }


    public void initPhone() {
        mTimer = new TimerCount(60 * 1000, 1000);
        mPhoneMsg = (TextView) findViewById(R.id.phone_show_text);
        mBtnGetCode= (Button) findViewById(R.id.forget_getcode);
        mPhoneLayout.setVisibility(View.VISIBLE);
        mPhoneMsg.setText(getString(R.string.auth_msg_before) + intent.getStringExtra("phone") + getString(R.string.auth_phone_msg_after));
        mBtnGetCode.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.forget_auth_next:
                Intent intent = new Intent();
                intent.setAction(ActivityAction.SETPWD_ACTIVITY);
                startActivity(intent);
                break;
            case R.id.forget_getcode:
                mTimer.start();
                break;
        }
    }

    class TimerCount extends CountDownTimer {
        public TimerCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            mBtnGetCode.setText(R.string.get_authcode);
            mBtnGetCode.setEnabled(true);
        }

        @Override
        public void onTick(long arg0) {
            mBtnGetCode.setEnabled(false);
            mBtnGetCode.setText(arg0 / 1000 + getString(R.string.register_countdown));
        }

    }
}
