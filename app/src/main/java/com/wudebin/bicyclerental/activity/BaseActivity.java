package com.wudebin.bicyclerental.activity;

import android.os.Bundle;


public abstract class BaseActivity extends AbstractBaseActivity {

    public HttpClientProxy mHttpClientProxy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHttpClientProxy = new HttpClientProxy();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHttpClientProxy != null) {
            mHttpClientProxy.cancelAll();
        }
    }
}
