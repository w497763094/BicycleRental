package com.wudebin.bicyclerental.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.wudebin.bicyclerental.activity.BaseActivity;
import com.wudebin.bicyclerental.constant.ActivityAction;
import com.wudebin.bicyclerental.util.StringUtil;

import com.wudebin.bicyclerental.R;

public class ForgetPasswordActivity extends BaseActivity {
    private Toolbar mToolbar;
    private FloatingActionButton mFabNext;
    private TextView mLoseAll;
    private MaterialEditText mEditAccount;
    private static final int PHONE = 1;
    private static final int EMAIL = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpwd);
        initToolbar();
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.forget_password));
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
        mFabNext = (FloatingActionButton) findViewById(R.id.forget_next);
        mLoseAll = (TextView) findViewById(R.id.forget_review);
        mEditAccount = (MaterialEditText) findViewById(R.id.forget_edit_account);
        mFabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if (!StringUtil.isMobileNO(mEditAccount.getText().toString())) {
                    if (!StringUtil.isEmail(mEditAccount.getText().toString())) {
                        mEditAccount.setError(getString(R.string.edit_error));
                        return;
                    }else
                    {
                        intent.putExtra("type",EMAIL);
                        intent.putExtra("email",mEditAccount.getText().toString());
                    }
                }else
                {
                    intent.putExtra("type",PHONE);
                    intent.putExtra("phone",mEditAccount.getText().toString());
                }

                intent.setAction(ActivityAction.FORGETAUTH_ACTIVITY);
                startActivity(intent);
            }
        });
        mLoseAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(ActivityAction.FORGETREVIEW_ACTIVITY);
                startActivity(intent);
            }
        });
    }
}
