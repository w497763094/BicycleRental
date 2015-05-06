package com.wudebin.bicyclerental.login;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.method.NumberKeyListener;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.wudebin.bicyclerental.activity.BaseActivity;
import com.wudebin.bicyclerental.constant.ActivityAction;
import com.wudebin.bicyclerental.util.StringUtil;

import com.wudebin.bicyclerental.R;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private TextView mForgetPassword;
    private FloatingActionButton mFabLogin;
    private MaterialEditText mEditAccount;
    private MaterialEditText mEditPwd;
    private CheckBox mPwdVisible;
    private Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initToolbar();
        init();
    }

    public void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.login));
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
        mForgetPassword = (TextView) findViewById(R.id.login_forgetpassword);
        mEditAccount = (MaterialEditText) findViewById(R.id.login_account);
        mEditPwd = (MaterialEditText) findViewById(R.id.login_pwd);
        mFabLogin = (FloatingActionButton) findViewById(R.id.fab_login);
        mPwdVisible = (CheckBox) findViewById(R.id.pwd_visible);
        mForgetPassword.setOnClickListener(this);
        mForgetPassword.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        mFabLogin.setOnClickListener(this);
        mPwdVisible.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mEditPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    Editable etable = mEditPwd.getText();
                    Selection.setSelection(etable, etable.length());
                } else {
                    mEditPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    Editable etable = mEditPwd.getText();
                    Selection.setSelection(etable, etable.length());
                }
            }
        });
        mPwdVisible.setChecked(false);
        mEditPwd.setKeyListener(new NumberKeyListener() {
            @Override
            protected char[] getAcceptedChars() {
                return new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
                        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
            }

            @Override
            public int getInputType() {
                return InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_VARIATION_PASSWORD;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_forgetpassword:
                intent.setAction(ActivityAction.FORGETPWD_ACTIVITY);
                startActivity(intent);
                break;
            case R.id.fab_login:
                login();
                break;
        }
    }

    public void login() {
            if (!StringUtil.isMobileNO(mEditAccount.getText().toString())) {
                    mEditAccount.setError(getString(R.string.edit_error));
                    return;
            } else if (!StringUtil.isPassword(mEditPwd.getText().toString())) {
                mEditPwd.setError(getString(R.string.password_error));
                return;
            }
        intent.setAction(ActivityAction.MAIN_ACTIVITY);
        startActivity(intent);
        this.finish();
    }


}
