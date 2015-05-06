package com.wudebin.bicyclerental.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.wudebin.bicyclerental.activity.HttpClientProxy;


public abstract class AbstractBaseFragment extends Fragment  {

    public HttpClientProxy mHttpClientProxy;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mHttpClientProxy = new HttpClientProxy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mHttpClientProxy != null) {
            mHttpClientProxy.cancelAll();
        }
    }
public HttpClientProxy getHttpClientProxy(){
    return mHttpClientProxy;
}

}
