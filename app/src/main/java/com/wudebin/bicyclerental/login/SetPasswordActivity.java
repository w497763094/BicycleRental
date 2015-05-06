package com.wudebin.bicyclerental.login;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.wudebin.bicyclerental.R;
import com.wudebin.bicyclerental.activity.BaseActivity;
import com.wudebin.bicyclerental.util.StringUtil;


public class SetPasswordActivity extends BaseActivity {

    private Toolbar mToolbar;
    private CheckBox mPwdVisible;
    private MaterialEditText mEditPwd;
    private Button mBtnFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setpwd);
        initToolbar();
        init();
    }

    public void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.reset_pwd));
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

    public void init()
    {
        mPwdVisible= (CheckBox) findViewById(R.id.pwd_visible);
        mEditPwd= (MaterialEditText) findViewById(R.id.set_edit_pwd);
        mBtnFinish= (Button) findViewById(R.id.btn_set_finish);
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

        mBtnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!StringUtil.isPassword(mEditPwd.getText().toString())) {
                    mEditPwd.setError(getString(R.string.password_error));
                }
            }
        });
        mPwdVisible.setChecked(false);
    }
}
