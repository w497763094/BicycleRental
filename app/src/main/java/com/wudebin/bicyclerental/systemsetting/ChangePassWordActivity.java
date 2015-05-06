package com.wudebin.bicyclerental.systemsetting;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.method.NumberKeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.wudebin.bicyclerental.R;
import com.wudebin.bicyclerental.activity.BaseActivity;
import com.wudebin.bicyclerental.util.StringUtil;

public class ChangePassWordActivity extends BaseActivity {
    private Toolbar mToolbar;
    private MaterialEditText mNewPWD,mUsedPWD;
    private CheckBox mPwdVisibleNew,mPwdVisibleUsed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepwd);
        initView();
    }
    public void initView()
    {
        mToolbar=(Toolbar)findViewById(R.id.toolbar);
        mNewPWD=(MaterialEditText)findViewById(R.id.change_pwd_new);
        mUsedPWD=(MaterialEditText)findViewById(R.id.change_pwd_used);
        mPwdVisibleNew=(CheckBox) findViewById(R.id.pwd_visible_bottom);
        mPwdVisibleUsed=(CheckBox) findViewById(R.id.pwd_visible_top);
        addlistener();
        mToolbar.setTitle(R.string.changepwd);
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
//                    case R.id.action_save:
//                        checkPWD();
//                        break;
//                    default:
//                        break;
                }
                return true;
            }
        });

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//                getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
    private void addlistener(){
        mPwdVisibleNew.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mNewPWD.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    Editable etable = mNewPWD.getText();
                    Selection.setSelection(etable, etable.length());
                } else {
                    mNewPWD.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    Editable etable = mNewPWD.getText();
                    Selection.setSelection(etable, etable.length());
                }
            }
        });
        mPwdVisibleUsed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mUsedPWD.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    Editable etable = mUsedPWD.getText();
                    Selection.setSelection(etable, etable.length());
                } else {
                    mUsedPWD.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    Editable etable = mUsedPWD.getText();
                    Selection.setSelection(etable, etable.length());
                }
            }
        });
        mNewPWD.setKeyListener(new NumberKeyListener() {
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
                return InputType.TYPE_CLASS_TEXT;
            }
        });
        mUsedPWD.setKeyListener(new NumberKeyListener() {
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
                return InputType.TYPE_CLASS_TEXT;
            }
        });
    }
    private void checkPWD(){
        if (!StringUtil.isPassword(mUsedPWD.getText().toString())) {
            mUsedPWD.setError(getString(R.string.password_error));
            return;
        }else if (!StringUtil.isPassword(mNewPWD.getText().toString())) {
            mNewPWD.setError(getString(R.string.password_error));
            return;
        }else{
            Toast.makeText(ChangePassWordActivity.this, "save", Toast.LENGTH_SHORT).show();
        }
    }

}
