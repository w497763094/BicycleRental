package com.wudebin.bicyclerental.login;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.method.NumberKeyListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.wudebin.bicyclerental.constant.SMSConstant;
import com.wudebin.bicyclerental.fragment.AbstractBaseFragment;
import com.wudebin.bicyclerental.util.StringUtil;

import com.wudebin.bicyclerental.R;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisterPhoneFragment extends AbstractBaseFragment implements View.OnClickListener {

    private Button mBtnGetCode;
    private CheckBox mPwdVisiable;
    private MaterialEditText mEditCode;
    private MaterialEditText mEditPhone;
    private MaterialEditText mEditPwd;
    private FloatingActionButton mFabRegister;
    private TimerCount mTimer;
    private Context mContext;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_phone, null);
        init(view);
        initSMS();
        return view;
    }

    public void init(View v) {
        mTimer = new TimerCount(60 * 1000, 1000);
        mBtnGetCode = (Button) v.findViewById(R.id.register_getcode);
        mPwdVisiable = (CheckBox) v.findViewById(R.id.register_pwd_visiable);
        mEditCode = (MaterialEditText) v.findViewById(R.id.register_edit_code);
        mEditPhone = (MaterialEditText) v.findViewById(R.id.register_edit_phone);
        mEditPwd = (MaterialEditText) v.findViewById(R.id.register_edit_pwd);
        mFabRegister = (FloatingActionButton) v.findViewById(R.id.register_fab_phone);
        mBtnGetCode.setOnClickListener(this);
        mPwdVisiable.setOnClickListener(this);
        mFabRegister.setOnClickListener(this);
        mPwdVisiable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        mEditPwd.setKeyListener(new NumberKeyListener() {
            @Override
            protected char[] getAcceptedChars() {
                return new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
                        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
            }

            @Override
            public int getInputType() {
                return InputType.TYPE_CLASS_TEXT;
            }
        });
        mPwdVisiable.setChecked(false);
    }

    public void initSMS() {
        SMSSDK.initSDK(mContext, SMSConstant.APP_KEY, SMSConstant.APP_SECRET);
        EventHandler eh = new EventHandler() {

            @Override
            public void afterEvent(int event, int result, Object data) {

                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }

        };
        SMSSDK.registerEventHandler(eh);
    }

        public Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            Log.e("event", "event=" + event);
            if (result == SMSSDK.RESULT_COMPLETE) {
                //短信注册成功后，返回MainActivity,然后提示新好友
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//提交验证码成功
                    Toast.makeText(mContext, "验证码成功", Toast.LENGTH_SHORT).show();
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    mTimer.start();
                    Toast.makeText(mContext, "验证码已经发送", Toast.LENGTH_SHORT).show();
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {//返回支持发送验证码的国家列表
                    Toast.makeText(mContext, "获取国家列表成功", Toast.LENGTH_SHORT).show();
                }
            } else {
                ((Throwable) data).printStackTrace();
//                int resId = getStringRes(mContext, "smssdk_network_error");
                Toast.makeText(mContext, "验证码错误", Toast.LENGTH_SHORT).show();
//                if ( R.string.sms_error> 0) {
//                Toast.makeText(mContext,  R.string.sms_error, Toast.LENGTH_SHORT).show();
//            }
            }

        }

    };

    @Override
    public void onDetach() {
        super.onDetach();
        mTimer.cancel();
        SMSSDK.unregisterAllEventHandler();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_getcode:
                if (!StringUtil.isMobileNO(mEditPhone.getText().toString())) {
                    mEditPhone.setError(getString(R.string.edit_error));
                } else {
                    SMSSDK.getVerificationCode("86", mEditPhone.getText().toString());
                }
                break;
            case R.id.register_fab_phone:
                check();
                break;
        }
    }

    public void check() {
        if (!StringUtil.isMobileNO(mEditPhone.getText().toString())) {
            mEditPhone.setError(getString(R.string.edit_error));
        } else if (!StringUtil.isPassword(mEditPwd.getText().toString())) {
            mEditPwd.setError(getString(R.string.password_error));
        } else {
            SMSSDK.submitVerificationCode("86", mEditPhone.getText().toString(), mEditCode.getText().toString());
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
